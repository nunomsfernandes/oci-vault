package pt.timestamp.oci.vaultdemo.secrets.vault;


import com.oracle.bmc.auth.*;
import com.oracle.bmc.secrets.SecretsClient;
import com.oracle.bmc.secrets.model.Base64SecretBundleContentDetails;
import com.oracle.bmc.secrets.requests.GetSecretBundleRequest;
import com.oracle.bmc.secrets.responses.GetSecretBundleResponse;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pt.timestamp.oci.vaultdemo.config.properties.oci.OCIInstanceProperties;
import pt.timestamp.oci.vaultdemo.secrets.ISecretReader;

import java.io.IOException;

/***
 * ISecretReader that read secrets from an OCI vault
 */
@Component
public class OCIVaultSecretReader implements ISecretReader {

    private final String regionId;
    private final BasicAuthenticationDetailsProvider authProvider;

    public OCIVaultSecretReader(OCIInstanceProperties instanceProperties, BasicAuthenticationDetailsProvider authProvider) {
        this.regionId = instanceProperties.regionId();
        this.authProvider = authProvider;
    }

    @Override
    public String readtSecret (String secretKey) {
        GetSecretBundleResponse getSecretBundleResponse;
        try (SecretsClient secretsClient = SecretsClient.builder().region(this.regionId).build(this.authProvider)) {
            GetSecretBundleRequest getSecretBundleRequest = GetSecretBundleRequest
                    .builder()
                    .secretId(secretKey)
                    .stage(GetSecretBundleRequest.Stage.Latest)
                    .build();
            getSecretBundleResponse = secretsClient.getSecretBundle(getSecretBundleRequest);
        }
        Base64SecretBundleContentDetails base64SecretBundleContentDetails =
                (Base64SecretBundleContentDetails) getSecretBundleResponse.getSecretBundle().getSecretBundleContent();
        byte[] secretValueDecoded = Base64.decodeBase64(base64SecretBundleContentDetails.getContent());
        return new String(secretValueDecoded);
    }


}
