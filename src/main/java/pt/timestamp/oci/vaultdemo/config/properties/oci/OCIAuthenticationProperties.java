package pt.timestamp.oci.vaultdemo.config.properties.oci;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("oci.authentication")
public record OCIAuthenticationProperties(String userId, String fingerprint, String privateKey, String passphrase, String profile) {
}
