package com.newDemom.Librarian.Domain.Dto;

import com.newDemom.Librarian.Domain.BorrowRecord;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatronDto {

    private Long id;
    @NotBlank
    private String name;

    @NotBlank
    private String contactAddress;

    @NotBlank
    private String phoneNumber;

    private List<BorrowRecordDto> borrowRecords;
}
