package com.codigojava.biblioteca.controllers;

import com.codigojava.biblioteca.dataholders.PublishersCreatedRecordDh;
import com.codigojava.biblioteca.dataholders.UsersCreatedDh;
import com.codigojava.biblioteca.dtos.AuthResponse;
import com.codigojava.biblioteca.dtos.PublishersDto;
import com.codigojava.biblioteca.dtos.UsersDto;
import com.codigojava.biblioteca.services.AuthService;
import com.codigojava.biblioteca.services.UsersService;
import com.codigojava.biblioteca.wrappers.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@RequestBody @Valid UsersCreatedDh create, UriComponentsBuilder uriComponentsBuilder){
        //crear un nuevo usuario

        //metodo para crear en el service
        AuthResponse created = authService.userRegister(create);
        var uri=uriComponentsBuilder.path("/users/{id}").buildAndExpand(created.user().userId()).toUri();

        return ResponseEntity.created(uri).body(new ApiResponse<>(created));

    }
}
