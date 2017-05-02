package nl.ipsenh;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import nl.ipsenh.model.User;
import nl.ipsenh.persistence.RoleDAO;
import nl.ipsenh.persistence.UserDAO;
import nl.ipsenh.resource.RoleResource;
import nl.ipsenh.resource.UserResource;
import nl.ipsenh.service.AuthenticationService;
import nl.ipsenh.service.RoleService;
import nl.ipsenh.service.UserService;
import org.eclipse.jetty.servlet.FilterHolder;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.skife.jdbi.v2.DBI;

import javax.servlet.DispatcherType;
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

        //Setup resources
        final UserDAO userDAO = jdbi.onDemand(UserDAO.class);
        final RoleDAO roleDAO = jdbi.onDemand(RoleDAO.class);

        final UserService userService = new UserService(userDAO);
        final RoleService roleService = new RoleService(roleDAO);

        environment.jersey().register(new UserResource(userService));
        environment.jersey().register(new RoleResource(roleService));

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
