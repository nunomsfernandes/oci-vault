package pt.timestamp.oci.vaultdemo.config.properties.k8s;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("datasource")
public record DataSourceProperties(String url, String username, String password) {
}
