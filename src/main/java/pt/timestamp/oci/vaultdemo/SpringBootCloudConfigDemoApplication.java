package pt.timestamp.oci.vaultdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pt.timestamp.oci.vaultdemo.config.properties.k8s.DataSourceProperties;
import pt.timestamp.oci.vaultdemo.config.properties.oci.OCIAuthenticationProperties;
import pt.timestamp.oci.vaultdemo.config.properties.oci.OCIInstanceProperties;
import pt.timestamp.oci.vaultdemo.config.properties.oci.VaultDataSourceProperties;

@SpringBootApplication
@EnableConfigurationProperties({OCIInstanceProperties.class, OCIAuthenticationProperties.class,
		VaultDataSourceProperties.class,
		DataSourceProperties.class, DataSourceProperties.class})
public class SpringBootCloudConfigDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCloudConfigDemoApplication.class, args);
	}

}
