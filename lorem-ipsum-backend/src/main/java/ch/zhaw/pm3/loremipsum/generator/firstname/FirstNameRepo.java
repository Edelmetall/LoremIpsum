package ch.zhaw.pm3.loremipsum.generator.firstname;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FirstNameRepo extends CrudRepository<FirstNameEntity, Long> {

    List<FirstNameEntity> findByLandCategory(String landCategory);
    List<FirstNameEntity> findByLandCategoryAndGenderIn(String landCategory, List<String> gender);
    List<FirstNameEntity> findByGenderIn(List<String> gender);

}
