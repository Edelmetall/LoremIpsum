package ch.zhaw.pm3.loremipsum.output;

import ch.zhaw.pm3.loremipsum.common.HeaderInformation;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.OptionDto;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.RowEntryDto;
import ch.zhaw.pm3.loremipsum.output.template.csv.CsvOutputService;
import ch.zhaw.pm3.loremipsum.output.template.csharp.CSharpOutputService;
import ch.zhaw.pm3.loremipsum.output.template.json.JsonOutputService;
import ch.zhaw.pm3.loremipsum.output.template.php.PhpOutputService;
import ch.zhaw.pm3.loremipsum.output.template.sql.SqlOutputService;
import ch.zhaw.pm3.loremipsum.output.template.xml.XmlOutputService;
import ch.zhaw.pm3.loremipsum.output.template.java.JavaOutputService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * generates output
 */
@Service
public class OutputService {

    private final XmlOutputService xmlOutputService;
    private final JsonOutputService jsonOutputService;
    private final JavaOutputService javaOutputService;
    private final SqlOutputService sqlOutputService;
    private final PhpOutputService phpOutputService;
    private final CSharpOutputService cSharpOutputService;
    private final CsvOutputService csvOutputService;

    public OutputService( @Autowired XmlOutputService xmlOutputService,
                          @Autowired JsonOutputService jsonOutputService,
                          @Autowired JavaOutputService javaOutputService,
                          @Autowired SqlOutputService sqlOutputService,
                          @Autowired PhpOutputService phpOutputService,
                          @Autowired CSharpOutputService cSharpOutputService,
                          @Autowired @Qualifier(value = "loremIpsumCsvOutputService") CsvOutputService csvOutputService) {
        this.xmlOutputService = xmlOutputService;
        this.jsonOutputService = jsonOutputService;
        this.javaOutputService = javaOutputService;
        this.sqlOutputService = sqlOutputService;
        this.phpOutputService = phpOutputService;
        this.cSharpOutputService = cSharpOutputService;
        this.csvOutputService = csvOutputService;
    }

    /**
     * generate data
     *
     * @param outputEnum        format
     * @param headerInformation header informations
     * @param rowEntryDtos      fields
     * @param optionDto         selected options
     * @return generated data as a string
     */
    public String generateModel(OutputEnum outputEnum, List<HeaderInformation> headerInformation, List<RowEntryDto> rowEntryDtos, OptionDto optionDto) {
        return switch (outputEnum) {
            case XML -> xmlOutputService.generateOutputFile(headerInformation, rowEntryDtos, optionDto);
            case JAVA -> javaOutputService.generateOutputFile(headerInformation, rowEntryDtos, optionDto);
            case JSON -> jsonOutputService.generateOutputFile(headerInformation, rowEntryDtos, optionDto);
            case SQL -> sqlOutputService.generateOutputFile(headerInformation, rowEntryDtos, optionDto);
            case PHP -> phpOutputService.generateOutputFile(headerInformation, rowEntryDtos, optionDto);
            case CSHARP -> cSharpOutputService.generateOutputFile(headerInformation, rowEntryDtos, optionDto);
            case CSV -> csvOutputService.generateOutputFile(headerInformation, rowEntryDtos, optionDto);
            default -> throw new IllegalStateException("Not yet implemented: " + outputEnum);
        };
    }
}
