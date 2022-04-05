package ch.zhaw.pm3.loremipsum.generator;

import ch.zhaw.pm3.loremipsum.generator.ui.dto.EntryDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.OptionDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowTemplateDto;

import java.util.Set;

public abstract class AbstractEntryGenService {


    public EntryDto genEntry(RowTemplateDto rowTemplateDto, Set<OptionDto> optionDtoSet) {
        EntryDto entryDto = new EntryDto();
        entryDto.setName(rowTemplateDto.getName());
        entryDto.setData(getData(rowTemplateDto, optionDtoSet));
        return entryDto;
    }

    protected abstract String getData(RowTemplateDto rowTemplateDto, Set<OptionDto> optionDtoSet);

}
