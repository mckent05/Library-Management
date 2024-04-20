package com.newDemom.Librarian.Service;

import com.newDemom.Librarian.Domain.Dto.LoginDto;
import com.newDemom.Librarian.Domain.Dto.RegisterDto;

public interface AuthService {

    public String login(LoginDto loginDto);

    public String register(RegisterDto registerDto);

}
