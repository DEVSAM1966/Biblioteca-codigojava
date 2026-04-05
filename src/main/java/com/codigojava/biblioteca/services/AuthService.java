package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.UsersCreatedDh;
import com.codigojava.biblioteca.dtos.AuthResponse;
import com.codigojava.biblioteca.dtos.UsersDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    AuthResponse userRegister(@Valid UsersCreatedDh usersCreatedDh);
}
