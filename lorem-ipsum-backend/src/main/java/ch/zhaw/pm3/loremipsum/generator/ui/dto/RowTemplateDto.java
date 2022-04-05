package ch.zhaw.pm3.loremipsum.generator.ui.dto;

import ch.zhaw.pm3.loremipsum.generator.data.RowTemplateEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

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
    private Set<String> option;
    private String regex;



    public RowTemplateDto mapFrom(RowTemplateEntity rowTemplateEntity) {
        this.setId(rowTemplateEntity.getId());
        this.setIndex(rowTemplateEntity.getIndex());
        return this;
    }


}
