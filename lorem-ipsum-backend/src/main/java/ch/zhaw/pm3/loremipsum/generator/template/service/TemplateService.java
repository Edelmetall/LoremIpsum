package ch.zhaw.pm3.loremipsum.generator.template.service;

import ch.zhaw.pm3.loremipsum.common.EntryTypeEnum;
import ch.zhaw.pm3.loremipsum.customer.data.CustomerRepository;
import ch.zhaw.pm3.loremipsum.common.AutoMapper;
import ch.zhaw.pm3.loremipsum.generator.template.data.DataFormatEntity;
import ch.zhaw.pm3.loremipsum.generator.template.data.TemplateEntity;
import ch.zhaw.pm3.loremipsum.generator.template.repo.DataFormatRepository;
import ch.zhaw.pm3.loremipsum.generator.template.repo.TemplateRepository;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.DataFormatDto;
import ch.zhaw.pm3.loremipsum.generator.template.ui.dto.TemplateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TemplateService {
    private final AutoMapper autoMapper;
    private final TemplateRepository templateRepository;
    private final CustomerRepository customerRepository;
    private final DataFormatRepository dataFormatRepository;

    public TemplateService(@Autowired AutoMapper automapper,
                           @Autowired TemplateRepository templateRepository,
                           @Autowired CustomerRepository customerRepository,
                           @Autowired DataFormatRepository dataFormatRepository) {
        this.autoMapper = automapper;
        this.templateRepository = templateRepository;
        this.customerRepository = customerRepository;
        this.dataFormatRepository = dataFormatRepository;
    }

    /**
     * get all existing templates
     *
     * @return collection of existing templates
     */
    public Collection<TemplateDto> getAllTemplates() {
        return StreamSupport.stream(templateRepository.findAll().spliterator(), false)
                .map(templateEntity -> autoMapper.map(templateEntity, TemplateDto.class)).toList();
    }

    /**
     * get a specific template by id
     *
     * @param templateId id of the template
     * @return template with passed id or empty template object if the template does not exist
     */
    public TemplateDto getTemplateById(long templateId) {
        Optional<TemplateEntity> templateEntity = templateRepository.findById(templateId);
        TemplateDto templateDto = new TemplateDto();
        if (templateEntity.isPresent()) {
            templateDto = autoMapper.map(templateEntity.get(), TemplateDto.class);
        }
        return templateDto;
    }

    /**
     * get all existing and active data formats
     *
     * @return active dataformats
     */
    public Collection<DataFormatDto> getAvailableDataFormats() {
        return Arrays.stream(EntryTypeEnum.values()).map(DataFormatDto::new).collect(Collectors.toList());
    }

    /**
     * create or update a template
     *
     * @param templateDto template dto object
     * @return saved template entity
     */
    public TemplateDto saveTemplate(TemplateDto templateDto) {
        TemplateEntity templateEntity = autoMapper.map(templateDto, TemplateEntity.class);
        templateEntity.getRowTemplateEntities().forEach(rowTemplateEntity -> {
            rowTemplateEntity.setTemplateEntity(templateEntity);
        });
        return autoMapper.map(templateRepository.save(templateEntity), TemplateDto.class);
    }

    /**
     * delete a template
     *
     * @param templateId id of template which should be deleted
     * @return true if deletion was successful, otherwise false
     */
    public boolean deleteTemplate(Long templateId) {
        boolean deleted = false;
        Optional<TemplateEntity> templateEntity = templateRepository.findById(templateId);
        if (templateEntity.isPresent()) {
            templateRepository.delete(templateEntity.get());
            deleted = true;
        }
        return deleted;
    }
}
