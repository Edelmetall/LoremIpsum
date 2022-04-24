package ch.zhaw.pm3.loremipsum.generator.template.repo;

import ch.zhaw.pm3.loremipsum.generator.template.data.TemplateEntity;
import org.springframework.data.repository.CrudRepository;

public interface TemplateRepository extends CrudRepository<TemplateEntity, Long> {
}
