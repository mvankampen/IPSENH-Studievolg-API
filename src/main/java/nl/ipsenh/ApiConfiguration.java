package nl.ipsenh;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.bundles.assets.AssetsBundleConfiguration;
import io.dropwizard.bundles.assets.AssetsConfiguration;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Jamie on 13-4-2017.
 */

public class ApiConfiguration extends Configuration implements AssetsBundleConfiguration {

    @Valid @NotNull @JsonProperty private final AssetsConfiguration assets =
        AssetsConfiguration.builder().build();
    @NotEmpty private String apiName;
    @Valid @NotNull private DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDatabase() {
        return this.database;
    }

    @JsonProperty("database") public void setDatabase(DataSourceFactory database) {
        this.database = database;
    }

    public AssetsConfiguration getAssetsConfiguration() {
        return assets;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getAllowedOrigins() {
        return "http://localhost:3000";
    }

    public String getAllowedMethods() {
        return "GET, POST, PUT, DELETE, OPTIONS";
    }

    public String getAllowedHeaders() {
        return "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin";
    }
}
