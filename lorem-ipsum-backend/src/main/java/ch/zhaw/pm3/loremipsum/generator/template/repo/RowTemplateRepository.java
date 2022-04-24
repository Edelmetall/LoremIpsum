package ch.zhaw.pm3.loremipsum.generator.template.repo;

import ch.zhaw.pm3.loremipsum.generator.template.data.RowTemplateEntity;
import org.springframework.data.repository.CrudRepository;

public interface RowTemplateRepository extends CrudRepository<RowTemplateEntity, Long> {
}
