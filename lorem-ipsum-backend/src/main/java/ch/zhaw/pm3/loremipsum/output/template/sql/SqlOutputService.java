package ch.zhaw.pm3.loremipsum.output.template.sql;

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
 * Output service for SQL files
 */
@Service
public class SqlOutputService extends AbstractOutputService {

    private final Template entityTemplate;

    public SqlOutputService() {
        super();
        entityTemplate = velocityEngine.getTemplate("template/sqlTemplate.vm");
    }

    @Override
    protected String generateOutputFileIntern(List<HeaderInformation> headerInformation, List<RowEntryDto> rowEntryDtoSet, OptionDto optionDto) {
        VelocityContext context = new VelocityContext();
        context.put("entryTypes", headerInformation);
        context.put("data", convertToSingleList(headerInformation, rowEntryDtoSet));
        StringWriter writer = new StringWriter();
        entityTemplate.merge(context, writer);
        return writer.toString();
    }
}
