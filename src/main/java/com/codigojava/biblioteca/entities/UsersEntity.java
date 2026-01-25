package com.codigojava.biblioteca.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "dni"),
                @UniqueConstraint(columnNames = "phone"),
                @UniqueConstraint(columnNames = "email")
        }
)
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @NotBlank
    @Size(min = 1, max = 100)
    @Column(name = "fullname", length = 100, nullable = false)
    private String fullname;

    @NotBlank
    @Size(min = 1, max = 20)
    @Column(name = "dni", length = 20, nullable = false)
    private String dni;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "city", length = 40)
    private String city;

    @Column(name = "province", length = 30)
    private String province;

    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @Column(name = "country", length = 30)
    private String country;

    @Column(name = "phone", length = 16, nullable = false)
    private String phone;

    @Column(name = "email", length = 120, nullable = false)
    private String email;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "user_drop")
    private Boolean userDrop;

    @Column(name = "days_disciplinary")
    private Integer daysDisciplinary;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20, nullable = false)
    private RoleEnum role;

}
