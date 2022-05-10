package ch.zhaw.pm3.loremipsum.output.template.csharp;

import ch.zhaw.pm3.loremipsum.common.EntryTypeEnum;
import ch.zhaw.pm3.loremipsum.common.HeaderInformation;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.OptionDto;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.RowEntryDto;
import ch.zhaw.pm3.loremipsum.output.template.AbstractOutputService;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CSharpOutputService extends AbstractOutputService {

    private final Template entityTemplate;

    public CSharpOutputService() {
        super();
        entityTemplate = velocityEngine.getTemplate("template/cSharpClassTemplate.vm");
    }

    @Override
    protected String generateOutputFileIntern(List<HeaderInformation> headerInformation, List<RowEntryDto> rowEntryDtoSet, OptionDto optionDto) {
        VelocityContext context = new VelocityContext();
        List<CSharpAttribute> cSharpAttributeList = new ArrayList<>();
        List<String> constructorInputList = new ArrayList<>();

        headerInformation.forEach(element -> cSharpAttributeList.add(
                new CSharpAttribute(element.getName(), getCSharpType(element.getEntryTypeEnum()))
        ));

        rowEntryDtoSet.forEach(rowEntry -> {
            StringBuilder builder = new StringBuilder();
            for (int indexEntry = 0; indexEntry < rowEntry.getEntryList().size(); indexEntry++) {
                builder.append(switch (headerInformation.get(indexEntry).getEntryTypeEnum()) {
                    case DATE -> "DateOnly.Parse(\"" + rowEntry.getEntryList().get(indexEntry) + "\")";
                    case DATE_TIME -> "DateTime.Parse( \"" + rowEntry.getEntryList().get(indexEntry) + "\")";
                    default -> "\"" + rowEntry.getEntryList().get(indexEntry) + "\"";
                });

                if (indexEntry != cSharpAttributeList.size() - 1) {
                    builder.append(", ");
                }
            }
            constructorInputList.add(builder.toString());
        });

        context.put("constructorInputList", constructorInputList);
        context.put("importSet", getNeededImportSet());
        context.put("className", "Example");
        context.put("attributeList", cSharpAttributeList);

        StringWriter writer = new StringWriter();
        entityTemplate.merge(context, writer);
        return writer.toString();
    }

    private Set<String> getNeededImportSet() {
        Set<String> neededImportSet = new HashSet<>();
        neededImportSet.add("System.Collections.Generic");
        neededImportSet.add("System");

        return neededImportSet;
    }

    private String getCSharpType(EntryTypeEnum entryType) {
        return switch (entryType) {
            case DATE -> "DateOnly";
            case DATE_TIME -> "DateTime";
            case TIME -> "TimeOnly";
            default -> "String";
        };
    }
}
