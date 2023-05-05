package pt.timestamp.oci.vaultdemo.config.properties.oci;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("oci.instance")
public record OCIInstanceProperties(String tenancyId, String regionId) {
}
