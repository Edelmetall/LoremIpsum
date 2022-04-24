package ch.zhaw.pm3.loremipsum.generator.template.ui.dto;

import ch.zhaw.pm3.loremipsum.common.EntryTypeEnum;
import ch.zhaw.pm3.loremipsum.common.OptionEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DataFormatDto {
    private String name;
    private final List<OptionDto> options = new ArrayList<>();

    public DataFormatDto(EntryTypeEnum entryTypeEnum){
       this.name = entryTypeEnum.getDisplayName();
       for(OptionEnum optionEnum: entryTypeEnum.getAvailableOptions()){
           options.add(new OptionDto(optionEnum));
       }
    }
}
