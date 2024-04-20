package com.newDemom.Librarian.Domain.Dto;

import jakarta.validation.constraints.NotEmpty;

public class PatronDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String contactAddress;

    @NotEmpty
    private String phoneNumber;
}
