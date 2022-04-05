package ch.zhaw.pm3.loremipsum.generator.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class OptionDto implements Serializable {
    private String optionName;
    private String optionData;
}
