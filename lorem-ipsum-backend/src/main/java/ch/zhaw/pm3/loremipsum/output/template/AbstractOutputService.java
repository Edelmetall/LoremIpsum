package ch.zhaw.pm3.loremipsum.output.template;

import ch.zhaw.pm3.loremipsum.common.HeaderInfomation;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowEntryDto;
import org.apache.velocity.app.VelocityEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractOutputService {

    protected VelocityEngine velocityEngine;
    public AbstractOutputService() {
        velocityEngine = new VelocityEngine(Objects.requireNonNull(
                getClass().getClassLoader().getResource("velocity.properties")).getPath());
        velocityEngine.init();
    }


    protected abstract String generateOutputFileIntern(List<HeaderInfomation> headerInformation, Set<RowEntryDto> rowEntryDtoSet);

    public String generateOutputFile(List<HeaderInfomation> headerInformation, Set<RowEntryDto> rowEntryDtoSet) {
        List<RowEntryDto> rowEntryDtoList = new ArrayList<>(rowEntryDtoSet);
        for (int i = 0; i < rowEntryDtoSet.size(); i++) {
            if (headerInformation.size() != rowEntryDtoList.get(i).getEntryList().size()) {
                throw new RuntimeException("Row " + ++i + "contains " +
                        rowEntryDtoList.get(i).getEntryList().size() +
                        " DataNodes but there are only" + headerInformation.size() + " headerNodes");
            }
        }
        return generateOutputFileIntern(headerInformation, rowEntryDtoSet);
    }



}
