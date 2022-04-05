package ch.zhaw.pm3.loremipsum.generator.service;

import ch.zhaw.pm3.loremipsum.generator.AbstractEntryGenService;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.EntryDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.OptionDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowTemplateDto;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FirstNameService extends AbstractEntryGenService {
    Faker faker = new Faker();

    @Override
    protected String getData(RowTemplateDto rowTemplateDto, Set<OptionDto> optionDtoSet) {
        return faker.name().firstName();
    }

}
