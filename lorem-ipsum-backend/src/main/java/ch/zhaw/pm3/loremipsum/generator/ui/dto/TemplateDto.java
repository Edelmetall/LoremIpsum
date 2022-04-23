package ch.zhaw.pm3.loremipsum.generator.ui.dto;

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
    private List<RowConfigurationDto> rowConfigurationDtoSet;

    public List<RowConfigurationDto> getRowConfigurationDtoSet() {
        if (this.rowConfigurationDtoSet == null) {
            this.rowConfigurationDtoSet = new ArrayList<>();
        }
        return this.rowConfigurationDtoSet;
    }
}
