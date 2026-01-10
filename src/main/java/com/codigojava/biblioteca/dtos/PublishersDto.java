package com.codigojava.biblioteca.dtos;

import lombok.Data;

@Data
public class PublishersDto {
    public Integer publisherId;
    public String namePublisher;
    public String address;
    public String city;
    public String province;
    public String postalCode;
    public String country;
    public String phone;
    public String notes;
}

