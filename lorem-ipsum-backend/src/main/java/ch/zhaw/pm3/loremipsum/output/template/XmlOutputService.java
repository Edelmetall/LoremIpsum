package ch.zhaw.pm3.loremipsum.output.template;

import ch.zhaw.pm3.loremipsum.common.EntryTypeEnum;
import ch.zhaw.pm3.loremipsum.common.HeaderInfomation;
import ch.zhaw.pm3.loremipsum.generator.data.DataTypeEnum;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowEntryDto;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.List;
import java.util.Set;

@Service
public class XmlOutputService extends AbstractOutputService {
    private final Template entityTemplate;

    public XmlOutputService() {
        super();
        entityTemplate = velocityEngine.getTemplate("template/xmlTemplate.vm");
    }


    @Override
    protected String generateOutputFileIntern(List<HeaderInfomation> headerInformation, Set<RowEntryDto> rowEntryDtoSet) {
        VelocityContext context = new VelocityContext();
        context.put("rowList", rowEntryDtoSet);
        StringWriter writer = new StringWriter();
        entityTemplate.merge(context, writer);
        return writer.toString();
    }
}
