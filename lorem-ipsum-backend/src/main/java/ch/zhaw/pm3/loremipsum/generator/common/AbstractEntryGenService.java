package ch.zhaw.pm3.loremipsum.generator.common;

import ch.zhaw.pm3.loremipsum.common.OptionCategoryEnum;
import ch.zhaw.pm3.loremipsum.common.OptionEnum;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.EntryDto;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.OptionDto;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.RowTemplateDto;
import com.github.javafaker.service.RandomService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractEntryGenService {
    protected Set<OptionEnum> allowedOptions = new HashSet<>();
    protected RandomService randomService = new RandomService();

    protected AbstractEntryGenService(OptionEnum... optionEnums) {
        this.allowedOptions.addAll(List.of(optionEnums));
    }

    public EntryDto genEntry(RowTemplateDto rowTemplateDto, Set<OptionDto> optionDtoSet) {

        optionDtoSet.forEach(optionDto -> {
            if (OptionCategoryEnum.GENERATOR == optionDto.getOptionEnum().getOptionCategoryEnum() &&
                    !this.allowedOptions.contains(optionDto.getOptionEnum())) {
                throw new IllegalArgumentException("The option " + optionDto.getOptionData() + " is not Allowed here.");
            }
        });


        EntryDto entryDto = new EntryDto();
        entryDto.setName(rowTemplateDto.getName());
        entryDto.setData(getData(rowTemplateDto, optionDtoSet));
        return entryDto;
    }

    protected abstract String getData(RowTemplateDto rowTemplateDto, Set<OptionDto> optionDtoSet);

}
