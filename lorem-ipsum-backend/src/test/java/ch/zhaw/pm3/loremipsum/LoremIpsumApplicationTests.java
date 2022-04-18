package ch.zhaw.pm3.loremipsum;

import ch.zhaw.pm3.loremipsum.common.EntryTypeEnum;
import ch.zhaw.pm3.loremipsum.generator.GenService;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.GenDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowConfigurationDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.TemplateDto;
import ch.zhaw.pm3.loremipsum.output.OutputEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

class LoremIpsumApplicationTests extends AbstractSpringBootTest {

    @Test
    void contextLoads() {
        System.out.println("Test Works");
    }

    @Autowired
    private GenService genService;

    @Test
    void setGenService() {
        GenDto genDto = new GenDto();

        genDto.setOutputName(OutputEnum.XML.name());
        TemplateDto templateDto = new TemplateDto();

        templateDto.setName("MyName");
        templateDto.setId(1L);

        RowConfigurationDto rowConfigurationDto1 = new RowConfigurationDto();
        rowConfigurationDto1.setDataType(EntryTypeEnum.FIRST_NAME.getDisplayName());
        rowConfigurationDto1.setName("FirstName");

        RowConfigurationDto rowConfigurationDto2 = new RowConfigurationDto();
        rowConfigurationDto2.setDataType(EntryTypeEnum.LAST_NAME.getDisplayName());
        rowConfigurationDto2.setName("LastName");


        templateDto.setRowConfigurationDtoSet(Arrays.asList(rowConfigurationDto1, rowConfigurationDto2));
        genDto.setTemplateDto(templateDto);
        System.out.println(genService.generateData(genDto));
    }
}
