package ch.zhaw.pm3.loremipsum.generator.template.ui.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RowTemplateDto implements Serializable {

    // Technisch
    private Long id;


    // Fachliche
    private int index;
    private String dataType;
    private String name;
    private String example;
    private List<OptionDto> option;
    private String regex;

    public List<OptionDto> getOption() {
        if (option == null) {
            this.option = new ArrayList<>();
        }
        return option;
    }
}
