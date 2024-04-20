package com.newDemom.Librarian.Mappers.Impl;

import com.newDemom.Librarian.Domain.BookEntity;
import com.newDemom.Librarian.Domain.Dto.BookDto;
import com.newDemom.Librarian.Mappers.Mapper;
import org.modelmapper.ModelMapper;

public class BookMapper implements Mapper<BookEntity, BookDto> {

    private ModelMapper modelMapper;

    public BookMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public BookDto mapFrom(BookEntity bookEntity) {
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Override
    public BookEntity MapTo(BookDto bookDto) {
        return modelMapper.map(bookDto, BookEntity.class);
    }
}
