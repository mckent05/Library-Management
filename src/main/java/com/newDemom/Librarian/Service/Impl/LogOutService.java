package com.newDemom.Librarian.Service.Impl;

import com.newDemom.Librarian.Service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class LogOutService implements LogoutHandler {

    private TokenService tokenService;

    public LogOutService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            return;
        }
        jwt = authHeader.substring(7);
        tokenService.logOutToken(jwt);
    }
}
