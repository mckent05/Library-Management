package com.newDemom.Librarian.Domain.Dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
public class LoginDto {
    @NotEmpty
    private String userNameOrEmail;
    private String password;

}
