package Security;

import org.bouncycastle.util.encoders.Hex;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PBKDF2 {

    public PBKDF2(){}

    public static String derivedKeyGen (String password, String salt, Integer iterations) {
        var specs = new PBEKeySpec(
                password.toCharArray(),
                salt.getBytes(),
                iterations,
                128);

        try {
            var pbkdf2 = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            var secretKey = pbkdf2.generateSecret(specs);
            return Hex.toHexString(secretKey.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
