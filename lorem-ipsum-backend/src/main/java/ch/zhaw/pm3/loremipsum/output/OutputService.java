package ch.zhaw.pm3.loremipsum.output;

import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowEntryDto;
import ch.zhaw.pm3.loremipsum.output.template.JsonOutputService;
import ch.zhaw.pm3.loremipsum.output.template.XmlOutputService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class OutputService {

    private final XmlOutputService xmlOutputService;
    private final JsonOutputService jsonOutputService;

    public String generateModel(OutputEnum outputEnum, Set<RowEntryDto> rowEntryDtos) {
        return switch (outputEnum) {
            case XML -> xmlOutputService.generateOutputFile(rowEntryDtos);
            case JSON -> jsonOutputService.generateOutputFile(rowEntryDtos);
            default -> throw new IllegalStateException("Not yet implemented: " + outputEnum);
        };
    }
}
