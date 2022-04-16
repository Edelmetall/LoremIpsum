package ch.zhaw.pm3.loremipsum.generator.repo;

import ch.zhaw.pm3.loremipsum.generator.data.DataFormatEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface DataFormatRepository extends CrudRepository<DataFormatEntity, Long> {
    DataFormatEntity findByName(String name);
    Collection<DataFormatEntity> findByActive(boolean active);
}
