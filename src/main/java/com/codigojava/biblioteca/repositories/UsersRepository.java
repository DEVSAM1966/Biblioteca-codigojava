package com.codigojava.biblioteca.repositories;

import com.codigojava.biblioteca.entities.UsersEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {

    List<UsersEntity> findByFullnameContainingIgnoreCase(String name);

    boolean existsByEmail(@NotBlank(message = "User email is mandatory") @Size(max = 120, message = "User email cannot exceed 120 characters") @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                message = "Invalid email format") String email);
}
