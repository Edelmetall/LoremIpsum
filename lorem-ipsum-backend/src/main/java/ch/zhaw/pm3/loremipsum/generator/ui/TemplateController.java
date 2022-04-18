package ch.zhaw.pm3.loremipsum.generator.ui;

import ch.zhaw.pm3.loremipsum.common.HeaderInfomation;
import ch.zhaw.pm3.loremipsum.generator.GenService;
import ch.zhaw.pm3.loremipsum.generator.data.DataFormatEntity;
import ch.zhaw.pm3.loremipsum.generator.service.TemplateService;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.GenDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowEntryDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.TemplateDto;
import ch.zhaw.pm3.loremipsum.output.OutputEnum;
import ch.zhaw.pm3.loremipsum.output.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/template")
public class TemplateController {

    private final GenService genService;
    private TemplateService templateService;
    private OutputService outputService;

    public TemplateController(@Autowired GenService genService,
                              @Autowired TemplateService templateService,
                              @Autowired OutputService outputService) {
        this.genService = genService;
        this.templateService = templateService;
        this.outputService = outputService;
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

    @PostMapping("/delete")
    @Transactional
    public boolean delete(@RequestBody final Long templateId) {
        return templateService.deleteTemplate(templateId);
    }

    @PostMapping("/generate")
    public String generateTemplate(@RequestBody final GenDto genDto) {
        List<HeaderInfomation> headerInfomationList = genService.getHeaderInformation(genDto);
        List<RowEntryDto> rowEntryDtos = genService.generateData(genDto);

        return outputService.generateModel(OutputEnum.valueOf(genDto.getOutputName()), headerInfomationList, rowEntryDtos);
    }

}
