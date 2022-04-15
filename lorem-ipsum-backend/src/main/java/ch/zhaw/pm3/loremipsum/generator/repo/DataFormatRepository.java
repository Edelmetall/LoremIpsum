package ch.zhaw.pm3.loremipsum.generator.repo;

import ch.zhaw.pm3.loremipsum.generator.data.DataFormatEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DataFormatRepository extends CrudRepository<DataFormatEntity, Long> {
    @Query(value = "SELECT data FROM DATA_FORMAT data where data.name = :name")
    DataFormatEntity findByName(@Param("name") String name);
}
