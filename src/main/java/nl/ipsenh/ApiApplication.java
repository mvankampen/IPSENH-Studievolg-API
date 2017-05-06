package nl.ipsenh;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import nl.ipsenh.model.ABRequirement;
import nl.ipsenh.model.User;
import nl.ipsenh.persistence.ABRequirementDAO;
import nl.ipsenh.persistence.CourseDAO;
import nl.ipsenh.persistence.CoursePassedDAO;
import nl.ipsenh.persistence.EnrollmentDAO;
import nl.ipsenh.persistence.RestrictionDAO;
import nl.ipsenh.persistence.RoleDAO;
import nl.ipsenh.persistence.UserDAO;
import nl.ipsenh.resource.CourseResource;
import nl.ipsenh.resource.EnrollmentResource;
import nl.ipsenh.resource.RestrictionResource;
import nl.ipsenh.resource.RoleResource;
import nl.ipsenh.resource.UserResource;
import nl.ipsenh.service.*;
import org.eclipse.jetty.servlet.FilterHolder;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.skife.jdbi.v2.DBI;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * Created by Jamie on 13-4-2017.
 */
public class ApiApplication extends Application<ApiConfiguration> {

    public static void main(String[] args) throws Exception {
        new ApiApplication().run(args);
    }

    @Override
    public void run(ApiConfiguration configuration, Environment environment) throws Exception {
        System.out.println("Set API name to: " + configuration.getApiName());

        //Setup database
        final DBIFactory dbiFactory = new DBIFactory();
        final DBI jdbi = dbiFactory.build(environment, configuration.getDatabase(), "postgresql");

        // cors
        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        //configure CORS parameters
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, configuration.getAllowedOrigins());
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, configuration.getAllowedHeaders());
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, configuration.getAllowedMethods());
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");


        //Setup resources
        final UserDAO userDAO = jdbi.onDemand(UserDAO.class);
        final RoleDAO roleDAO = jdbi.onDemand(RoleDAO.class);
        final CourseDAO courseDAO = jdbi.onDemand(CourseDAO.class);
        final RestrictionDAO restrictionDAO = jdbi.onDemand(RestrictionDAO.class);
        final ABRequirementDAO abRequirementDAO = jdbi.onDemand(ABRequirementDAO.class);
        final CoursePassedDAO coursePassedDAO = jdbi.onDemand(CoursePassedDAO.class);
        final EnrollmentDAO enrollmentDAO = jdbi.onDemand(EnrollmentDAO.class);


        final UserService userService = new UserService(userDAO);
        final RoleService roleService = new RoleService(roleDAO);
        final CourseService courseService = new CourseService(courseDAO);
        final RestrictionService restrictionService = new RestrictionService(restrictionDAO);
        final ABRequirementService abRequirementService = new ABRequirementService(abRequirementDAO);
        final CoursePassedService coursePassedService = new CoursePassedService(coursePassedDAO);
        final EnrollmentService enrollmentService = new EnrollmentService(restrictionService, abRequirementService, coursePassedService, courseService, enrollmentDAO);

        environment.jersey().register(new UserResource(userService));
        environment.jersey().register(new RoleResource(roleService));
        environment.jersey().register(new CourseResource(courseService));
        environment.jersey().register(new RestrictionResource(restrictionService));
        environment.jersey().register(new EnrollmentResource(enrollmentService));

        setupAuthentication(environment, userDAO);
        configureClientFilter(environment);
    }

    private void setupAuthentication(Environment environment, UserDAO userDAO) {
        AuthenticationService authenticationService = new AuthenticationService(userDAO);
        ApiUnauthorizedHandler apiUnauthorizedHandler = new ApiUnauthorizedHandler();

        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(authenticationService)
                        .setAuthorizer(authenticationService)
                        .setRealm("Admin privileges are required to access this resource")
                        .setUnauthorizedHandler(apiUnauthorizedHandler)
                        .buildAuthFilter())
        );
        environment.jersey().register(new AuthValueFactoryProvider.Binder<User>(User.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
    }

    private void configureClientFilter(Environment environment) {
        environment.getApplicationContext().addFilter(
                new FilterHolder(new ClientFilter()),
                "/*",
                EnumSet.allOf(DispatcherType.class)
        );
    }
}
