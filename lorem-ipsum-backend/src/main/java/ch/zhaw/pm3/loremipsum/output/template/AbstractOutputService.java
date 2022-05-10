package ch.zhaw.pm3.loremipsum.output.template;

import ch.zhaw.pm3.loremipsum.common.HeaderInformation;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.OptionDto;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.RowEntryDto;
import org.apache.velocity.app.VelocityEngine;

import java.util.*;

public abstract class AbstractOutputService {

    protected VelocityEngine velocityEngine;

    public AbstractOutputService() {
        velocityEngine = new VelocityEngine(Objects.requireNonNull(
                getClass().getClassLoader().getResource("velocity.properties")).getPath());
        velocityEngine.init();
    }

    protected abstract String generateOutputFileIntern(List<HeaderInformation> headerInformation, List<RowEntryDto> rowEntryDtoSet, OptionDto optionDto);

    public String generateOutputFile(List<HeaderInformation> headerInformation, List<RowEntryDto> rowEntryDtoSet, OptionDto optionDto) {
        List<RowEntryDto> rowEntryDtoList = new ArrayList<>(rowEntryDtoSet);
        for (int i = 0; i < rowEntryDtoSet.size(); i++) {
            if (headerInformation.size() != rowEntryDtoList.get(i).getEntryList().size()) {
                throw new RuntimeException("Row " + ++i + "contains " +
                        rowEntryDtoList.get(i).getEntryList().size() +
                        " DataNodes but there are only" + headerInformation.size() + " headerNodes");
            }
        }
        return generateOutputFileIntern(headerInformation, rowEntryDtoSet, optionDto);
    }

    /**
     * Creates a list where each entry is represented as a Map with all the values for each attribute
     *
     * @param headerInformation contains the key values
     * @param rowEntryDtoSet    contains the data
     * @return list where each entry is represented as a Map
     */
    protected List<Map<String, String>> convertToSingleList(List<HeaderInformation> headerInformation, List<RowEntryDto> rowEntryDtoSet) {
        List<Map<String, String>> data = new ArrayList<>();
        for (RowEntryDto rowEntryDto : rowEntryDtoSet) {
            Map<String, String> values = new HashMap<>();
            for (int i = 0; i < rowEntryDto.getEntryList().size(); i++) {
                values.put(headerInformation.get(i).getName(), rowEntryDto.getEntryList().get(i));
            }
            data.add(values);
        }
        return data;
    }
}
