package ch.zhaw.pm3.loremipsum.generator.pan;

import ch.zhaw.pm3.loremipsum.generator.common.Middleware;

public class LuhnAlgoMiddleware extends Middleware<String> {
    @Override
    public String handle(String field, String businessWrapper) {
        String panWithoutCheck = field.substring(0, field.length() - 1);
        return handleNext(panWithoutCheck + calculateCheckDigit(panWithoutCheck), businessWrapper);
    }


    /**
     * Calculates the last digits for the card number received as parameter
     *
     * @param card {@link String} number
     * @return {@link String} the check digit
     */
    private String calculateCheckDigit(String card) {
        if (card == null)
            return null;
        String digit;
        /* convert to array of int for simplicity */
        int[] digits = new int[card.length()];
        for (int i = 0; i < card.length(); i++) {
            digits[i] = Character.getNumericValue(card.charAt(i));
        }

        /* double every other starting from right - jumping from 2 in 2 */
        for (int i = digits.length - 1; i >= 0; i -= 2) {
            digits[i] += digits[i];

            /* taking the sum of digits grater than 10 - simple trick by substract 9 */
            if (digits[i] >= 10) {
                digits[i] = digits[i] - 9;
            }
        }
        int sum = 0;
        for (int j : digits) {
            sum += j;
        }
        /* multiply by 9 step */
        sum = sum * 9;

        /* convert to string to be easier to take the last digit */
        digit = sum + "";
        return digit.substring(digit.length() - 1);
    }
}
