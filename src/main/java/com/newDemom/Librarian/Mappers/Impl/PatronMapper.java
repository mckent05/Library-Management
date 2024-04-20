package com.newDemom.Librarian.Mappers.Impl;

import com.newDemom.Librarian.Domain.Dto.PatronDto;
import com.newDemom.Librarian.Domain.PatronEntity;
import com.newDemom.Librarian.Mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PatronMapper implements Mapper<PatronEntity, PatronDto> {

    private ModelMapper modelMapper;

    public PatronMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PatronDto mapFrom(PatronEntity patronEntity) {
        return modelMapper.map(patronEntity, PatronDto.class);
    }

    @Override
    public PatronEntity MapTo(PatronDto patronDto) {
        return modelMapper.map(patronDto, PatronEntity.class);
    }
}
