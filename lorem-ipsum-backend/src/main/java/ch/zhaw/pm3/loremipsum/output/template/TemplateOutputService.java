package ch.zhaw.pm3.loremipsum.output.template;

import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowEntryDto;

import java.util.Set;

public interface TemplateOutputService {

    String generateOutputFile(Set<RowEntryDto> rowEntryDtoSet);

}
