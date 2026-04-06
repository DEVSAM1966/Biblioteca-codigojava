package com.codigojava.biblioteca.controllers;

import com.codigojava.biblioteca.dataholders.DatosAutenticacionDh;
import com.codigojava.biblioteca.dataholders.UsersCreatedDh;
import com.codigojava.biblioteca.dtos.AuthResponse;
import com.codigojava.biblioteca.entities.UsersEntity;
import com.codigojava.biblioteca.security.TokenService;
import com.codigojava.biblioteca.services.AuthService;
import com.codigojava.biblioteca.services.RegisterService;
import com.codigojava.biblioteca.wrappers.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private RegisterService registerService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private AuthService authService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@RequestBody @Valid UsersCreatedDh create, UriComponentsBuilder uriComponentsBuilder){
        //crear un nuevo usuario

        //metodo para crear en el service
        AuthResponse created = registerService.userRegister(create);
        var uri=uriComponentsBuilder.path("/users/{id}").buildAndExpand(created.user().userId()).toUri();

        return ResponseEntity.created(uri).body(new ApiResponse<>(created));

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody @Valid DatosAutenticacionDh datos) {
        var authToken = new UsernamePasswordAuthenticationToken(datos.email(), datos.password());
        var autenticacion = manager.authenticate(authToken);

        UsersEntity user = (UsersEntity) autenticacion.getPrincipal();
        AuthResponse response = authService.login(user);

        return ResponseEntity.ok(new ApiResponse<>(response));
    }

}
