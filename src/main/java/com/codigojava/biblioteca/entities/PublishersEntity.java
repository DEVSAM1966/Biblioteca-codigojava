package com.codigojava.biblioteca.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; 

@Entity
@Data 
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Publishers")
public class PublishersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private Integer publisherId;

    
    @NotBlank
    @Size(max = 100)
    @Column(name = "name_publisher", nullable = false, length = 100)
    private String namePublisher;

    
    @Size(max = 100)
    @Column(name = "address", length = 100)
    private String address;

   
    @Size(max = 40)
    @Column(name = "city", length = 40)
    private String city;

    
    @Size(max = 30)
    @Column(name = "province", length = 30)
    private String province;

    @Size(max = 20)
    @Column(name = "postal_code", length = 20)
    private String postalCode;

    
    @Size(max = 30)
    @Column(name = "country", length = 30)
    private String country;

    
    @Size(max = 16)
    @Column(name = "phone", length = 16)
    private String phone;

    
    @Size(max = 255)
    @Column(name = "notes", length = 255)
    private String notes;
    
}
