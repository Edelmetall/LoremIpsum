package ch.zhaw.pm3.loremipsum.generator.service;

import ch.zhaw.pm3.loremipsum.generator.ui.dto.OptionDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowConfigurationDto;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TeleNrService extends AbstractEntryGenService {
    @Override
    protected String getData(RowConfigurationDto rowConfigurationDto, Set<OptionDto> optionDtoSet) {
        return "+41 32 765 43 21";
    }
}
