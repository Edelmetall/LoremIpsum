package ch.zhaw.pm3.loremipsum.output;

import ch.zhaw.pm3.loremipsum.common.HeaderInfomation;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowEntryDto;
import ch.zhaw.pm3.loremipsum.output.template.json.JsonOutputService;
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

    public String generateModel(OutputEnum outputEnum, List<HeaderInfomation> headerInformation, List<RowEntryDto> rowEntryDtos) {
        return switch (outputEnum) {
            case XML -> xmlOutputService.generateOutputFile(headerInformation, rowEntryDtos);
            case JAVA -> javaOutputService.generateOutputFile(headerInformation, rowEntryDtos);
            case JSON -> jsonOutputService.generateOutputFile(headerInformation, rowEntryDtos);
            default -> throw new IllegalStateException("Not yet implemented: " + outputEnum);
        };
    }
}
