package com.customerService.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDTO {

    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^\\+[0-9](1,3)[0-9]{3,14}$", message = "Invalid phone Number format. Use + followed by country code and digits.")
    private String phoneNumber;

    @NotBlank(message = "street address is required")
    private String streetAddress;

    @NotBlank(message = "city is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @Pattern(regexp = "^[0-9]{7}$", message = "Postal code must be 7 digits")
    private String postalCode;

    @NotBlank(message = "Country is required")
    private String country;
}
