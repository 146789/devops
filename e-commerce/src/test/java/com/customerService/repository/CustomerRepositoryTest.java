package com.customerService.repository;

import com.customerService.entity.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;


//    @BeforeEach
//    public void setUp(){
//
//        customer = Customer.builder().id(1L)
//                .firstName("Naveen")
//                .lastName("Munuparthi")
//                .email("naveen@gmail.com")
//                .state("Andhra pradesh")
//                .city("Visakhapahtnam")
//                .phoneNumber("+976481263871")
//                .country("India")
//                .streetAddress("seethammapeta")
//                .postalCode("3265274")
//                .build();
//
//        customerRepository.save(customer);
//
//    }



    @Test
    @Sql(scripts = "classpath:sqlscript/customer_data.sql")
    public void testFindCustomerById() {

        Optional<Customer> optionalCustomer  = customerRepository.findById(31L);
        assertTrue(optionalCustomer.isPresent());

        Customer customer1 = optionalCustomer.get();
        assertEquals("John", customer1.getFirstName());
        assertEquals("Doe", customer1.getLastName());
        assertEquals("john.doe@example.com", customer1.getEmail());
    }

    @Test
    @Sql(scripts = "classpath:sqlscript/customer_data.sql")
    public void testFindByFirstName() {

        List<Customer> johns = customerRepository.findByFirstName("John");
        assertEquals(1, johns.size());
        Customer john = johns.get(0);
        assertEquals("John", john.getFirstName());
    }

    @Test
    @Sql(scripts = "classpath:sqlscript/customer_data.sql")
    public void testFindByLastName() {

        List<Customer> smiths = customerRepository.findByLastName("Smith");
        assertEquals(2, smiths.size());
    }

    @Test
    @Sql(scripts = "classpath:sqlscript/customer_data.sql")
    public void testFindByEmail(){

        Optional<Customer> optionalCustomer = customerRepository.findByEmail("alice.smith@example.com");
        assertTrue(optionalCustomer.isPresent());

        Customer alice = optionalCustomer.get();
        assertEquals("Alice", alice.getFirstName());
        assertEquals("Smith", alice.getLastName());

        assertNotNull(alice.getState());
        assertNotNull(alice.getStreetAddress());
    }

//    @AfterEach
//    void tearDown() {
//
//        customer = null;
//        customerRepository.deleteAll();
//    }
}
