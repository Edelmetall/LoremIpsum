package ch.zhaw.pm3.loremipsum.output.template.sql;

import ch.zhaw.pm3.loremipsum.common.HeaderInfomation;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowEntryDto;
import ch.zhaw.pm3.loremipsum.output.template.AbstractOutputService;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.List;

@Service
public class SqlOutputService extends AbstractOutputService {

    private final Template entityTemplate;

    public SqlOutputService() {
        super();
        entityTemplate = velocityEngine.getTemplate("template/sqlTemplate.vm");
    }

    @Override
    protected String generateOutputFileIntern(List<HeaderInfomation> headerInformation, List<RowEntryDto> rowEntryDtoSet) {
        VelocityContext context = new VelocityContext();
        context.put("entryTypes", headerInformation);
        context.put("data", convertToSingleList(headerInformation, rowEntryDtoSet));
        StringWriter writer = new StringWriter();
        entityTemplate.merge(context, writer);
        return writer.toString();
    }
}
