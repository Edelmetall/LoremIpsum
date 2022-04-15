package ch.zhaw.pm3.loremipsum.generator.service;

import ch.zhaw.pm3.loremipsum.customer.data.CustomerRepository;
import ch.zhaw.pm3.loremipsum.generator.data.mapper.AutoMapper;
import ch.zhaw.pm3.loremipsum.generator.data.DataFormatEntity;
import ch.zhaw.pm3.loremipsum.generator.data.TemplateEntity;
import ch.zhaw.pm3.loremipsum.generator.repo.DataFormatRepository;
import ch.zhaw.pm3.loremipsum.generator.repo.RowTemplateRepository;
import ch.zhaw.pm3.loremipsum.generator.repo.TemplateRepository;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.TemplateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
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

    public Collection<TemplateDto> getAllTemplates() {
        return StreamSupport.stream(templateRepository.findAll().spliterator(), false)
                .map(templateEntity -> autoMapper.map(templateEntity, TemplateDto.class)).toList();
    }

    public TemplateDto getTemplateById(long templateId) {
        Optional<TemplateEntity> templateEntity = templateRepository.findById(templateId);
        TemplateDto templateDto = new TemplateDto();
        if (templateEntity.isPresent()) {
            templateDto = autoMapper.map(templateEntity.get(), TemplateDto.class);
        }
        return templateDto;
    }

    public Collection<DataFormatEntity> getAvailableDataFormats() {
        return StreamSupport.stream(dataFormatRepository.findAll().spliterator(), false).toList();
    }

    public TemplateDto saveTemplate(TemplateDto templateDto) {
        TemplateEntity templateEntity =autoMapper.map(templateDto, TemplateEntity.class);
        templateEntity.setOwner(customerRepository.findById(50L));
        templateEntity.getRowTemplateEntities().forEach(rowTemplateEntity -> {
            rowTemplateEntity.setTemplateEntity(templateEntity);
        });
        return autoMapper.map(templateRepository.save(templateEntity), TemplateDto.class);
    }
}
