package ch.zhaw.pm3.loremipsum.generator;

import ch.zhaw.pm3.loremipsum.common.EntryTypeEnum;
import ch.zhaw.pm3.loremipsum.common.HeaderInfomation;
import ch.zhaw.pm3.loremipsum.generator.service.FirstNameService;
import ch.zhaw.pm3.loremipsum.generator.service.LastNameService;
import ch.zhaw.pm3.loremipsum.generator.service.TeleNrService;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.EntryDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.GenDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowEntryDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowConfigurationDto;
import ch.zhaw.pm3.loremipsum.output.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Generiert anhand dem mitgegebenen Templatekonfiguration die Testdaten.
     *
     * @param genDto Templatekonfiguration
     * @return Liste der gewuenschten Testdaten
     */
    public List<RowEntryDto> generateData(GenDto genDto) {
        int testDataSetSize = 10;
        List<RowEntryDto> rowEntryDtos = new ArrayList<>();
        while (rowEntryDtos.size() < testDataSetSize) {
            RowEntryDto rowEntryDto = new RowEntryDto();
            for (RowConfigurationDto rowConfigurationDto : genDto.getTemplateDto().getRowConfigurationDtoSet()) {

                rowEntryDto.getEntryList().add(getEntryType(rowConfigurationDto).getData());
            }
            rowEntryDtos.add(rowEntryDto);
        }
        return rowEntryDtos;
    }

    /**
     * Generiert die Liste der Headerinformationen gemaess der TemplateKonfiguration
     *
     * @param genDto Templatekonfiguration
     * @return Liste der Headerinformationen
     */
    public List<HeaderInfomation> getHeaderInformation(GenDto genDto) {
        List<HeaderInfomation> headerInfomationList = new ArrayList<>();
        genDto.getTemplateDto().getRowConfigurationDtoSet().forEach(rowTemplateDto ->
                headerInfomationList.add(new HeaderInfomation(rowTemplateDto.getName(),
                        EntryTypeEnum.getEnumFromDisplayName(rowTemplateDto.getDataType())))
        );
        return headerInfomationList;
    }


    private EntryDto getEntryType(RowConfigurationDto rowConfigurationDto) {
        return switch (EntryTypeEnum.getEnumFromDisplayName(rowConfigurationDto.getDataType())) {
            case FIRST_NAME -> firstNameService.genEntry(rowConfigurationDto, null);
            case LAST_NAME -> lastNameService.genEntry(rowConfigurationDto, null);
            case TELE_NR -> teleNrService.genEntry(rowConfigurationDto, null);
            default -> null;
        };
    }
}
