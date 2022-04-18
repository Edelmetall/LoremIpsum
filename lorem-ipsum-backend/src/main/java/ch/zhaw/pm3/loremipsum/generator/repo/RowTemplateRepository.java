package ch.zhaw.pm3.loremipsum.generator.repo;

import ch.zhaw.pm3.loremipsum.generator.data.RowConfigurationEntity;
import org.springframework.data.repository.CrudRepository;

public interface RowTemplateRepository extends CrudRepository<RowConfigurationEntity, Long> {
}
