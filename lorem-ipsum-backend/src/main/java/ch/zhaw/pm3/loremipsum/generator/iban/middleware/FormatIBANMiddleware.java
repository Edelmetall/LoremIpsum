package ch.zhaw.pm3.loremipsum.generator.iban.middleware;

import ch.zhaw.pm3.loremipsum.common.LandEnum;
import ch.zhaw.pm3.loremipsum.generator.iban.IBANWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormatIBANMiddleware extends Middleware<IBANWrapper> {

    private static final Map<LandEnum, List<Range>> COUNTRY_IBAN_FORMAT = new HashMap<>();

    static {
        // In Beta only ZKB Accounts
        COUNTRY_IBAN_FORMAT.put(LandEnum.SWITZERLAND, List.of(new Range(0, 4),
                new Range(4, 8), new Range(8, 12), new Range(12, 16), new Range(16, 20), new Range(20, 21)));
        // In Beta only Irsuuauk Accounts
        COUNTRY_IBAN_FORMAT.put(LandEnum.UKRAINE, List.of(new Range(0, 4), new Range(4, 8),
                new Range(8, 12), new Range(12, 16), new Range(16, 20), new Range(20, 24),
                new Range(24, 28), new Range(28, 29)));
    }

    @Override
    public String handle(String field, IBANWrapper businessWrapper) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < COUNTRY_IBAN_FORMAT.get(businessWrapper.landEnum()).size(); i++) {
            Range currentRange = COUNTRY_IBAN_FORMAT.get(businessWrapper.landEnum()).get(i);
            stringBuilder.append(field, currentRange.from, currentRange.to);

            if (i != COUNTRY_IBAN_FORMAT.get(businessWrapper.landEnum()).size() - 1) {
                stringBuilder.append(" ");
            }
        }
        return handleNext(stringBuilder.toString(), businessWrapper);
    }


    record Range(int from, int to) {
    }
}
