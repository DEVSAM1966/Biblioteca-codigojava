package com.codigojava.biblioteca.repositories;

import com.codigojava.biblioteca.entities.UsersEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {

    List<UsersEntity> findByFullnameContainingIgnoreCase(String name);

    boolean existsByEmail(@NotBlank(message = "User email is mandatory") @Size(max = 120, message = "User email cannot exceed 120 characters") @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                message = "Invalid email format") String email);

    boolean existsByPhone(@NotBlank(message = "User phone is mandatory") @Size(max = 16, message = "User phone cannot exceed 16 characters") @Pattern(
                regexp = "^\\+?[0-9\\s\\-()]{6,20}$",
                message = "Invalid phone number format") String phone);

    boolean existsByDni(@NotBlank(message = "User DNI is mandatory") @Size(max = 20, message = "User DNI cannot exceed 20 characters") String dni);

    UserDetails findByEmail(String username);

    Optional<UsersEntity> findByDniAndEmailAndPhone(
            String dni,
            String email,
            String phone
    );

}
