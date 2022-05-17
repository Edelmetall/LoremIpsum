package ch.zhaw.pm3.loremipsum.generator.template.service;

import ch.zhaw.pm3.loremipsum.generator.common.AbstractEntryGenService;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.RowTemplateDto;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.OptionDto;
import com.github.javafaker.Faker;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LastNameService extends AbstractEntryGenService {
    Faker faker = new Faker();

    @Override
    protected String getData(RowTemplateDto rowTemplateDto, Set<OptionDto> optionDtoSet) {
        return faker.name().lastName();
    }
}
