package ch.zhaw.pm3.loremipsum.generator.common;

import org.apache.commons.lang3.RandomStringUtils;

public class StringSolverUtil {

    private StringSolverUtil() {
    }


    private static final char DIGIT = '#';      // [0-9]
    private static final char NON_DIGIT = '*';  // [A-z]
    private static final char ANY_CHAR = '&';  // Any Ascii char

    public static String resolve(String stringWithExpression) {

        char[] charArray = stringWithExpression.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = switch (charArray[i]) {
                case DIGIT -> RandomStringUtils.randomNumeric(1, 2).charAt(0);
                case NON_DIGIT -> RandomStringUtils.randomAlphabetic(1, 2).charAt(0);
                case ANY_CHAR -> RandomStringUtils.randomAscii(1, 2).charAt(0);
                default -> charArray[i];
            };
        }

        return new String(charArray);
    }

}
