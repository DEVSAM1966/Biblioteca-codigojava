import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; 

@Entity
@Data 
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "publishers")
public class PublishersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private Integer publisherId;

    
    @NotBlank(message = "Publisher name is mandatory")
    @Size(max = 100, message = "Publisher name cannot exceed 100 characters")
    @Column(name = "name_publisher", nullable = false, length = 100)
    private String namePublisher;

    
    @Size(max = 100, message = "Address cannot exceed 100 characters")
    @Column(name = "address", length = 100)
    private String address;

   
    @Size(max = 40, message = "City cannot exceed 40 characters")
    @Column(name = "city", length = 40)
    private String city;

    
    @Size(max = 30, message = "Province cannot exceed 30 characters")
    @Column(name = "province", length = 30)
    private String province;

    @Size(max = 20, message = "Postal code cannot exceed 20 characters")
    @Pattern(regexp = "^\\d+$", message = "Postal code must contain only numbers")
    @Column(name = "postal_code", length = 20)
    private String postalCode;

    
    @Size(max = 30, message = "Country cannot exceed 30 characters")
    @Column(name = "country", length = 30)
    private String country;

    
    @Size(max = 16, message = "Phone number cannot exceed 16 characters")
    @Pattern(regexp = "^[0-9+\\-\\s()]*$", message = "Phone number contains invalid characters (letters are not allowed)") 
    @Column(name = "phone", length = 16)
    private String phone;

    
    @Size(max = 255, message = "Notes cannot exceed 255 characters")
    @Column(name = "notes", length = 255)
    private String notes;
    
}
