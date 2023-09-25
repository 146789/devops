package com.customerService.controller;

import com.customerService.DTO.CustomerDTO;
import com.customerService.Exceptions.CustomerNotFoundException;
import com.customerService.Exceptions.ErrorResponse;
import com.customerService.Exceptions.InvalidDataException;
import com.customerService.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
@Data
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @GetMapping("/")
    public ResponseEntity<?> getAllCustomers() {
        try {
            List<CustomerDTO> customers = customerService.getAllCustomers();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setError("NO_CUSTOMERS_FOUND");
            errorResponse.setMessage("No customers found.");
            errorResponse.setTimeStamp(LocalDateTime.now());
            errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }


    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Long id) {

        try {
            CustomerDTO customer = customerService.getCustomerById(id);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            String errorMessage = "Customer not found: " + e.getMessage();
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setError("NO_CUSTOMER_FOUND");
            errorResponse.setMessage(errorMessage);
            errorResponse.setTimeStamp(LocalDateTime.now());
            errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> createCustomer(@RequestBody CustomerDTO customerDTO) {

        try {
            CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
            return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
        } catch (CustomerNotFoundException e) {
            String errorMessage = "Customer not found: " + e.getMessage();
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setError("NO_CUSTOMER_FOUND");
            errorResponse.setMessage(errorMessage);
            errorResponse.setTimeStamp(LocalDateTime.now());
            errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }


    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO updateCustomerDTO) {
        try {
            CustomerDTO updatedCustomer = customerService.updateCustomer(id, updateCustomerDTO);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            String errorMessage = "Customer not found: " + e.getMessage();
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setError("NO_CUSTOMER_FOUND");
            errorResponse.setMessage(errorMessage);
            errorResponse.setTimeStamp(LocalDateTime.now());
            errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id) {
            try {
                customerService.deleteCustomer(id);
                return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
            }
            catch (CustomerNotFoundException e) {
            String errorMessage = "Customer not found: " + e.getMessage();
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setError("NO_CUSTOMER_FOUND");
            errorResponse.setMessage(errorMessage);
            errorResponse.setTimeStamp(LocalDateTime.now());
            errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
}
