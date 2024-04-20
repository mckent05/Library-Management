package com.newDemom.Librarian.Service.Impl;

import com.newDemom.Librarian.Domain.Dto.LoginDto;
import com.newDemom.Librarian.Domain.Dto.RegisterDto;
import com.newDemom.Librarian.Domain.Token;
import com.newDemom.Librarian.Domain.UserEntity;
import com.newDemom.Librarian.Exception.BlogAPIException;
import com.newDemom.Librarian.Repository.UserRepository;
import com.newDemom.Librarian.Security.JwtTokenProvider;
import com.newDemom.Librarian.Service.AuthService;
import com.newDemom.Librarian.Service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    AuthenticationManager authenticationManager;
    UserRepository userRepository;

    private JwtTokenProvider jwtTokenProvider;

    private TokenService tokenService;

    PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider,
                           TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenService = tokenService;

    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUserNameOrEmail(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtTokenProvider.generateToken(authenticate);
        UserEntity user = userRepository.findByUserNameOrEmail(loginDto.getUserNameOrEmail(),
                loginDto.getUserNameOrEmail()).orElseThrow(() ->
                new BlogAPIException("Invalid username or Email", HttpStatus.BAD_REQUEST));
        List<Token> getTokens = tokenService.getAllUserTokens(user.getId());
        if(getTokens.isEmpty() ||
                !tokenService.isTokenValidate(getTokens.get(0).getToken())) {
            tokenService.createToken(token, user);
        } else {
            token = getTokens.get(0).getToken();
        }

        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new BlogAPIException("Email already exists!", HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByUserName(registerDto.getUserName())){
            throw new BlogAPIException("Username already exists!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setName(registerDto.getName());
        user.setUserName(registerDto.getUserName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        userRepository.save(user);
        return "User successfully signed up";

    }
}
