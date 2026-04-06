package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dtos.AuthResponse;
import com.codigojava.biblioteca.entities.UsersEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    AuthResponse login(UsersEntity user);
}
