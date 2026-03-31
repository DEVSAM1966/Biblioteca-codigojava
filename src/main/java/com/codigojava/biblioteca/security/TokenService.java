package com.codigojava.biblioteca.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.codigojava.biblioteca.entities.UsersEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generarToken(UsersEntity usuario){
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Biblioteca API")
                    .withSubject(String.valueOf(usuario.getUserId()))
                    .withClaim("role", usuario.getRole().name())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("error al generar el  token jwt", exception);
        }
    }

    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-04:00"));
    }

    public String getSubject(String tokenJwt){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    // specify any specific claim validations
                    .withIssuer("Biblioteca API")
                    // reusable verifier instance
                    .build()
                    .verify(tokenJwt)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("token invaldo o expirado");
        }
    }
}
