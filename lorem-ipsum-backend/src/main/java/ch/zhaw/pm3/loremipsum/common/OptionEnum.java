package ch.zhaw.pm3.loremipsum.common;

import ch.zhaw.pm3.loremipsum.generator.firstname.GenderEnum;

import java.util.Arrays;
import java.util.List;

public enum OptionEnum {

    LAND_CD(OptionCategoryEnum.GENERATOR, CountryEnum.INDIA.name(), CountryEnum.SWITZERLAND.name(), CountryEnum.UKRAINE.name()),
    GENDER(OptionCategoryEnum.GENERATOR, Arrays.stream(GenderEnum.values()).map(Enum::name).toArray(String[]::new)),
    PHONE_NUMER_FORMAT(OptionCategoryEnum.GENERATOR, Arrays.stream(TeleNrFormatEnum.values()).map(Enum::name).toArray(String[]::new)),
    JAVA_VERSION(OptionCategoryEnum.OUTPUT);

    private final OptionCategoryEnum optionCategoryEnum;
    private final List<String> values;

    OptionEnum(OptionCategoryEnum optionCategoryEnum, String... values) {
        this.optionCategoryEnum = optionCategoryEnum;
        this.values = List.of(values);
    }

    public List<String> getValues() {
        return this.values;
    }

    public OptionCategoryEnum getOptionCategoryEnum() {
        return optionCategoryEnum;
    }
}
