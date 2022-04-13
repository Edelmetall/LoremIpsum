package ch.zhaw.pm3.loremipsum.output.template.xml;

import ch.zhaw.pm3.loremipsum.common.EntryTypeEnum;
import ch.zhaw.pm3.loremipsum.common.HeaderInformation;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowEntryDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class XmlOutputServiceTest {

    private static final String EXPECTED_OUTPUT = """
            <?xml version="1.0" encoding="UTF-8"?>
            <data>
                <entry>
                    <firstName>Max</firstName>
                    <lastName>Beispiel</lastName>
                </entry>
                <entry>
                    <firstName>Andrea</firstName>
                    <lastName>Muster</lastName>
                </entry>
            </data>
            """;

    private XmlOutputService xmlOutputService;

    @BeforeEach
    public void setUp() {
        xmlOutputService = new XmlOutputService();
    }

    @Test
    public void testJsonOutputService() {
        List<HeaderInformation> headerInformations = new ArrayList<>();
        HeaderInformation headerInformation;

        headerInformation = new HeaderInformation("firstName", EntryTypeEnum.FIRST_NAME);
        headerInformations.add(headerInformation);

        headerInformation = new HeaderInformation("lastName", EntryTypeEnum.LAST_NAME);
        headerInformations.add(headerInformation);

        List<RowEntryDto> rowEntryDtos = new ArrayList<>();
        RowEntryDto rowEntryDto;

        rowEntryDto = new RowEntryDto();
        rowEntryDto.getEntryList().add("Max");
        rowEntryDto.getEntryList().add("Beispiel");
        rowEntryDtos.add(rowEntryDto);

        rowEntryDto = new RowEntryDto();
        rowEntryDto.getEntryList().add("Andrea");
        rowEntryDto.getEntryList().add("Muster");
        rowEntryDtos.add(rowEntryDto);

        Assertions.assertEquals(EXPECTED_OUTPUT,
                xmlOutputService.generateOutputFile(headerInformations, rowEntryDtos));
    }
}
