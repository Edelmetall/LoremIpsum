package ch.zhaw.pm3.loremipsum.generator.service;

import ch.zhaw.pm3.loremipsum.generator.AbstractEntryGenService;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.EntryDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.OptionDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowTemplateDto;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TeleNrService extends AbstractEntryGenService {
    @Override
    protected String getData(RowTemplateDto rowTemplateDto, Set<OptionDto> optionDtoSet) {
        return "+41 32 765 43 21";
    }
}
