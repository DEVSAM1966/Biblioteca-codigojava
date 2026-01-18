package com.codigojava.biblioteca.dtos;

import com.codigojava.biblioteca.entities.RoleEnum;
import lombok.Data;

import java.util.Date;

@Data
public class UsersDto {

   private Integer userId;

   private String fullname;

   private String dni;

   private String address;

   private String city;

   private String province;

   private String postalCode;

   private String country;

   private String phone;

   private String email;

   private String password;

   private Date registrationDate;

   private boolean userDrop;

   private Integer daysDisciplinary;
   
   private RoleEnum role;

}
