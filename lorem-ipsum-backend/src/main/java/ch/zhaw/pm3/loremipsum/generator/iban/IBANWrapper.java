package ch.zhaw.pm3.loremipsum.generator.iban;

import ch.zhaw.pm3.loremipsum.common.LandEnum;

public record IBANWrapper(String format, String bankCode, LandEnum landEnum) {
}
