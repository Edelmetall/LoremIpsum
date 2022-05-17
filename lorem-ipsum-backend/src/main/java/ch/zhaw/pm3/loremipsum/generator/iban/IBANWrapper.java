package ch.zhaw.pm3.loremipsum.generator.iban;


import ch.zhaw.pm3.loremipsum.common.CountryEnum;

public record IBANWrapper(String format, String bankCode, CountryEnum landEnum) {
}
