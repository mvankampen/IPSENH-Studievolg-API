package nl.ipsenh;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import nl.ipsenh.persistence.UserDAO;
import nl.ipsenh.resource.UserResource;
import nl.ipsenh.service.UserService;
import org.eclipse.jetty.servlet.FilterHolder;
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

        final UserService userService = new UserService(userDAO);

        environment.jersey().register(new UserResource(userService));

        configureClientFilter(environment);
    }

    private void configureClientFilter(Environment environment) {
        environment.getApplicationContext().addFilter(
                new FilterHolder(new ClientFilter()),
                "/*",
                EnumSet.allOf(DispatcherType.class)
        );
    }
}
