package ch.zhaw.pm3.loremipsum.output.template.java;

import ch.zhaw.pm3.loremipsum.common.EntryTypeEnum;
import ch.zhaw.pm3.loremipsum.common.HeaderInformation;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowEntryDto;
import ch.zhaw.pm3.loremipsum.output.template.AbstractOutputService;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.*;

@Service
public class JavaOutputService extends AbstractOutputService {

    private final Template entityTemplate;

    public JavaOutputService() {
        super();
        entityTemplate = velocityEngine.getTemplate("template/javaClassTemplate.vm");
    }


    @Override
    protected String generateOutputFileIntern(List<HeaderInformation> headerInformation, List<RowEntryDto> rowEntryDtoSet) {
        VelocityContext context = new VelocityContext();
        List<JavaAttribute> javaAttributeList = new ArrayList<>();
        List<String> constructorInputList = new ArrayList<>();

        headerInformation.forEach(headerInfomation -> javaAttributeList.add(
                new JavaAttribute(headerInfomation.getName(), getJavaType(headerInfomation.getEntryTypeEnum()))));


        rowEntryDtoSet.forEach(rowEntryDto -> {
            StringBuilder builder = new StringBuilder();
            for (int indexEntry = 0; indexEntry < rowEntryDto.getEntryList().size(); indexEntry++) {
                builder.append(switch (headerInformation.get(indexEntry).getEntryTypeEnum()) {

                    // Need format 2016-08-16
                    case DATE -> "LocalDate.parse(\"" + rowEntryDto.getEntryList().get(indexEntry) + "\")";
                    // Need format 2007-12-03T10:15:30
                    case DATE_TIME -> "LocalDateTime.parse( \"" + rowEntryDto.getEntryList().get(indexEntry) + "\")";
                    default -> "\"" + rowEntryDto.getEntryList().get(indexEntry) + "\"";
                });

                if (indexEntry != javaAttributeList.size() - 1) {
                    builder.append(", ");
                }
            }
            constructorInputList.add(builder.toString());
        });


        context.put("packageName", "ch.example");
        context.put("constructorInputList", constructorInputList);
        context.put("importSet", getNeededImportSet(headerInformation));
        context.put("className", "Example");
        context.put("attributeList", javaAttributeList);


        StringWriter writer = new StringWriter();
        entityTemplate.merge(context, writer);
        return writer.toString();
    }


    private Set<String> getNeededImportSet(List<HeaderInformation> headerInformation) {
        Set<String> neededImportSet = new HashSet<>();
        neededImportSet.add("java.util.List");
        neededImportSet.add("java.util.Arrays");

        headerInformation.forEach(headerInfomation -> {
            switch (headerInfomation.getEntryTypeEnum()) {
                case DATE -> neededImportSet.add("java.time.LocalDate");
                case DATE_TIME -> neededImportSet.add("java.time.LocalDateTime");
                case TIME -> neededImportSet.add("java.time.LocalTime");
            }
        });
        return neededImportSet;
    }

    private String getJavaType(EntryTypeEnum entryTypeEnum) {
        return switch (entryTypeEnum) {
            case DATE -> "LocalDate";
            case DATE_TIME -> "LocalDateTime";
            case TIME -> "LocalTime";
            default -> "String";
        };
    }

}
