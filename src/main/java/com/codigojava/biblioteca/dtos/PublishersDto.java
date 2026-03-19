package com.codigojava.biblioteca.dtos;


import com.codigojava.biblioteca.entities.PublishersEntity;

public record PublishersDto(
        Integer publisherId,
        String namePublisher,
        String address,
        String city,
        String province,
        String postalCode,
        String country,
        String phone,
        String notes
) {

}

