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

    @NotEmpty
    private String apiName;
//
//    @Valid
//    @NotNull
//    @JsonProperty
    private final AssetsConfiguration assets = AssetsConfiguration.builder().build();
//
//    @Valid
//    @NotNull
//    private DataSourceFactory database = new DataSourceFactory();
//
//    public DataSourceFactory getDatabase() {
//        return this.database;
//    }

    public AssetsConfiguration getAssetsConfiguration() {
        return assets;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }
    
//
//    @JsonProperty("database")
//    public void setDatabase(DataSourceFactory database) {
//        this.database = database;
//    }
}
