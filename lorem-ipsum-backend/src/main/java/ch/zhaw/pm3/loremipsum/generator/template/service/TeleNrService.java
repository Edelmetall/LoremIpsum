package ch.zhaw.pm3.loremipsum.generator.template.service;

import ch.zhaw.pm3.loremipsum.generator.common.AbstractEntryGenService;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.OptionDto;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.RowTemplateDto;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TeleNrService extends AbstractEntryGenService {
    @Override
    protected String getData(RowTemplateDto rowTemplateDto, Set<OptionDto> optionDtoSet) {
        return "+41 32 765 43 21";
    }
}
