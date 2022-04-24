package ch.zhaw.pm3.loremipsum.output.template.json;

import ch.zhaw.pm3.loremipsum.common.EntryTypeEnum;
import ch.zhaw.pm3.loremipsum.common.HeaderInformation;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.RowEntryDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonOutputServiceTest {

    private static final String EXPECTED_OUTPUT = """
            {
                "data": {
                    "entry": {
                        "firstName": "Max",
                        "lastName": "Beispiel"
                    },
                    "entry": {
                        "firstName": "Andrea",
                        "lastName": "Muster"
                    }
                }
            }
            """;

    private JsonOutputService jsonOutputService;

    @BeforeEach
    public void setUp() {
        jsonOutputService = new JsonOutputService();
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
                jsonOutputService.generateOutputFile(headerInformations, rowEntryDtos));
    }
}
