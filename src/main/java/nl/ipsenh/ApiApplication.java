package nl.ipsenh;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import nl.ipsenh.mapper.JsonProcessingExceptionMapper;
import nl.ipsenh.mapper.RuntimeExceptionMapper;
import nl.ipsenh.model.User;
import nl.ipsenh.persistence.*;
import nl.ipsenh.resource.*;
import nl.ipsenh.service.*;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.skife.jdbi.v2.DBI;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * @author Jamie Kalloe, Lorenzo Jokhan, Michael van Kampen
 * @version 1.0
 * @since 2017-04-13
 */
public class ApiApplication extends Application<ApiConfiguration> {

    public static void main(String[] args) throws Exception {
        new ApiApplication().run(args);
    }

    @Override public void run(ApiConfiguration configuration, Environment environment)
        throws Exception {
        System.out.println("Set API name to: " + configuration.getApiName());
        environment.jersey().register(new JsonProcessingExceptionMapper());
        environment.jersey().register(new RuntimeExceptionMapper());

        //Setup database
        final DBIFactory dbiFactory = new DBIFactory();
        final DBI jdbi = dbiFactory.build(environment, configuration.getDatabase(), "postgresql");

        // cors
        final FilterRegistration.Dynamic cors =
            environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        //configure CORS parameters
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM,
            configuration.getAllowedOrigins());
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM,
            configuration.getAllowedHeaders());
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM,
            configuration.getAllowedMethods());
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        //Setup resources
        final UserDAO userDAO = jdbi.onDemand(UserDAO.class);
        final CourseDAO courseDAO = jdbi.onDemand(CourseDAO.class);
        final CourseRestrictionDAO courseRestrictionDAO = jdbi.onDemand(CourseRestrictionDAO.class);
        final ABRequirementDAO abRequirementDAO = jdbi.onDemand(ABRequirementDAO.class);
        final CoursePassedDAO coursePassedDAO = jdbi.onDemand(CoursePassedDAO.class);
        final EnrollmentDAO enrollmentDAO = jdbi.onDemand(EnrollmentDAO.class);
        final CourseOwnerDAO courseOwnerDAO = jdbi.onDemand(CourseOwnerDAO.class);
        final ExamDAO examDAO = jdbi.onDemand(ExamDAO.class);
        final ExamResultDAO examResultDAO = jdbi.onDemand(ExamResultDAO.class);

        final UserService userService = new UserService(userDAO);
        final CourseService courseService = new CourseService(courseDAO);
        final RestrictionService restrictionService = new RestrictionService(courseRestrictionDAO);
        final ABRequirementService abRequirementService =
            new ABRequirementService(abRequirementDAO, courseService);
        final CoursePassedService coursePassedService = new CoursePassedService(coursePassedDAO);
        final EnrollmentService enrollmentService =
            new EnrollmentService(restrictionService, abRequirementService, coursePassedService,
                courseService, enrollmentDAO);
        final CourseOwnerService courseOwnerService =
            new CourseOwnerService(courseOwnerDAO, userService);
        final ExamService examService = new ExamService(examDAO);
        final ExamResultService examResultService =
            new ExamResultService(examResultDAO, enrollmentService, userService, courseService);

        environment.jersey().register(new UserResource(userService));
        environment.jersey().register(new CourseResource(courseService));
        environment.jersey().register(new RestrictionResource(restrictionService));
        environment.jersey().register(new ABRestrictionResource(abRequirementService));
        environment.jersey().register(new EnrollmentResource(enrollmentService));
        environment.jersey().register(new CourseOwnerResource(courseOwnerService));
        environment.jersey().register(new ExamResource(examService));
        environment.jersey().register(new ExamResultResource(examResultService));

        setupAuthentication(environment, userDAO);
        configureClientFilter(environment);
    }

    private void setupAuthentication(Environment environment, UserDAO userDAO) {
        AuthenticationService authenticationService = new AuthenticationService(userDAO);
        ApiUnauthorizedHandler apiUnauthorizedHandler = new ApiUnauthorizedHandler();

        environment.jersey().register(new AuthDynamicFeature(
            new BasicCredentialAuthFilter.Builder<User>().setAuthenticator(authenticationService)
                .setAuthorizer(authenticationService)
                .setRealm("Admin privileges are required to access this resource")
                .setUnauthorizedHandler(apiUnauthorizedHandler).buildAuthFilter()));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<User>(User.class));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
    }

    private void configureClientFilter(Environment environment) {
        environment.getApplicationContext().addFilter(new FilterHolder(new ClientFilter()), "/*",
            EnumSet.allOf(DispatcherType.class));
    }
}
