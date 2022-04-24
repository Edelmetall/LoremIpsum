package ch.zhaw.pm3.loremipsum.generator.template.ui.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
public class TemplateDto implements Serializable {


    private Long id;
    private String name;
    private Long ownerId;
    private List<RowTemplateDto> rowTemplateDtoSet;

    public List<RowTemplateDto> getRowTemplateDtoSet() {
        if (this.rowTemplateDtoSet == null) {
            this.rowTemplateDtoSet = new ArrayList<>();
        }
        return this.rowTemplateDtoSet;
    }
}
