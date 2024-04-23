package com.newDemom.Librarian.Domain.Dto;

import com.newDemom.Librarian.Domain.BorrowRecord;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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


    private List<BorrowRecordDto> borrowRecords;
}
