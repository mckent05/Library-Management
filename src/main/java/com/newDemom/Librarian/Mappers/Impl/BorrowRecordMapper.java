package com.newDemom.Librarian.Mappers.Impl;

import com.newDemom.Librarian.Domain.BorrowRecord;
import com.newDemom.Librarian.Domain.Dto.BorrowRecordDto;
import com.newDemom.Librarian.Mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BorrowRecordMapper implements Mapper<BorrowRecord, BorrowRecordDto> {

    private ModelMapper modelMapper;

    public BorrowRecordMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BorrowRecordDto mapFrom(BorrowRecord borrowRecord) {
        return modelMapper.map(borrowRecord, BorrowRecordDto.class);
    }

    @Override
    public BorrowRecord MapTo(BorrowRecordDto borrowRecordDto) {
        return modelMapper.map(borrowRecordDto, BorrowRecord.class);
    }
}
