package ch.zhaw.pm3.loremipsum.generator.ui.dto;

import ch.zhaw.pm3.loremipsum.generator.data.TemplateEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
public class TemplateDto implements Serializable {


    private Long id;
    private String name;
    private List<RowTemplateDto> rowTemplateDtoSet;


    public TemplateDto mapFrom(TemplateEntity templateEntity) {
        this.setId(templateEntity.getId());
        this.setName(templateEntity.getName());

        templateEntity.getRowTemplateEntities().forEach(rowTemplateEntity ->
                this.getRowTemplateDtoSet().add(new RowTemplateDto().mapFrom(rowTemplateEntity)));

        return this;
    }

    public List<RowTemplateDto> getRowTemplateDtoSet() {
        if (this.rowTemplateDtoSet == null) {
            this.rowTemplateDtoSet = new ArrayList<>();
        }
        return this.rowTemplateDtoSet;
    }
}
