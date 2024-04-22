package com.newDemom.Librarian.Domain.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

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

}
