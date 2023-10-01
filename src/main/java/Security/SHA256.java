package Security;

import org.bouncycastle.util.encoders.Hex;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class SHA256 {

    public static String encrypt(String word) {
        try {
            return Hex.toHexString(computeDigest(word.getBytes()));
        } catch (NoSuchProviderException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] computeDigest(byte[] word) throws NoSuchProviderException, NoSuchAlgorithmException {
        var messageDigest = MessageDigest.getInstance("SHA-256", "BC");
        messageDigest.update(word);
        return messageDigest.digest();
    }
}

