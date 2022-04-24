package ch.zhaw.pm3.loremipsum;

import ch.zhaw.pm3.loremipsum.common.EntryTypeEnum;
import ch.zhaw.pm3.loremipsum.common.LandEnum;
import ch.zhaw.pm3.loremipsum.common.OptionEnum;
import ch.zhaw.pm3.loremipsum.generator.GenService;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.GenDto;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.OptionDto;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.RowTemplateDto;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.TemplateDto;
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

        RowTemplateDto rowTemplateDto1 = new RowTemplateDto();
        rowTemplateDto1.setDataType(EntryTypeEnum.FIRST_NAME.getDisplayName());
        rowTemplateDto1.setName("FirstName");
        rowTemplateDto1.getOption().add(new OptionDto(OptionEnum.LAND_CD, LandEnum.SWITZERLAND.name()));
        RowTemplateDto rowTemplateDto2 = new RowTemplateDto();
        rowTemplateDto2.setDataType(EntryTypeEnum.LAST_NAME.getDisplayName());
        rowTemplateDto2.setName("LastName");


        templateDto.setRowTemplateDtoSet(Arrays.asList(rowTemplateDto1, rowTemplateDto2));
        genDto.setTemplateDto(templateDto);
        System.out.println(genService.generateData(genDto));
    }
}
