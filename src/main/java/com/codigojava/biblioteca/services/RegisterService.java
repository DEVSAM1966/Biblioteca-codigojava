package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.UsersCreatedDh;
import com.codigojava.biblioteca.dtos.AuthResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public interface RegisterService {
    AuthResponse userRegister(@Valid UsersCreatedDh usersCreatedDh);
}
