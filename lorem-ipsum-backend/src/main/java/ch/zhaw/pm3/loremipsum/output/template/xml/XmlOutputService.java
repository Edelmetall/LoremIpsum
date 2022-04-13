package ch.zhaw.pm3.loremipsum.output.template.xml;

import ch.zhaw.pm3.loremipsum.common.HeaderInformation;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowEntryDto;
import ch.zhaw.pm3.loremipsum.output.template.AbstractOutputService;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.List;

@Service
public class XmlOutputService extends AbstractOutputService {
    private final Template entityTemplate;

    public XmlOutputService() {
        super();
        entityTemplate = velocityEngine.getTemplate("template/xmlTemplate.vm");
    }


    @Override
    protected String generateOutputFileIntern(List<HeaderInformation> headerInformation, List<RowEntryDto> rowEntryDtoSet) {
        VelocityContext context = new VelocityContext();
        context.put("data", convertToSingleList(headerInformation, rowEntryDtoSet));
        StringWriter writer = new StringWriter();
        entityTemplate.merge(context, writer);
        return writer.toString();
    }
}
