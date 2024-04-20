package com.newDemom.Librarian.Domain.Dto;

import jakarta.validation.constraints.NotEmpty;

public class BookDto {

    @NotEmpty
    private String isbn;

    @NotEmpty
    private String publicationYear;

    @NotEmpty
    private String author;

    @NotEmpty
    private String title;
}
