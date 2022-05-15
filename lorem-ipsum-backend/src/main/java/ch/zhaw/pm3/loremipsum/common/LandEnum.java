package ch.zhaw.pm3.loremipsum.common;

import lombok.Getter;

@Getter
public enum LandEnum {

    // Find supported code on https://github.com/DiUS/java-faker
    INDIA("en-IND"),
    SWITZERLAND("de-CH"),
    UKRAINE("uk-UA");

    private final String locale;


    LandEnum(String locale) {
        this.locale = locale;
    }



}
