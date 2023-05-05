package pt.timestamp.oci.vaultdemo.config.properties.oci;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("oci.vault.datasource")
public record VaultDataSourceProperties(String urlKey, String usernameKey, String passwordKey) {
}
