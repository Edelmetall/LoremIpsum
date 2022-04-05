package ch.zhaw.pm3.loremipsum.output.template;

import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowEntryDto;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.Objects;
import java.util.Set;

@Service
public class XmlOutputService implements TemplateOutputService {

    private final Template entityTemplate;

    public XmlOutputService() {
        VelocityEngine velocityEngine = new VelocityEngine(Objects.requireNonNull(
                getClass().getClassLoader().getResource("velocity.properties")).getPath());
        velocityEngine.init();
        entityTemplate = velocityEngine.getTemplate("template/xmlTemplate.vm");
    }

    @Override
    public String generateOutputFile(Set<RowEntryDto> rowEntryDtoSet) {
        VelocityContext context = new VelocityContext();
        context.put("rowList", rowEntryDtoSet);
        StringWriter writer = new StringWriter();
        entityTemplate.merge(context, writer);
        return writer.toString();
    }
}
