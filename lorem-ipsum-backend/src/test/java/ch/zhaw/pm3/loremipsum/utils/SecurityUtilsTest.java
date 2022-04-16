package ch.zhaw.pm3.loremipsum.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Test SecurityUtils
 */
public class SecurityUtilsTest {

    /**
     * Test encodePassword method
     */
    @Test
    public void testEncodePassword() {
        String password1 = "pw1";
        String password2 = "pw2";

        byte[] salt = new byte[32];
        byte[] encoded1 = SecurityUtils.encodePassword(salt, password1);
        byte[] encoded2 = SecurityUtils.encodePassword(salt, password2);

        // should be the same (password 1)
        Assertions.assertArrayEquals(encoded1, SecurityUtils.encodePassword(salt, password1));

        // should be the same (password 2)
        Assertions.assertArrayEquals(encoded2, SecurityUtils.encodePassword(salt, password2));

        // encrypted password of password 1 and 2 should be different
        Assertions.assertFalse(Arrays.equals(encoded1, encoded2),
                "Encoded password of %s and %s should not be the same".formatted(password1, password2));
    }

    /**
     * Test md5 method
     */
    @Test
    public void testMd5() {
        String test1 = "test1";
        String test2 = "test2";

        byte[] hash1 = SecurityUtils.md5(test1);
        byte[] hash2 = SecurityUtils.md5(test2);

        // hash should be the same (test 1)
        Assertions.assertArrayEquals(hash1, SecurityUtils.md5(test1));

        // hash should be the same (test 2)
        Assertions.assertArrayEquals(hash2, SecurityUtils.md5(test2));

        // hash of test 1 and 2 should be different
        Assertions.assertFalse(Arrays.equals(hash1, hash2),
                "MD5 hash of %s and %s should not be the same".formatted(test1, test2));
    }
}
