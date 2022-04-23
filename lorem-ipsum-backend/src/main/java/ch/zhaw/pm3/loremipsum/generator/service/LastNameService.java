package ch.zhaw.pm3.loremipsum.generator.service;

import ch.zhaw.pm3.loremipsum.generator.ui.dto.OptionDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowConfigurationDto;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class LastNameService extends AbstractEntryGenService {
    Faker faker = new Faker();

    @Override
    protected String getData(RowConfigurationDto rowConfigurationDto, Set<OptionDto> optionDtoSet) {
        return faker.name().lastName();
    }
}
