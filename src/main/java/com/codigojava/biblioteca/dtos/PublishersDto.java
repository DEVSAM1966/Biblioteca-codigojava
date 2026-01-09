package com.codigojava.biblioteca.dtos;

import lombok.Data;

@Data
public class PublishersDto {

    public int publisherId;
    public String namePublisher;
    public String address;
    public String city;
    public String province;
    public String postalCode;
    public String country;
    public String phone;
    public String notes;
//geters y seters
    public int getPublisherId() {
        return publisherId;
    }
    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }
    public String getNamePublisher() {
        return namePublisher;
    }
    public void setNamePublisher(String namePublisher) {
        this.namePublisher = namePublisher;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
//Constructor vacio
    public PublishersDto() {
    }
    //Constructor completo
    public PublishersDto(int publisherId,
                         String namePublisher,
                         String address,
                         String city,
                         String province,
                         String postalCode,
                         String country,
                         String phone,
                         String notes) {
        this.publisherId = publisherId;
        this.namePublisher = namePublisher;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
        this.phone = phone;
        this.notes = notes;
    }
}

