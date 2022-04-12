package ch.zhaw.pm3.loremipsum.output;

import ch.zhaw.pm3.loremipsum.common.EntryTypeEnum;
import ch.zhaw.pm3.loremipsum.common.HeaderInfomation;
import ch.zhaw.pm3.loremipsum.generator.data.DataTypeEnum;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowEntryDto;
import ch.zhaw.pm3.loremipsum.output.template.JsonOutputService;
import ch.zhaw.pm3.loremipsum.output.template.XmlOutputService;
import ch.zhaw.pm3.loremipsum.output.template.java.JavaOutputService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OutputService {

    private final XmlOutputService xmlOutputService;
    private final JsonOutputService jsonOutputService;
    private final JavaOutputService javaOutputService;

    public String generateModel(OutputEnum outputEnum, List<HeaderInfomation> headerInformation, Set<RowEntryDto> rowEntryDtos) {
        return switch (outputEnum) {
            case XML -> xmlOutputService.generateOutputFile(headerInformation, rowEntryDtos);
            case JAVA -> javaOutputService.generateOutputFile(headerInformation, rowEntryDtos);
            case JSON -> jsonOutputService.generateOutputFile(headerInformation, rowEntryDtos);
            default -> throw new IllegalStateException("Not yet implemented: " + outputEnum);
        };
    }
}
