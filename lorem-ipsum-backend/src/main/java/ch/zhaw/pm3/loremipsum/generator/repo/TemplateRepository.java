package ch.zhaw.pm3.loremipsum.generator.repo;

import ch.zhaw.pm3.loremipsum.generator.data.TemplateEntity;
import org.springframework.data.repository.CrudRepository;

public interface TemplateRepository extends CrudRepository<TemplateEntity, Long> {
}
