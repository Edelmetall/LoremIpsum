package ch.zhaw.pm3.loremipsum.generator.telenr;

import ch.zhaw.pm3.loremipsum.common.LandEnum;
import ch.zhaw.pm3.loremipsum.common.OptionEnum;
import ch.zhaw.pm3.loremipsum.common.TeleNrFormatEnum;
import ch.zhaw.pm3.loremipsum.generator.common.AbstractEntryGenService;
import ch.zhaw.pm3.loremipsum.generator.common.StringSolverUtil;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.OptionDto;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.RowTemplateDto;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeleNrService extends AbstractEntryGenService {


    public TeleNrService(){
        super(OptionEnum.LAND_CD, OptionEnum.TELE_NR_FORMAT);
    }

    private final static Map<LandEnum, PhoneNumber> PHONE_NUMBER_MAP = new HashMap<>();

    static {
        PHONE_NUMBER_MAP.put(LandEnum.SWITZERLAND, new PhoneNumber("### ### ## ##", "+41 ## ### ## ##"));
        PHONE_NUMBER_MAP.put(LandEnum.INDIA, new PhoneNumber("0091", "+91 ## ### ## ##"));
    }

    @Override
    protected String getData(RowTemplateDto rowTemplateDto, Set<OptionDto> optionDtoSet) {


        Optional<OptionDto> teleNrFormat = optionDtoSet.stream()
                .filter(optionDto -> OptionEnum.TELE_NR_FORMAT == optionDto.getOptionEnum()).findFirst();


        Optional<OptionDto> landCategoryOption = optionDtoSet.stream()
                .filter(optionDto -> OptionEnum.LAND_CD == optionDto.getOptionEnum()).findFirst();

        PhoneNumber phoneNumber;

        if (landCategoryOption.isPresent()) {
            phoneNumber = PHONE_NUMBER_MAP.get(LandEnum.valueOf(landCategoryOption.get().getOptionData()));
        } else {
            phoneNumber = new ArrayList<>(PHONE_NUMBER_MAP.values()).get(new Random().nextInt(PHONE_NUMBER_MAP.values().size()));
        }


        if (teleNrFormat.isPresent() && teleNrFormat.get().getOptionData().equals(TeleNrFormatEnum.NATIONAL.name())) {
            return StringSolverUtil.resolve(phoneNumber.national());
        }

        return StringSolverUtil.resolve(phoneNumber.international());
    }
}
