package pt.timestamp.oci.vaultdemo.config.k8s;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pt.timestamp.oci.vaultdemo.config.properties.k8s.DataSourceProperties;
import pt.timestamp.oci.vaultdemo.datasource.DataSource;

import java.io.IOException;

@Configuration
public class K8sDataSourceConfiguration {

    private final DataSourceProperties dataSourceProperties;

    public K8sDataSourceConfiguration(DataSourceProperties dataSourceProperties) {
        this.dataSourceProperties = dataSourceProperties;
    }

    @Bean
    public DataSource k8sDataSource() {
        return new DataSource(this.dataSourceProperties.url(), this.dataSourceProperties.username(), this.dataSourceProperties.password());
    }


}
