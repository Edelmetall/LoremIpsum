package ch.zhaw.pm3.loremipsum.output.template;

import ch.zhaw.pm3.loremipsum.common.HeaderInformation;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.OptionDto;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.RowEntryDto;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class CsvOutputService extends AbstractOutputService {
    private char delimiter = ';';

    public CsvOutputService() {
        super();
    }

    @Override
    protected String generateOutputFileIntern(List<HeaderInformation> headerInformation, List<RowEntryDto> rowEntryDtoSet, OptionDto optionDto) {
        StringBuilder output = new StringBuilder();
        for (Iterator<HeaderInformation> it = headerInformation.iterator(); it.hasNext(); ) {
            output.append(it.next().getName());
            if (it.hasNext()) {
                output.append(delimiter);
            }
        }
        output.append("\n");
        for (RowEntryDto row : rowEntryDtoSet) {
            for (Iterator<String> it = row.getEntryList().iterator(); it.hasNext(); ) {
                output.append(it.next());
                if (it.hasNext()) {
                    output.append(delimiter);
                }
            }
            output.append(" \n");
        }
        return output.toString();
    }

    public CsvOutputService(char delimiter) {
        super();
        this.delimiter = delimiter;
    }

    // if delimiter is being changed during runtime
    public void setDelimiter(char newDelimiter) {
        this.delimiter = newDelimiter;
    }
}

