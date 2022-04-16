package ch.zhaw.pm3.loremipsum.generator.ui.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GenDto implements Serializable {

    private TemplateDto templateDto;

    private String outputName;
    private String outputOption;
}
