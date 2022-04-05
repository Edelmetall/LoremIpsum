package ch.zhaw.pm3.loremipsum.generator.ui;

import ch.zhaw.pm3.loremipsum.customer.data.CustomerEntity;
import ch.zhaw.pm3.loremipsum.generator.GenService;
import ch.zhaw.pm3.loremipsum.generator.data.TemplateEntity;
import ch.zhaw.pm3.loremipsum.generator.repo.TemplateRepository;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.GenDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.TemplateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.stream.StreamSupport;

@RestController
public class TemplateController {

    private final TemplateRepository templateRepository;
    private final GenService genService;

    public TemplateController(@Autowired TemplateRepository templateRepository,
                              @Autowired GenService genService) {
        this.templateRepository = templateRepository;
        this.genService = genService;
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

    @PostMapping("/api/template/generate")
    public String generateTemplate(@RequestBody final GenDto genDto) {
        return genService.generateStuff(genDto);
    }

}
