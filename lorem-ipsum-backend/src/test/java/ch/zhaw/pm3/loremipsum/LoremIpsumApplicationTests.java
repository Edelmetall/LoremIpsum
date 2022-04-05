package ch.zhaw.pm3.loremipsum;

import ch.zhaw.pm3.loremipsum.generator.GenService;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.GenDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowTemplateDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.TemplateDto;
import com.github.javafaker.HarryPotter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Set;

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

        genDto.setOutput("XML");
        TemplateDto templateDto = new TemplateDto();

        templateDto.setName("MyName");
        templateDto.setId(1L);

        RowTemplateDto rowTemplateDto1 = new RowTemplateDto();
        rowTemplateDto1.setName("FirstName");

        RowTemplateDto rowTemplateDto2 = new RowTemplateDto();
        rowTemplateDto2.setName("LastName");


        templateDto.setRowTemplateDtoSet(Set.of(rowTemplateDto1, rowTemplateDto2));
        genDto.setTemplateDto(templateDto);
        genService.generateStuff(genDto);
    }
}
