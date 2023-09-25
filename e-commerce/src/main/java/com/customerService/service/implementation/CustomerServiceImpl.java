package com.customerService.service.implementation;


import com.customerService.DTO.CustomerDTO;
import com.customerService.Exceptions.CustomerNotFoundException;
import com.customerService.entity.Customer;
import com.customerService.repository.CustomerRepository;
import com.customerService.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
    }


    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        if(customers.isEmpty()) {
            throw new CustomerNotFoundException("Customer", "invalid", 0);

        }
        return customers.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw  new CustomerNotFoundException("Post", "id", customerId);
        }
        return convertToDTO(optionalCustomer.get());
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = convertToEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDTO(savedCustomer);
    }

    @Override
    public CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isEmpty()) {
            throw new CustomerNotFoundException("Customer", "id",customerId);
        }
        Customer existingCustomer = optionalCustomer.get();
        existingCustomer.setFirstName(customerDTO.getFirstName());
        existingCustomer.setLastName(customerDTO.getLastName());
        existingCustomer.setEmail(customerDTO.getEmail());
        existingCustomer.setCity(customerDTO.getCity());
        existingCustomer.setState(customerDTO.getState());
        existingCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        existingCustomer.setStreetAddress(customerDTO.getStreetAddress());
        existingCustomer.setCountry(customerDTO.getCountry());
        existingCustomer.setPostalCode(customerDTO.getPostalCode());

        Customer updatedCustomer = customerRepository.save(existingCustomer);

        return convertToDTO(updatedCustomer);

    }

    @Override
    public void deleteCustomer(Long customerId) {

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            throw new CustomerNotFoundException("Customer", "id", customerId);
        }
        customerRepository.deleteById(customerId);
    }

    private CustomerDTO convertToDTO(Customer customer) {
        /*// CustomerDTO customerDTO = new CustomerDTO();
        //BeanUtils.copyProperties(customerDTO, customer);
        //return customerDTO;*/
        return modelMapper.map(customer, CustomerDTO.class);
    }

    private Customer convertToEntity(CustomerDTO customerDTO) {
        /*//Customer customer = new Customer();
        //BeanUtils.copyProperties(customerDTO, customer);
        //return customer;*/
        return modelMapper.map(customerDTO, Customer.class);

    }
}
