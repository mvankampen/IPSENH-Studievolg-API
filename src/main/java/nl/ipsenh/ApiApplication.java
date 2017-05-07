package nl.ipsenh;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import nl.ipsenh.model.User;
import nl.ipsenh.persistence.ABRequirementDAO;
import nl.ipsenh.persistence.CourseDAO;
import nl.ipsenh.persistence.CoursePassedDAO;
import nl.ipsenh.persistence.CourseRestrictionDAO;
import nl.ipsenh.persistence.EnrollmentDAO;
import nl.ipsenh.persistence.UserDAO;
import nl.ipsenh.resource.CourseResource;
import nl.ipsenh.resource.EnrollmentResource;
import nl.ipsenh.resource.RestrictionResource;
import nl.ipsenh.resource.UserResource;
import nl.ipsenh.service.ABRequirementService;
import nl.ipsenh.service.AuthenticationService;
import nl.ipsenh.service.CoursePassedService;
import nl.ipsenh.service.CourseService;
import nl.ipsenh.service.EnrollmentService;
import nl.ipsenh.service.RestrictionService;
import nl.ipsenh.service.UserService;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.skife.jdbi.v2.DBI;

/**
 * @author Jamie Kalloe, Lorenzo Jokhan, Michael van Kampen
 * @version 1.0
 * @since 2017-04-13
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
    final FilterRegistration.Dynamic cors = environment.servlets()
        .addFilter("CORS", CrossOriginFilter.class);

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

    final UserService userService = new UserService(userDAO);
    final CourseService courseService = new CourseService(courseDAO);
    final RestrictionService restrictionService = new RestrictionService(courseRestrictionDAO);
    final ABRequirementService abRequirementService = new ABRequirementService(abRequirementDAO);
    final CoursePassedService coursePassedService = new CoursePassedService(coursePassedDAO);
    final EnrollmentService enrollmentService = new EnrollmentService(restrictionService,
        abRequirementService, coursePassedService, courseService, enrollmentDAO);

    environment.jersey().register(new UserResource(userService));
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
