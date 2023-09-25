package com.customerService.services;

import com.customerService.DTO.CustomerDTO;
import com.customerService.entity.Customer;
import com.customerService.repository.CustomerRepository;
import com.customerService.service.CustomerService;
import com.customerService.service.implementation.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService = new  CustomerServiceImpl(customerRepository);

    Customer customer;

    @BeforeEach
    void setUp() {

        customer = Customer.builder().firstName("naveen")
                .lastName("mn")
                .email("naveensrt248@gmail.com")
                .phoneNumber("+918919196089")
                .city("Hyderabad")
                .state("telangana")
                .country("India")
                .streetAddress("gachibowli")
                .postalCode("530016")
                .build();
    }

    @Test
    public void getCustomerList_thenReturnEmployeeList(){

        Customer customer1 = Customer.builder().firstName("shardul")
                .lastName("Takur")
                .email("shardultakur248@gmail.com")
                .phoneNumber("+918919196089")
                .city("Hyderabad")
                .state("telangana")
                .country("India")
                .streetAddress("gachibowli")
                .postalCode("530016")
                .build();
        when(customerRepository.findAll()).thenReturn(List.of(customer, customer1));

        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();


        assertThat(customerDTOS).isEmpty();
        assertThat(customerDTOS.size()).isEqualTo(0);


    }
}