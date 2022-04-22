package ch.zhaw.pm3.loremipsum.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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

    /**
     * generate a random password (8 character with one uppercase letter and one digit)
     *
     * @return random password
     */
    public static String generatePassword() {
        SecureRandom random = new SecureRandom();

        char[] password = new char[8];
        int indexUpper = random.nextInt(password.length / 2);
        int indexDigit = random.nextInt(password.length / 2) + password.length / 2;

        for (int i = 0; i < password.length; i++) {
            if (i == indexDigit) {
                password[i] = (char) ('0' + random.nextInt(10)); // random digit
            } else {
                password[i] = (char) ((i == indexUpper ? 'A' : 'a') + random.nextInt(26)); // random letter
            }
        }

        return String.valueOf(password);
    }
}
