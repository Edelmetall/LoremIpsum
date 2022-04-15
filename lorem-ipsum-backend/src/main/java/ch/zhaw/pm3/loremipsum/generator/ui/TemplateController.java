package ch.zhaw.pm3.loremipsum.generator.ui;

import ch.zhaw.pm3.loremipsum.generator.GenService;
import ch.zhaw.pm3.loremipsum.generator.data.DataFormatEntity;
import ch.zhaw.pm3.loremipsum.generator.data.TemplateEntity;
import ch.zhaw.pm3.loremipsum.generator.service.TemplateService;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.GenDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.TemplateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
@RequestMapping("/api/template")
public class TemplateController {

    private final GenService genService;
    private TemplateService templateService;

    public TemplateController(@Autowired GenService genService,
                              @Autowired TemplateService templateService) {
        this.genService = genService;
        this.templateService = templateService;
    }

    @Transactional
    @GetMapping
    public Collection<TemplateDto> getAllTemplate() {
        return templateService.getAllTemplates();
    }

    @Transactional
    @GetMapping("/availableFormats")
    public Collection<DataFormatEntity> getAvailableDataFormats() {
        return templateService.getAvailableDataFormats();
    }

    @Transactional
    @GetMapping("/byId")
    public TemplateDto getTemplateById(@RequestParam(name = "id") Long templateId) {
        return templateService.getTemplateById(templateId);
    }

    @PostMapping("/save")
    @Transactional
    public TemplateDto save(@RequestBody final TemplateDto templateDto) {
        return templateService.saveTemplate(templateDto);
    }

    @PostMapping("/generate")
    public String generateTemplate(@RequestBody final GenDto genDto) {
        return genService.generateStuff(genDto);
    }

}
