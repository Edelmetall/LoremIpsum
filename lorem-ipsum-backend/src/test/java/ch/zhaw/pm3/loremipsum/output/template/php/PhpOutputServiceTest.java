package ch.zhaw.pm3.loremipsum.output.template.php;

import ch.zhaw.pm3.loremipsum.common.EntryTypeEnum;
import ch.zhaw.pm3.loremipsum.common.HeaderInfomation;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowEntryDto;
import ch.zhaw.pm3.loremipsum.output.template.json.JsonOutputService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PhpOutputServiceTest {

    private static final String EXPECTED_OUTPUT = """
            <?php
                        
            $data = [
                [
                    "firstName" => "Max",
                    "lastName" => "Beispiel"
                ],
                [
                    "firstName" => "Andrea",
                    "lastName" => "Muster"
                ]
            ];
                        
            ?>
            """;

    private PhpOutputService phpOutputService;

    @BeforeEach
    public void setUp() {
        phpOutputService = new PhpOutputService();
    }

    @Test
    public void testJsonOutputService() {
        List<HeaderInfomation> headerInfomations = new ArrayList<>();
        HeaderInfomation headerInfomation;

        headerInfomation = new HeaderInfomation("firstName", EntryTypeEnum.FIRST_NAME);
        headerInfomations.add(headerInfomation);

        headerInfomation = new HeaderInfomation("lastName", EntryTypeEnum.LAST_NAME);
        headerInfomations.add(headerInfomation);

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
                phpOutputService.generateOutputFile(headerInfomations, rowEntryDtos));
    }
}
