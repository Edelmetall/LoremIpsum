package ch.zhaw.pm3.loremipsum;

import ch.zhaw.pm3.loremipsum.generator.EntryTypeEnum;
import ch.zhaw.pm3.loremipsum.generator.GenService;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.GenDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowTemplateDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.TemplateDto;
import ch.zhaw.pm3.loremipsum.output.OutputEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class LoremIpsumApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("Test Works");
    }

    @Autowired
    private GenService genService;

    @Test
    void setGenService() {
        GenDto genDto = new GenDto();

        genDto.setOutput(OutputEnum.XML.name());
        TemplateDto templateDto = new TemplateDto();

        templateDto.setName("MyName");
        templateDto.setId(1L);

        RowTemplateDto rowTemplateDto1 = new RowTemplateDto();
        rowTemplateDto1.setDataType(EntryTypeEnum.FIRST_NAME.getDisplayName());
        rowTemplateDto1.setName("FirstName");

        RowTemplateDto rowTemplateDto2 = new RowTemplateDto();
        rowTemplateDto2.setDataType(EntryTypeEnum.LAST_NAME.getDisplayName());
        rowTemplateDto2.setName("LastName");

        RowTemplateDto rowTemplateDto3 = new RowTemplateDto();
        rowTemplateDto3.setDataType(EntryTypeEnum.TELE_NR.getDisplayName());
        rowTemplateDto3.setName("MeineTelefonNr");


        templateDto.setRowTemplateDtoSet(Arrays.asList(rowTemplateDto1, rowTemplateDto2, rowTemplateDto3));
        genDto.setTemplateDto(templateDto);
        System.out.println(genService.generateStuff(genDto));
    }
}
