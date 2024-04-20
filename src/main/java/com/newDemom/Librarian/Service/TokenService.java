package com.newDemom.Librarian.Service;

import com.newDemom.Librarian.Domain.Token;
import com.newDemom.Librarian.Domain.UserEntity;

import java.util.List;

public interface TokenService {

    public void createToken(String jwt, UserEntity userEntity);

    public List<Token> getAllUserTokens(Long userId);

    public void saveAllTokens(List<Token> token);

    public void logOutToken(String jwtToken);
    public boolean isTokenValidate(String token);
}
