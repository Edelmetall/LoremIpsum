package ch.zhaw.pm3.loremipsum.generator.firstname;

import ch.zhaw.pm3.loremipsum.common.OptionEnum;
import ch.zhaw.pm3.loremipsum.generator.common.AbstractEntryGenService;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.OptionDto;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.RowTemplateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FirstNameService extends AbstractEntryGenService {


    @Autowired
    private FirstNameRepo firstNameRepo;

    public FirstNameService() {
        super(OptionEnum.LAND_CD, OptionEnum.GENDER);
    }

    @Override
    protected String getData(RowTemplateDto rowTemplateDto, Set<OptionDto> optionDtoSet) {

        Optional<OptionDto> landCategoryOption = optionDtoSet.stream()
                .filter(optionDto -> OptionEnum.LAND_CD == optionDto.getOptionEnum()).findFirst();

        Optional<OptionDto> genderOption = optionDtoSet.stream()
                .filter(optionDto -> OptionEnum.GENDER == optionDto.getOptionEnum()).findFirst();
        List<FirstNameEntity> firstNameEntityList;


        if (landCategoryOption.isPresent() && genderOption.isPresent()) {
            firstNameEntityList = firstNameRepo.findByLandCategoryAndGenderIn(
                    landCategoryOption.get().getOptionData(), List.of(GenderEnum.DIV.name(), genderOption.get().getOptionData()));
        } else if (landCategoryOption.isPresent()) {
            firstNameEntityList = firstNameRepo.findByLandCategory(landCategoryOption.get().getOptionData());
        } else if (genderOption.isPresent()) {
            firstNameEntityList = firstNameRepo.findByGenderIn(List.of(GenderEnum.DIV.name(), genderOption.get().getOptionData()));
        } else {
            firstNameEntityList = StreamSupport.stream(firstNameRepo.findAll().spliterator(), false)
                    .collect(Collectors.toList());
        }


        return firstNameEntityList.get(randomService.nextInt(firstNameEntityList.size())).getValue();
    }

}
