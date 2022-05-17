package ch.zhaw.pm3.loremipsum.generator.template.ui;

import ch.zhaw.pm3.loremipsum.generator.GenService;
import ch.zhaw.pm3.loremipsum.generator.template.service.TemplateService;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.DataFormatDto;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.GenDto;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.TemplateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;

@RestController
@RequestMapping("/api/template")
public class TemplateController {

    private final GenService genService;
    private final TemplateService templateService;

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
    @GetMapping("/availableDataFormats")
    public Collection<DataFormatDto> getAvailableDataFormats() {
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

    @PostMapping("/delete")
    @Transactional
    public boolean delete(@RequestBody final Long templateId) {
        return templateService.deleteTemplate(templateId);
    }

    @PostMapping("/generate")
    public String generateTemplate(@RequestBody final GenDto genDto) {
        return genService.generateData(genDto);
    }

}
