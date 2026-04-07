package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.DatosAutenticacionDh;
import com.codigojava.biblioteca.dtos.AuthResponse;
import com.codigojava.biblioteca.entities.UsersEntity;
import com.codigojava.biblioteca.mappers.UsersMapper;
import com.codigojava.biblioteca.repositories.UsersRepository;
import com.codigojava.biblioteca.security.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServicelmp implements AuthService{

    private final UsersRepository usersRepository;

    private final TokenService tokenService;

    private final UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user= usersRepository.findByEmail(username);
        if (user == null) {
            log.warn("loadUserByUsername - No user found with email: {}", username);
            throw new UsernameNotFoundException("User not found with email: " + username);
        }
        return user;
    }

    @Override
    public AuthResponse login(UsersEntity user) {
        String token = tokenService.generarToken(user);
        return new AuthResponse(usersMapper.asUserSummaryDto(user), token);
    }



}
