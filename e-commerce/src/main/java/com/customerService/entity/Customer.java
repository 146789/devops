package com.customerService.entity;

import com.customerService.DTO.CustomerDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name="street_address", nullable = false)
    private String streetAddress;

    @Column(name="city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name="postal_code")
    private String postalCode;

    @Column(name="country", nullable = false)
    private String country;

}
