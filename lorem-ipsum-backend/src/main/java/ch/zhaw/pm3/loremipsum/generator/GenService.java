package ch.zhaw.pm3.loremipsum.generator;

import ch.zhaw.pm3.loremipsum.generator.ui.dto.EntryDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.GenDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowEntryDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowTemplateDto;
import com.github.javafaker.Faker;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;

@Service
public class GenService {


    public String generateStuff(GenDto genDto) {
        Faker faker = new Faker();
        int testDataSetSize = 10;
        final String resourceFolder = "src/main/resources/";

        final Template entityTemplate;
        VelocityEngine velocityEngine = new VelocityEngine(resourceFolder + "velocity.properties");
        velocityEngine.init();
        entityTemplate = velocityEngine.getTemplate("template/xmlTemplate.vm");
        VelocityContext context = new VelocityContext();


        Set<RowEntryDto> rowEntryDtos = new HashSet<>();


        for (int i = 0; rowEntryDtos.size() < testDataSetSize; i++) {
            RowEntryDto rowEntryDto = new RowEntryDto();

            for (RowTemplateDto rowTemplateDto : genDto.getTemplateDto().getRowTemplateDtoSet()) {
                EntryDto entryDto = new EntryDto();
                entryDto.setName(rowTemplateDto.getName().toLowerCase());
                entryDto.setData(faker.name().firstName());
                rowEntryDto.getEntryList().add(entryDto);
            }
            rowEntryDtos.add(rowEntryDto);
        }
        context.put("rowList", rowEntryDtos);


        StringWriter writer = new StringWriter();
        entityTemplate.merge(context, writer);
        System.out.println(writer.toString());
        return "Hello World" + genDto.getOutput();
    }


}
