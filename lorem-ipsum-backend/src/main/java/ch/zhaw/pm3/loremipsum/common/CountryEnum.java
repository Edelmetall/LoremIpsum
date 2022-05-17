package ch.zhaw.pm3.loremipsum.common;

import lombok.Getter;

@Getter
public enum CountryEnum {

    // Find supported code on https://github.com/DiUS/java-faker
    INDIA("en-IND"),
    SWITZERLAND("de-CH"),
    UKRAINE("uk-UA");

    private final String locale;

    CountryEnum(String locale) {
        this.locale = locale;
    }
}
