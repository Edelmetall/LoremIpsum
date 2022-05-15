package ch.zhaw.pm3.loremipsum.generator.iban.middleware;

import ch.zhaw.pm3.loremipsum.common.LandEnum;
import ch.zhaw.pm3.loremipsum.generator.iban.IBANWrapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BankCodeMiddleware extends Middleware<IBANWrapper> {


    private static final Map<LandEnum, List<String>> COUNTRY_IBAN_BANK_CODE = new HashMap<>();

    static {
        COUNTRY_IBAN_BANK_CODE.put(LandEnum.SWITZERLAND,
                List.of(
                        "00700", // ZKB
                        "00231")); // UBS
        COUNTRY_IBAN_BANK_CODE.put(LandEnum.UKRAINE, List.of("000231"));
    }

    @Override
    public String handle(String field, IBANWrapper businessWrapper) {
        StringBuilder builder = new StringBuilder(field);
        int indexOfb = field.indexOf("b");
        List<String> bankCodeList = COUNTRY_IBAN_BANK_CODE.get(businessWrapper.landEnum());
        int lengthOfBankCodeCurrentCountry = bankCodeList.get(0).length();

        builder.replace(indexOfb, indexOfb + lengthOfBankCodeCurrentCountry, bankCodeList.get(new Random().nextInt(bankCodeList.size())));
        return handleNext(builder.toString(), businessWrapper);
    }

}
