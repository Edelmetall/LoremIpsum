package ch.zhaw.pm3.loremipsum.generator.template.repo;

import ch.zhaw.pm3.loremipsum.generator.template.data.DataFormatEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface DataFormatRepository extends CrudRepository<DataFormatEntity, Long> {
    DataFormatEntity findByName(String name);
    Collection<DataFormatEntity> findByActive(boolean active);
}
