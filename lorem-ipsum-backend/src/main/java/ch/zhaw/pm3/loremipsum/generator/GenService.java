package ch.zhaw.pm3.loremipsum.generator;

import ch.zhaw.pm3.loremipsum.generator.service.FirstNameService;
import ch.zhaw.pm3.loremipsum.generator.service.LastNameService;
import ch.zhaw.pm3.loremipsum.generator.service.TeleNrService;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.EntryDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.GenDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowEntryDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowTemplateDto;
import ch.zhaw.pm3.loremipsum.output.OutputEnum;
import ch.zhaw.pm3.loremipsum.output.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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

    public String generateStuff(GenDto genDto) {
        int testDataSetSize = 10;
        Set<RowEntryDto> rowEntryDtos = new HashSet<>();
        while (rowEntryDtos.size() < testDataSetSize) {
            RowEntryDto rowEntryDto = new RowEntryDto();
            for (RowTemplateDto rowTemplateDto : genDto.getTemplateDto().getRowTemplateDtoSet()) {
                rowEntryDto.getEntryList().add(getEntryType(rowTemplateDto));
            }
            rowEntryDtos.add(rowEntryDto);
        }
        return outputService.generateModel(OutputEnum.valueOf(genDto.getOutput()), rowEntryDtos);
    }


    private EntryDto getEntryType(RowTemplateDto rowTemplateDto) {
        return switch (EntryTypeEnum.getEnumFromDisplayName(rowTemplateDto.getDataType())) {
            case FIRST_NAME -> firstNameService.genEntry(rowTemplateDto, null);
            case LAST_NAME -> lastNameService.genEntry(rowTemplateDto, null);
            case TELE_NR -> teleNrService.genEntry(rowTemplateDto, null);
            default -> null;
        };
    }
}
