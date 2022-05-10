package ch.zhaw.pm3.loremipsum.generator;

import ch.zhaw.pm3.loremipsum.common.EntryTypeEnum;
import ch.zhaw.pm3.loremipsum.common.HeaderInformation;
import ch.zhaw.pm3.loremipsum.generator.firstname.FirstNameService;
import ch.zhaw.pm3.loremipsum.generator.template.service.LastNameService;
import ch.zhaw.pm3.loremipsum.generator.telenr.TeleNrService;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.*;
import ch.zhaw.pm3.loremipsum.output.OutputEnum;
import ch.zhaw.pm3.loremipsum.output.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GenService {

    @Autowired
    private OutputService outputService;

    @Autowired
    private FirstNameService firstNameService;

    @Autowired
    private LastNameService lastNameService;

    @Autowired
    private TeleNrService teleNrService;

    public String generateData(GenDto genDto) {
        int testDataSetSize = 10;
        List<HeaderInformation> headerInformationList = new ArrayList<>();
        List<RowEntryDto> rowEntryDtos = new ArrayList<>();

        genDto.getTemplateDto().getRowTemplateDtoSet().forEach(rowTemplateDto ->
                headerInformationList.add(new HeaderInformation(rowTemplateDto.getName(),
                        EntryTypeEnum.getEnumFromDisplayName(rowTemplateDto.getDataType())))
        );

        while (rowEntryDtos.size() < testDataSetSize) {
            RowEntryDto rowEntryDto = new RowEntryDto();
            for (RowTemplateDto rowTemplateDto : genDto.getTemplateDto().getRowTemplateDtoSet()) {

                rowEntryDto.getEntryList().add(getEntryType(rowTemplateDto,new HashSet<>(rowTemplateDto.getOption())).getData());
            }
            rowEntryDtos.add(rowEntryDto);
        }
        return outputService.generateModel(OutputEnum.valueOf(genDto.getOutputName()), headerInformationList, rowEntryDtos, genDto.getOutputOption());
    }


    private EntryDto getEntryType(RowTemplateDto rowTemplateDto, Set<OptionDto> optionDtoSet) {
        return switch (EntryTypeEnum.getEnumFromDisplayName(rowTemplateDto.getDataType())) {
            case FIRST_NAME -> firstNameService.genEntry(rowTemplateDto, optionDtoSet);
            case LAST_NAME -> lastNameService.genEntry(rowTemplateDto, optionDtoSet);
            case TELE_NR -> teleNrService.genEntry(rowTemplateDto, optionDtoSet);
            default -> null;
        };
    }
}
