package Security;

import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESCBC {

    public AESCBC(){}

    public static String encrypt(String password, String salt, byte[] iv) {

        try {
            var saltBytes = new SecretKeySpec(Hex.decode(salt), "AES");
            var cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");
            var passBytes = Hex.encode(password.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, saltBytes, new IvParameterSpec(iv, 0, 16));

            var CBCPass = cipher.doFinal(Hex.decode(passBytes));
            return new String(Hex.encode(CBCPass));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
