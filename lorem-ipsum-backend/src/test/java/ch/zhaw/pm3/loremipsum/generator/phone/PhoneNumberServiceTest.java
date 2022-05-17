package ch.zhaw.pm3.loremipsum.generator.phone;

import ch.zhaw.pm3.loremipsum.AbstractSpringBootTest;
import ch.zhaw.pm3.loremipsum.common.CountryEnum;
import ch.zhaw.pm3.loremipsum.common.OptionEnum;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.OptionDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class PhoneNumberServiceTest extends AbstractSpringBootTest {

    @Autowired
    private PhoneNumberService phoneNumberService;


    public static Stream<OptionDto> getPossibleOptionDto() {
        List<OptionDto> optionDtos = new ArrayList<>();
        Arrays.stream(CountryEnum.values()).toList().
                forEach(landEnum -> optionDtos.add(new OptionDto(OptionEnum.LAND_CD, landEnum.name())));

        return optionDtos.stream();
    }

    @ParameterizedTest
    @MethodSource("getPossibleOptionDto")
    public void testAllCountries(OptionDto optionDto) {
        String testResult = phoneNumberService.getData(null, Set.of(optionDto));
        assertBasicTeleNr(testResult.toCharArray());
    }


    @Test
    public void testSwissInternationalNr() {
        String testResult = phoneNumberService.getData(null, Set.of(new OptionDto(OptionEnum.LAND_CD, CountryEnum.SWITZERLAND.name())));

        Assertions.assertTrue(testResult.startsWith("+41 "));
        assertBasicTeleNr(testResult.toCharArray());
    }


    private void assertBasicTeleNr(char[] chars) {
        Assertions.assertTrue(Character.isDigit(chars[4]));
        Assertions.assertTrue(Character.isDigit(chars[5]));
        Assertions.assertEquals(' ', chars[6]);
        Assertions.assertTrue(Character.isDigit(chars[7]));
        Assertions.assertTrue(Character.isDigit(chars[8]));
        Assertions.assertTrue(Character.isDigit(chars[9]));
        Assertions.assertEquals(' ', chars[10]);
        Assertions.assertTrue(Character.isDigit(chars[11]));
        Assertions.assertTrue(Character.isDigit(chars[12]));
        Assertions.assertEquals(' ', chars[13]);
        Assertions.assertTrue(Character.isDigit(chars[14]));
        Assertions.assertTrue(Character.isDigit(chars[15]));
    }
}
