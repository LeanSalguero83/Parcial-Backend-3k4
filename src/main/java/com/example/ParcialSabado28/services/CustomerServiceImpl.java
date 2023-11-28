package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.CustomerDto;
import com.example.ParcialSabado28.model.Customer;
import com.example.ParcialSabado28.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService{
    CustomerRepository customerRepository;
    CustomerDtoMapper customerDtoMapper;
    CustomerMapper customerMapper;
    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> values = customerRepository.findAll();
        return values
                .stream()
                .map(customerDtoMapper)
                .toList();

    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = Optional.of(customerDto).map(customerMapper).orElseThrow();
        customerRepository.save(customer);
      return customerDtoMapper.apply(customer);


    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Customer customer = Optional.of(customerDto).map(customerMapper).orElseThrow();
        customerRepository.save(customer);
        return customerDtoMapper.apply(customer);



    }

    @Override
    public CustomerDto deleteCustomerById(String customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        CustomerDto deleted = optionalCustomer.map(customerDtoMapper).orElseThrow();
        optionalCustomer.ifPresent(customerRepository::delete);
        return deleted;


    }

    @Override
    public CustomerDto findCustomerById(String customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        return optionalCustomer.map(customerDtoMapper).orElseThrow();


    }
}
