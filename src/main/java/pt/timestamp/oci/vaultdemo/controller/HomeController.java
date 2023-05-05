package pt.timestamp.oci.vaultdemo.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.timestamp.oci.vaultdemo.config.properties.oci.OCIInstanceProperties;
import pt.timestamp.oci.vaultdemo.datasource.DataSource;

@RestController
public class HomeController {

    private final DataSource ociVaultEbsDataSource;
    private final DataSource k8sEbsDataSource;
    private final OCIInstanceProperties instanceProperties;

    public HomeController(@Qualifier("ociVaultDataSource") DataSource ociVaultEbsDataSource,
                          @Qualifier("k8sDataSource") DataSource k8sDataSource,
                          OCIInstanceProperties instanceProperties) {
        this.ociVaultEbsDataSource = ociVaultEbsDataSource;
        this.k8sEbsDataSource = k8sDataSource;
        this.instanceProperties = instanceProperties;
    }

    @GetMapping("/")
    public String home() {
        return ">>" + this.instanceProperties.regionId() + ">" + ociVaultEbsDataSource.username() + ":" + ociVaultEbsDataSource.password()
                + "@" + ociVaultEbsDataSource.url() + "\n" +
                ">>" + this.k8sEbsDataSource.username() + ":" + this.k8sEbsDataSource.password() + "@" + this.k8sEbsDataSource.url();
    }
}
