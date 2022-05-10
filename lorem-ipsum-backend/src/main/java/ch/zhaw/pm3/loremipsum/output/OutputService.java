package ch.zhaw.pm3.loremipsum.output;

import ch.zhaw.pm3.loremipsum.common.HeaderInformation;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.OptionDto;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.RowEntryDto;
import ch.zhaw.pm3.loremipsum.output.template.CsvOutputService;
import ch.zhaw.pm3.loremipsum.output.template.csharp.CSharpOutputService;
import ch.zhaw.pm3.loremipsum.output.template.json.JsonOutputService;
import ch.zhaw.pm3.loremipsum.output.template.php.PhpOutputService;
import ch.zhaw.pm3.loremipsum.output.template.sql.SqlOutputService;
import ch.zhaw.pm3.loremipsum.output.template.xml.XmlOutputService;
import ch.zhaw.pm3.loremipsum.output.template.java.JavaOutputService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OutputService {

    private final XmlOutputService xmlOutputService;
    private final JsonOutputService jsonOutputService;
    private final JavaOutputService javaOutputService;
    private final SqlOutputService sqlOutputService;
    private final PhpOutputService phpOutputService;
    private final CSharpOutputService cSharpOutputService;
    private final CsvOutputService csvOutputService;


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
