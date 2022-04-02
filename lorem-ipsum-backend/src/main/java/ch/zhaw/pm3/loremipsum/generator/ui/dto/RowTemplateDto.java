package ch.zhaw.pm3.loremipsum.generator.ui.dto;

import ch.zhaw.pm3.loremipsum.generator.data.RowTemplateEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RowTemplateDto implements Serializable {

    private Long id;
    private int index;


    public RowTemplateDto mapFrom(RowTemplateEntity rowTemplateEntity) {
        this.setId(rowTemplateEntity.getId());
        this.setIndex(rowTemplateEntity.getIndex());
        return this;
    }


}
