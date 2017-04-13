package nl.ipsenh;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlet.FilterHolder;

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
        System.out.println(String.format("Set API name to: %d", configuration.getApiName()));

        //setup resources
        //TODO: setup user resources

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
