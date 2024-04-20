package com.newDemom.Librarian.Domain.Dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BookDto {

    private Long id;

    @NotEmpty
    private String isbn;

    @NotEmpty
    private String publicationYear;

    @NotEmpty
    private String author;

    @NotEmpty
    private String title;
}
