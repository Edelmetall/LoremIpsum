package ch.zhaw.pm3.loremipsum.generator.iban;

import ch.zhaw.pm3.loremipsum.AbstractSpringBootTest;
import ch.zhaw.pm3.loremipsum.common.LandEnum;
import ch.zhaw.pm3.loremipsum.common.OptionEnum;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.OptionDto;
import org.apache.commons.validator.routines.checkdigit.IBANCheckDigit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class IBANServiceTest extends AbstractSpringBootTest {


    @Autowired
    private IBANService iBANService;

    @ParameterizedTest
    @EnumSource(LandEnum.class)
    public void testIBAN(LandEnum landEnum) {
        String iban = iBANService.getData(null, Set.of(new OptionDto(OptionEnum.LAND_CD, landEnum.name())));

        if (landEnum == LandEnum.INDIA) {
            Assertions.assertTrue(iban.isBlank());
        } else {
            Assertions.assertTrue(new IBANCheckDigit().isValid(iban.replace(" ", "")));
        }
    }




}
