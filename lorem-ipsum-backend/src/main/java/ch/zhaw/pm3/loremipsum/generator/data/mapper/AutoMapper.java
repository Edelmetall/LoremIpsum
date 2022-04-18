package ch.zhaw.pm3.loremipsum.generator.data.mapper;

import ch.zhaw.pm3.loremipsum.customer.data.CustomerEntity;
import ch.zhaw.pm3.loremipsum.customer.data.CustomerRepository;
import ch.zhaw.pm3.loremipsum.generator.data.DataFormatEntity;
import ch.zhaw.pm3.loremipsum.generator.data.RowConfigurationEntity;
import ch.zhaw.pm3.loremipsum.generator.data.TemplateEntity;
import ch.zhaw.pm3.loremipsum.generator.repo.DataFormatRepository;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowConfigurationDto;
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
                .addMapping(TemplateEntity::getRowTemplateEntities, TemplateDto::setRowConfigurationDtoSet)
                .addMappings(mapper -> mapper.map(src -> src.getOwner().getId(), TemplateDto::setOwnerId));

        modelMapper.typeMap(TemplateDto.class, TemplateEntity.class)
                .addMapping(TemplateDto::getRowConfigurationDtoSet, TemplateEntity::setRowTemplateEntities)
                .addMapping(TemplateDto::getOwnerId, TemplateEntity::setOwner);

        modelMapper.typeMap(Long.class, CustomerEntity.class).setConverter(mappingContext -> {
            Optional<CustomerEntity> customer = customerRepository.findById(mappingContext.getSource());
            return customer.orElseGet(() -> new CustomerEntity("", ""));
        });
    }

    private void addRowTemplateMapping() {
        modelMapper.typeMap(RowConfigurationEntity.class, RowConfigurationDto.class)
                .addMappings(mapper -> mapper.map(src -> src.getDataFormatEntity().getName(), RowConfigurationDto::setDataType));

        modelMapper.typeMap(RowConfigurationDto.class, RowConfigurationEntity.class)
                .addMappings(mapper -> mapper.map(RowConfigurationDto::getDataType, RowConfigurationEntity::setDataFormatEntity));

        modelMapper.typeMap(String.class, DataFormatEntity.class).setConverter(mappingContext ->
                dataFormatRepository.findByName(mappingContext.getSource()));
    }
}
