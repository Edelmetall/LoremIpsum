package ch.zhaw.pm3.loremipsum.output.template.csharp;

import ch.zhaw.pm3.loremipsum.common.EntryTypeEnum;
import ch.zhaw.pm3.loremipsum.common.HeaderInformation;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.RowEntryDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CSharpOutputServiceTest {

    private static final String EXPECTED_OUTPUT = """
            using System.Collections.Generic;
            using System;
                        
            public class Example {
            
                public String FirstName
                { get; set; }
                public String LastName
                { get; set; }
            	
                public Example(String firstName, String lastName) {
                    FirstName = firstName;
                    LastName = lastName;
                }
            	
                public List<Example> getExampleList() {
                    return new List<Example>(new[] {
                        new Example("Max", "Muster"),
                        new Example("Mona", "Müller")});
                }
            }
            """;

    private CSharpOutputService cSharpOutputService;

    @BeforeEach
    public void setUp() {
        cSharpOutputService = new CSharpOutputService();
    }

    @Test
    public void testCSharpOutputService() {
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
        String actualOutput = this.cSharpOutputService.generateOutputFile(headerInformationList, rowEntries, null);

        // assert
        Assertions.assertEquals(EXPECTED_OUTPUT, actualOutput);
    }



}
