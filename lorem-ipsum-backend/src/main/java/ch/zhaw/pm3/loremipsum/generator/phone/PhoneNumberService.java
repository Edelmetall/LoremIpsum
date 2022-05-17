package ch.zhaw.pm3.loremipsum.generator.phone;

import ch.zhaw.pm3.loremipsum.common.CountryEnum;
import ch.zhaw.pm3.loremipsum.common.OptionEnum;
import ch.zhaw.pm3.loremipsum.common.TeleNrFormatEnum;
import ch.zhaw.pm3.loremipsum.generator.common.AbstractEntryGenService;
import ch.zhaw.pm3.loremipsum.generator.common.StringSolverUtil;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.OptionDto;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.RowTemplateDto;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PhoneNumberService extends AbstractEntryGenService {

    private static final Map<CountryEnum, PhoneNumber> PHONE_NUMBER_MAP = new HashMap<>();

    static {
        PHONE_NUMBER_MAP.put(CountryEnum.SWITZERLAND, new PhoneNumber("### ### ## ##", "+41 ## ### ## ##"));
        PHONE_NUMBER_MAP.put(CountryEnum.INDIA, new PhoneNumber("0091", "+91 ## ### ## ##"));
        PHONE_NUMBER_MAP.put(CountryEnum.UKRAINE, new PhoneNumber("00380", "+38 0# ### ## ##"));
    }

    @Override
    protected String getData(RowTemplateDto rowTemplateDto, Set<OptionDto> optionDtoSet) {


        Optional<OptionDto> phoneNumberFormat = optionDtoSet.stream()
                .filter(optionDto -> OptionEnum.PHONE_NUMER_FORMAT == optionDto.getOptionEnum()).findFirst();


        Optional<OptionDto> landCategoryOption = optionDtoSet.stream()
                .filter(optionDto -> OptionEnum.LAND_CD == optionDto.getOptionEnum()).findFirst();

        PhoneNumber phoneNumber;

        if (landCategoryOption.isPresent()) {
            phoneNumber = PHONE_NUMBER_MAP.get(CountryEnum.valueOf(landCategoryOption.get().getOptionData()));
        } else {
            phoneNumber = new ArrayList<>(PHONE_NUMBER_MAP.values()).get(new Random().nextInt(PHONE_NUMBER_MAP.values().size()));
        }


        if (phoneNumberFormat.isPresent() && phoneNumberFormat.get().getOptionData().equals(TeleNrFormatEnum.NATIONAL.name())) {
            return StringSolverUtil.resolve(phoneNumber.national());
        }

        return StringSolverUtil.resolve(phoneNumber.international());
    }
}
