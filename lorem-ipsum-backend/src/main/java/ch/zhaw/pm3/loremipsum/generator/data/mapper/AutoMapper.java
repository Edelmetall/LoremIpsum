package ch.zhaw.pm3.loremipsum.generator.data.mapper;

import ch.zhaw.pm3.loremipsum.generator.data.DataFormatEntity;
import ch.zhaw.pm3.loremipsum.generator.data.RowTemplateEntity;
import ch.zhaw.pm3.loremipsum.generator.data.TemplateEntity;
import ch.zhaw.pm3.loremipsum.generator.repo.DataFormatRepository;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.RowTemplateDto;
import ch.zhaw.pm3.loremipsum.generator.ui.dto.TemplateDto;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoMapper {
    private final ModelMapper modelMapper;
    private final DataFormatRepository dataFormatRepository;

    public AutoMapper(@Autowired DataFormatRepository dataFormatRepository) {
        modelMapper = new ModelMapper();
        this.dataFormatRepository = dataFormatRepository;
        this.configureMapping();
    }

    public <D, T> D map(T source, Class<D> destination) {
        return this.modelMapper.map(source, destination);
    }

    private void configureMapping() {
        addTemplateMapping();
        addRowTemplateMapping();
    }

    private void addTemplateMapping() {
        modelMapper.typeMap(TemplateEntity.class, TemplateDto.class)
                .addMapping(TemplateEntity::getRowTemplateEntities, TemplateDto::setRowTemplateDtoSet);

        modelMapper.typeMap(TemplateDto.class, TemplateEntity.class)
                .addMapping(TemplateDto::getRowTemplateDtoSet, TemplateEntity::setRowTemplateEntities);
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
