package ch.zhaw.pm3.loremipsum.output.template.json;

import ch.zhaw.pm3.loremipsum.common.HeaderInformation;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.OptionDto;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.RowEntryDto;
import ch.zhaw.pm3.loremipsum.output.template.AbstractOutputService;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.List;

/**
 * output service for JSON files
 */
@Service
public class JsonOutputService extends AbstractOutputService {

    private final Template entityTemplate;

    public JsonOutputService() {
        super();
        entityTemplate = velocityEngine.getTemplate("template/jsonTemplate.vm");
    }

    @Override
    protected String generateOutputFileIntern(List<HeaderInformation> headerInformation, List<RowEntryDto> rowEntryDtoSet, OptionDto optionDto) {
        VelocityContext context = new VelocityContext();
        context.put("data", convertToSingleList(headerInformation, rowEntryDtoSet));
        StringWriter writer = new StringWriter();
        entityTemplate.merge(context, writer);
        return writer.toString();
    }
}
