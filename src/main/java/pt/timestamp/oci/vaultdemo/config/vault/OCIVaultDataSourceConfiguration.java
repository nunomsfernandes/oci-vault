package pt.timestamp.oci.vaultdemo.config.vault;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pt.timestamp.oci.vaultdemo.config.properties.oci.VaultDataSourceProperties;
import pt.timestamp.oci.vaultdemo.datasource.DataSource;
import pt.timestamp.oci.vaultdemo.secrets.ISecretReader;

import java.io.IOException;

@Configuration
public class OCIVaultDataSourceConfiguration {

    private final VaultDataSourceProperties dataSourceProperties;
    private final ISecretReader secretReader;

    public OCIVaultDataSourceConfiguration(VaultDataSourceProperties dataSourceProperties,
                                           @Qualifier("OCIVaultSecretReader") ISecretReader secretReader) {
        this.dataSourceProperties = dataSourceProperties;
        this.secretReader = secretReader;
    }

    @Bean
    public DataSource ociVaultDataSource() throws IOException {
        String url = this.secretReader.readtSecret(this.dataSourceProperties.urlKey());
        String username = this.secretReader.readtSecret(this.dataSourceProperties.usernameKey());
        String password = this.secretReader.readtSecret(this.dataSourceProperties.passwordKey());
        return new DataSource(url, username, password);
    }


}
