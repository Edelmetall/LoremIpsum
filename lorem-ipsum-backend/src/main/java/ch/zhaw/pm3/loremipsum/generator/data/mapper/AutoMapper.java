package ch.zhaw.pm3.loremipsum.generator.data.mapper;

import ch.zhaw.pm3.loremipsum.customer.data.CustomerEntity;
import ch.zhaw.pm3.loremipsum.customer.data.CustomerRepository;
import ch.zhaw.pm3.loremipsum.generator.data.DataFormatEntity;
import ch.zhaw.pm3.loremipsum.generator.data.RowTemplateEntity;
import ch.zhaw.pm3.loremipsum.generator.data.TemplateEntity;
import ch.zhaw.pm3.loremipsum.generator.repo.DataFormatRepository;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowTemplateDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.TemplateDto;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This class is responsible for mapping objects to a specific class.
 */
@Service
public class AutoMapper {
    private final ModelMapper modelMapper;
    private final DataFormatRepository dataFormatRepository;
    private final CustomerRepository customerRepository;

    public AutoMapper(@Autowired DataFormatRepository dataFormatRepository,
                      @Autowired CustomerRepository customerRepository) {
        modelMapper = new ModelMapper();
        this.dataFormatRepository = dataFormatRepository;
        this.customerRepository = customerRepository;
        this.configureMapping();
    }

    /**
     * @param source          object to be mapped
     * @param destinationType type which source should be mapped to
     * @param <D>             type of destination
     * @return the mapped object
     */
    public <D> D map(Object source, Class<D> destinationType) {
        return this.modelMapper.map(source, destinationType);
    }

    private void configureMapping() {
        addTemplateMapping();
        addRowTemplateMapping();
    }

    private void addTemplateMapping() {
        modelMapper.typeMap(TemplateEntity.class, TemplateDto.class)
                .addMapping(TemplateEntity::getRowTemplateEntities, TemplateDto::setRowTemplateDtoSet);

        modelMapper.typeMap(TemplateDto.class, TemplateEntity.class)
                .addMapping(TemplateDto::getRowTemplateDtoSet, TemplateEntity::setRowTemplateEntities)
                .addMapping(TemplateDto::getOwnerId, TemplateEntity::setOwner);

        modelMapper.typeMap(Long.class, CustomerEntity.class).setConverter(mappingContext -> {
            Optional<CustomerEntity> customer = customerRepository.findById(mappingContext.getSource());
            return customer.orElseGet(() -> new CustomerEntity("", ""));
        });
    }

    private void addRowTemplateMapping() {
        modelMapper.typeMap(RowTemplateEntity.class, RowTemplateDto.class)
                .addMappings(mapper -> mapper.map(src -> src.getDataFormatEntity().getName(), RowTemplateDto::setDataType));

        modelMapper.typeMap(RowTemplateDto.class, RowTemplateEntity.class)
                .addMappings(mapper -> mapper.map(RowTemplateDto::getDataType, RowTemplateEntity::setDataFormatEntity));

        modelMapper.typeMap(String.class, DataFormatEntity.class).setConverter(mappingContext ->
                dataFormatRepository.findByName(mappingContext.getSource()));
    }
}
