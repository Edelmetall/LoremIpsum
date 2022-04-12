package ch.zhaw.pm3.loremipsum.output.template;

import ch.zhaw.pm3.loremipsum.common.HeaderInfomation;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.EntryDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowEntryDto;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class JsonOutputService extends AbstractOutputService {

    private final Template entityTemplate;

    public JsonOutputService() {
        super();
        entityTemplate = velocityEngine.getTemplate("template/jsonTemplate.vm");
    }

    @Override
    protected String generateOutputFileIntern(List<HeaderInfomation> headerInformation, Set<RowEntryDto> rowEntryDtoSet) {
        VelocityContext context = new VelocityContext();
        List<EntryDto> entryDtoList = new ArrayList<>();

        for (RowEntryDto rowEntryDto : rowEntryDtoSet) {

            for (int i = 0; i < rowEntryDto.getEntryList().size(); i++) {
                entryDtoList.add(new EntryDto(headerInformation.get(i).getName(), rowEntryDto.getEntryList().get(i)));
            }

        }

        context.put("rowList", entryDtoList);
        StringWriter writer = new StringWriter();
        entityTemplate.merge(context, writer);
        return writer.toString();
    }

}
