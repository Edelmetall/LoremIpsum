package ch.zhaw.pm3.loremipsum.generator.iban.middleware;

import ch.zhaw.pm3.loremipsum.generator.iban.IBANWrapper;

import java.math.BigDecimal;

public class IBANCheckSumMiddleware extends Middleware<IBANWrapper> {

    final int asciOffset = 55;
    final BigDecimal modulo97 = new BigDecimal("97");
    final BigDecimal minuend = new BigDecimal("98");

    @Override
    public String handle(String field, IBANWrapper businessWrapper) {


        int firstChar = field.charAt(0) - asciOffset;
        int secondChar = field.charAt(1) - asciOffset;

        String ibanWithoutChecksumPlaceholder = firstChar + String.valueOf(secondChar) + "00" + field.substring(4);

        BigDecimal ibanAsNumber = new BigDecimal(ibanWithoutChecksumPlaceholder.substring(6) +
                ibanWithoutChecksumPlaceholder.substring(0, 6));

        String ibanWithChechsum = field.replace("pp",
                String.format("%02d", minuend.subtract(ibanAsNumber.remainder(modulo97)).longValue()));
        return handleNext(ibanWithChechsum, businessWrapper);
    }
}
