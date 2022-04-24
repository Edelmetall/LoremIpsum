package ch.zhaw.pm3.loremipsum.generator.template.ui.dto;

import ch.zhaw.pm3.loremipsum.common.OptionEnum;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
public class OptionDto implements Serializable {
    private final OptionEnum optionEnum;
    private final List<String> values;
    private String optionData;

    public OptionDto(OptionEnum optionEnum){
        this.optionEnum = optionEnum;
        this.values = optionEnum.getValues();
    }

    public OptionDto(OptionEnum optionEnum, String optionData){
        this(optionEnum);
        this.optionData = optionData;
    }
}
