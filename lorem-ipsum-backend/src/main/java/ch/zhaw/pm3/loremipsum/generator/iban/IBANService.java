package ch.zhaw.pm3.loremipsum.generator.iban;

import ch.zhaw.pm3.loremipsum.common.LandEnum;
import ch.zhaw.pm3.loremipsum.common.OptionEnum;
import ch.zhaw.pm3.loremipsum.generator.common.AbstractEntryGenService;
import ch.zhaw.pm3.loremipsum.generator.iban.middleware.*;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.OptionDto;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.RowTemplateDto;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class IBANService extends AbstractEntryGenService {

    private static Middleware<IBANWrapper> middleware;

    private static final Map<LandEnum, IBANWrapper> IBAN_PREFIX_MAP = new HashMap<>();

    static {
        // In Beta only ZKB Accounts
        IBAN_PREFIX_MAP.put(LandEnum.SWITZERLAND, new IBANWrapper("CHppbbbbb############", "00700", LandEnum.SWITZERLAND));
        // In Beta only Irsuuauk Accounts
        IBAN_PREFIX_MAP.put(LandEnum.UKRAINE, new IBANWrapper("UAppbbbbbb###################", "027321", LandEnum.UKRAINE));
    }

    public IBANService() {
        super(OptionEnum.LAND_CD);
        middleware = new ReplaceAccountNumberMiddleware().linkWith(new BankCodeMiddleware().linkWith(new IBANCheckSumMiddleware().linkWith(new FormatIBANMiddleware())));
    }

    @Override
    protected String getData(RowTemplateDto rowTemplateDto, Set<OptionDto> optionDtoSet) {

        Optional<OptionDto> landOption = optionDtoSet.stream()
                .filter(optionDto -> OptionEnum.LAND_CD == optionDto.getOptionEnum()).findFirst();


        if (landOption.isPresent()) {
            LandEnum landEnum = LandEnum.valueOf(landOption.get().getOptionData());

            return switch (landEnum) {
                case SWITZERLAND, UKRAINE ->
                        middleware.handle(IBAN_PREFIX_MAP.get(landEnum).format(), IBAN_PREFIX_MAP.get(landEnum));

                case INDIA -> ""; // India does not have included the IBAN Standard
            };
        }
        IBANWrapper random = new ArrayList<>(IBAN_PREFIX_MAP.values()).get(new Random().nextInt(IBAN_PREFIX_MAP.values().size()));
        return middleware.handle(random.format(), random);
    }


}
