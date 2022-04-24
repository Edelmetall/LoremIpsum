package ch.zhaw.pm3.loremipsum.output.template.sql;

import ch.zhaw.pm3.loremipsum.common.EntryTypeEnum;
import ch.zhaw.pm3.loremipsum.common.HeaderInformation;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.RowEntryDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SqlOutputServiceTest {

    private static final String EXPECTED_OUTPUT = """
            DROP TABLE IF EXISTS `myTable`;
                        
            CREATE TABLE `myTable` (
                `id` mediumint(8) unsigned NOT NULL auto_increment,
                `firstName` varchar(255) default NULL,
                `lastName` varchar(255) default NULL,
                PRIMARY KEY (`id`)
            ) AUTO_INCREMENT=1;
                        
            INSERT INTO `myTable` (`firstName`,`lastName`)
            VALUES
                ("Max","Muster"),
                ("Mona","Müller")
            """;

    private SqlOutputService sqlOutputService;

    @BeforeEach
    public void setUp() {
        sqlOutputService = new SqlOutputService();
    }

    @Test
    public void testSqlOutputService() {
        // setup
        List<HeaderInformation> headerInformationList = new ArrayList<>();
        headerInformationList.add(new HeaderInformation("firstName", EntryTypeEnum.FIRST_NAME));
        headerInformationList.add(new HeaderInformation("lastName", EntryTypeEnum.LAST_NAME));

        List<RowEntryDto> rowEntries = new ArrayList<>();
        RowEntryDto rowEntryDto;

        rowEntryDto = new RowEntryDto();
        rowEntryDto.getEntryList().add("Max");
        rowEntryDto.getEntryList().add("Muster");
        rowEntries.add(rowEntryDto);

        rowEntryDto = new RowEntryDto();
        rowEntryDto.getEntryList().add("Mona");
        rowEntryDto.getEntryList().add("Müller");
        rowEntries.add(rowEntryDto);

        // act
        String actualOutput = this.sqlOutputService.generateOutputFile(headerInformationList, rowEntries);

        // assert
        Assertions.assertEquals(EXPECTED_OUTPUT, actualOutput);
    }
}
