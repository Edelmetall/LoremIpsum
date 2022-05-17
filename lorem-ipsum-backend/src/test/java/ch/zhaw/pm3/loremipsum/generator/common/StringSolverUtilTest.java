package ch.zhaw.pm3.loremipsum.generator.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for StringSolverUtil
 */
public class StringSolverUtilTest {

    @Test
    public void testDigit() {
        String regex = "^\\d{4,4}$";
        String stringWithExpression = "####";

        for (int i = 0; i < 100; i++) {
            Assertions.assertTrue(StringSolverUtil.resolve(stringWithExpression).matches(regex));
        }
    }

    @Test
    public void testNonDigit() {
        String regex = "^[A-Za-z]{4,4}$";
        String stringWithExpression = "****";

        for (int i = 0; i < 100; i++) {
            Assertions.assertTrue(StringSolverUtil.resolve(stringWithExpression).matches(regex));
        }
    }

    @Test
    public void testAnyChar() {
        String regex = "^[\\x00-\\x7F]{4,4}$";
        String stringWithExpression = "&&&&";

        for (int i = 0; i < 100; i++) {
            Assertions.assertTrue(StringSolverUtil.resolve(stringWithExpression).matches(regex));
        }
    }

    @Test
    public void testMixed() {
        String regex = "^\\d[A-Za-z][\\x00-\\x7F]$";
        String stringWithExpression = "#*&";

        for (int i = 0; i < 100; i++) {
            Assertions.assertTrue(StringSolverUtil.resolve(stringWithExpression).matches(regex));
        }
    }
}
