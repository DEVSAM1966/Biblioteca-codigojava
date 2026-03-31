package com.codigojava.biblioteca.services;

import com.codigojava.biblioteca.dataholders.UsersCreatedDh;
import com.codigojava.biblioteca.dtos.AuthResponse;
import com.codigojava.biblioteca.dtos.UsersDto;
import com.codigojava.biblioteca.entities.PublishersEntity;
import com.codigojava.biblioteca.entities.RoleEnum;
import com.codigojava.biblioteca.entities.UsersEntity;
import com.codigojava.biblioteca.exceptions.BdNotSaveException;
import com.codigojava.biblioteca.mappers.UsersMapper;
import com.codigojava.biblioteca.repositories.UsersRepository;
import com.codigojava.biblioteca.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServicelmp implements UserDetailsService,AuthService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

   public AuthResponse userRegister(@Valid UsersCreatedDh usersCreatedDh){
       if (usersRepository.existsByEmail(usersCreatedDh.email())) {
           log.warn("CreateUser - email {} already exists", usersCreatedDh.email());
           throw new BdNotSaveException("The email " + usersCreatedDh.email() + " already exists.");
       }
       UsersEntity userNew = usersMapper.asEntity(usersCreatedDh);
       userNew.setPassword(passwordEncoder.encode(usersCreatedDh.password()));
       userNew.setRole(RoleEnum.USER);
       UsersEntity saved = usersRepository.save(userNew);
       String token = tokenService.generarToken(saved);
       return new AuthResponse(usersMapper.asUserSummaryDto(saved),token) ;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
