package pt.timestamp.oci.vaultdemo.secrets;

import java.io.IOException;

public interface ISecretReader {

    String readtSecret (String secretKey) throws IOException;

}
