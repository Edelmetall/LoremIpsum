package ch.zhaw.pm3.loremipsum.generator.ui;

import ch.zhaw.pm3.loremipsum.generator.data.TemplateEntity;
import ch.zhaw.pm3.loremipsum.generator.repo.TemplateRepository;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.TemplateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.stream.StreamSupport;

@RestController
public class TemplateController {

    private final TemplateRepository templateRepository;

    public TemplateController(@Autowired TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Transactional
    @GetMapping("/api/template/")
    public Collection<TemplateDto> getAllTemplate() {
        return StreamSupport.stream(templateRepository.findAll().spliterator(), false)
                .map(templateEntity -> new TemplateDto().mapFrom(templateEntity)).toList();
    }

    @GetMapping("/api/template/add")
    @Transactional
    public TemplateEntity add() {
        TemplateEntity templateEntity = new TemplateEntity("Test");
        return templateRepository.save(templateEntity);
    }

}
