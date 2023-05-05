package pt.timestamp.oci.vaultdemo.config.vault;

import com.oracle.bmc.auth.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import pt.timestamp.oci.vaultdemo.config.properties.oci.OCIAuthenticationProperties;
import pt.timestamp.oci.vaultdemo.config.properties.oci.OCIInstanceProperties;

import java.io.IOException;

@Configuration
public class OCIAuthenticationProviderConfiguration {

    private final OCIInstanceProperties instanceProperties;
    private final OCIAuthenticationProperties authenticationProperties;

    public OCIAuthenticationProviderConfiguration(OCIInstanceProperties instanceProperties, OCIAuthenticationProperties authenticationProperties) {
        this.instanceProperties = instanceProperties;
        this.authenticationProperties = authenticationProperties;
    }

    //@Bean
    //@Profile("configfile_authentication")
    public BasicAuthenticationDetailsProvider buildConfigFileAuthenticationProvider() throws IOException {
        return new ConfigFileAuthenticationDetailsProvider(this.authenticationProperties.profile());
    }

    //@Bean
    //@Profile("instance_authentication")
    public BasicAuthenticationDetailsProvider buildInstancePrincipalsAuthenticationProvider() {
        return InstancePrincipalsAuthenticationDetailsProvider.builder()
                .tenancyId(this.instanceProperties.tenancyId()).build();
    }

    //@Bean
    //@Profile("resource_authentication")
    public BasicAuthenticationDetailsProvider buildResourcePrincipalAuthenticationProvider() {
        return ResourcePrincipalAuthenticationDetailsProvider.builder().build();
    }

    @Bean
    //@Profile("simple_authentication")
    public BasicAuthenticationDetailsProvider buildSimpleAuthenticationProvider() {
        return SimpleAuthenticationDetailsProvider.builder()
                .tenantId(this.instanceProperties.tenancyId())
                .userId(this.authenticationProperties.userId())
                .fingerprint(this.authenticationProperties.fingerprint())
                .privateKeySupplier(new StringPrivateKeySupplier(this.authenticationProperties.privateKey()))
                .passPhrase(this.authenticationProperties.passphrase()).build();
    }
}

