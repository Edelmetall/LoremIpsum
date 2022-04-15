package ch.zhaw.pm3.loremipsum.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class SecurityUtils {

    private SecurityUtils() {
    }

    /**
     * covnert password to a secure byte array
     *
     * @param salt     salt
     * @param password password
     * @return secure byte array
     */
    public static byte[] encodePassword(byte[] salt, String password) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Generate MD5 hash for a string
     *
     * @param s string
     * @return MD5 hash
     */
    public static byte[] md5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(s.getBytes(), 0, s.length());
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
