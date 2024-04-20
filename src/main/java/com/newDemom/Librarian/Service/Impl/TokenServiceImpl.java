package com.newDemom.Librarian.Service.Impl;

import com.newDemom.Librarian.Domain.Token;
import com.newDemom.Librarian.Domain.TokenType;
import com.newDemom.Librarian.Domain.UserEntity;
import com.newDemom.Librarian.Exception.BlogAPIException;
import com.newDemom.Librarian.Repository.TokenRepository;
import com.newDemom.Librarian.Service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenServiceImpl implements TokenService {

    private TokenRepository tokenRepository;

    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void createToken(String jwt, UserEntity userEntity) {
        Token token = new Token();
        token.setToken(jwt);
        token.setUser(userEntity);
        token.setTokenType(TokenType.BEARER);
        token.setExpired(false);
        token.setRevoked(false);
        tokenRepository.save(token);
    }

    @Override
    public List<Token> getAllUserTokens(Long userId) {
        return tokenRepository.findAllUserTokens(userId);
    }

    @Override
    public void saveAllTokens(List<Token> token) {
        tokenRepository.saveAll(token);
    }

    @Override
    public void logOutToken(String jwtToken) {
        Token findToken = tokenRepository.findByToken(jwtToken).
                orElseThrow(() -> new BlogAPIException("Invalid Token", HttpStatus.BAD_REQUEST));
        findToken.setExpired(true);
        findToken.setRevoked(true);
        tokenRepository.save(findToken);
    }

    @Override
    public boolean isTokenValidate(String token) {
        boolean isTokenValid = tokenRepository.findByToken(token).
                map(t -> !t.isExpired() && !t.isRevoked()).orElse(false);
        return isTokenValid;
    }
}
