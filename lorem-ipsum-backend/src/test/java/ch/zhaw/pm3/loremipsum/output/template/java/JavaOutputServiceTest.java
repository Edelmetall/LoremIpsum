package ch.zhaw.pm3.loremipsum.output.template.java;

import ch.zhaw.pm3.loremipsum.AbstractSpringBootTest;
import ch.zhaw.pm3.loremipsum.common.EntryTypeEnum;
import ch.zhaw.pm3.loremipsum.common.HeaderInformation;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowEntryDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class JavaOutputServiceTest extends AbstractSpringBootTest {

    JavaOutputService javaOutputService;

    public static final String EXPECTED_OUTPUT =
            """
                    package ch.example;
                                        
                    import java.util.List;
                    import java.util.Arrays;
                    import java.time.LocalDate;
                    import java.time.LocalDateTime;
                                        
                    public class Example {
                                        
                        private String name;
                        private String name2;
                        private LocalDate date1;
                        private LocalDateTime dateTime1;
                                        
                        public Example(String name, String name2, LocalDate date1, LocalDateTime dateTime1) {
                            this.name = name;
                            this.name2 = name2;
                            this.date1 = date1;
                            this.dateTime1 = dateTime1;
                        }
                                        
                        /**
                         * Getter and Setter for Attributes
                         */
                                        
                        public String getName() {
                            return name;
                        }
                                        
                        public Example setName(String name) {
                            this.name = name;
                            return this;
                        }
                                        
                        public String getName2() {
                            return name2;
                        }
                                        
                        public Example setName2(String name2) {
                            this.name2 = name2;
                            return this;
                        }
                                        
                        public LocalDate getDate1() {
                            return date1;
                        }
                                        
                        public Example setDate1(LocalDate date1) {
                            this.date1 = date1;
                            return this;
                        }
                                        
                        public LocalDateTime getDateTime1() {
                            return dateTime1;
                        }
                                        
                        public Example setDateTime1(LocalDateTime dateTime1) {
                            this.dateTime1 = dateTime1;
                            return this;
                        }
                                        
                        public List<Example> getExampleList() {
                            return Arrays.asList(
                                new Example("myName", "myName2", LocalDate.parse("2016-08-16"), LocalDateTime.parse( "2007-12-03T10:15:30")));
                        }
                                        
                    }
                    """;

    @BeforeEach
    public void setUp() {
        javaOutputService = new JavaOutputService();
    }

    @Test
    // test naming pattern: [MethodUnderTest]_[Scenario]_[ExpectedResult]
    public void generateOutputFile_fourColumnOuptut_outputCreated() {
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
        String output = javaOutputService.generateOutputFile(header, List.of(dto));

        // assert
        Assertions.assertEquals(EXPECTED_OUTPUT, output);
    }
}
