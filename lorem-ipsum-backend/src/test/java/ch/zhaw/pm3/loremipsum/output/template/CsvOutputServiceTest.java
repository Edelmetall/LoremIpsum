package ch.zhaw.pm3.loremipsum.output.template;

import ch.zhaw.pm3.loremipsum.AbstractSpringBootTest;
import ch.zhaw.pm3.loremipsum.common.EntryTypeEnum;
import ch.zhaw.pm3.loremipsum.common.HeaderInformation;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowEntryDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CsvOutputServiceTest extends AbstractSpringBootTest {

    CsvOutputService csvOutputService;

    String test =
            """
                    name;name2;date1;dateTime1
                    myName;myName2;2016-08-16;2007-12-03T10:15:30\s
                    """;

    String testCommaDelimiter =
            """
                    name,name2,date1,dateTime1
                    myName,myName2,2016-08-16,2007-12-03T10:15:30\s
                    """;

    @BeforeEach
    public void setUp() {
        csvOutputService = new CsvOutputService();}

    @Test
    // test naming pattern: [MethodUnderTest]_[Scenario]_[ExpectedResult]
    public void generateOutputFile_fourColumnOutput_outputCreated() {
        // arrange
        RowEntryDto dto = new RowEntryDto();
        dto.getEntryList().add("myName");
        dto.getEntryList().add("myName2");
        dto.getEntryList().add("2016-08-16");
        dto.getEntryList().add("2007-12-03T10:15:30");
        List<HeaderInformation> header = new ArrayList<>();
        header.add(new HeaderInformation("name", EntryTypeEnum.FIRST_NAME));
        header.add(new HeaderInformation("name2", EntryTypeEnum.LAST_NAME));
        header.add(new HeaderInformation("date1", EntryTypeEnum.DATE));
        header.add(new HeaderInformation("dateTime1", EntryTypeEnum.DATE_TIME));

        // act
        String output = csvOutputService.generateOutputFile(header, List.of(dto));

        //check print
        System.out.println("\n------");
        System.out.println("\n" + test);
        System.out.println("--- comparing with: ---");
        System.out.println("\n" + output);
        System.out.println("------");

        // assert
        Assertions.assertEquals(test, output);
    }

    @Test
    // test naming pattern: [MethodUnderTest]_[Scenario]_[ExpectedResult]
    public void generateOutputFile_changeOfDelimiter_outputCreated() {
        // arrange
        RowEntryDto dto = new RowEntryDto();
        dto.getEntryList().add("myName");
        dto.getEntryList().add("myName2");
        dto.getEntryList().add("2016-08-16");
        dto.getEntryList().add("2007-12-03T10:15:30");
        List<HeaderInformation> header = new ArrayList<>();
        header.add(new HeaderInformation("name", EntryTypeEnum.FIRST_NAME));
        header.add(new HeaderInformation("name2", EntryTypeEnum.LAST_NAME));
        header.add(new HeaderInformation("date1", EntryTypeEnum.DATE));
        header.add(new HeaderInformation("dateTime1", EntryTypeEnum.DATE_TIME));

        // act
        csvOutputService.setDelimiter(',');
        String output = csvOutputService.generateOutputFile(header, List.of(dto));

        //check print
        System.out.println("\n------");
        System.out.println("\n" + testCommaDelimiter);
        System.out.println("--- comparing with: ---");
        System.out.println("\n" + output);
        System.out.println("------");

        // assert
        Assertions.assertEquals(testCommaDelimiter, output);
    }
}
