package com.codigojava.biblioteca.dtos;


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
) {}

