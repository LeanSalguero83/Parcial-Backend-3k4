package com.example.ParcialSabado28.services;

import com.example.ParcialSabado28.controller.CustomerDto;
import com.example.ParcialSabado28.model.Customer;
import com.example.ParcialSabado28.repository.CustomerRepository;
import com.example.ParcialSabado28.repository.IdentifierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerDtoMapper customerDtoMapper;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private IdentifierRepository identifierRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCustomers() {
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        CustomerDto customerDto1 = new CustomerDto();
        CustomerDto customerDto2 = new CustomerDto();

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));
        when(customerDtoMapper.apply(customer1)).thenReturn(customerDto1);
        when(customerDtoMapper.apply(customer2)).thenReturn(customerDto2);

        List<CustomerDto> result = customerService.getAllCustomers();

        assertEquals(2, result.size());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void createCustomer() {
        CustomerDto customerDto = new CustomerDto();
        Customer customer = new Customer();

        when(customerMapper.apply(customerDto)).thenReturn(customer);
        when(identifierRepository.nextValue(anyString())).thenReturn(1);
        when(customerDtoMapper.apply(customer)).thenReturn(customerDto);

        CustomerDto result = customerService.createCustomer(customerDto);

        assertEquals(customerDto, result);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void updateCustomer() {
        CustomerDto customerDto = new CustomerDto();
        Customer customer = new Customer();

        when(customerMapper.apply(customerDto)).thenReturn(customer);
        when(customerDtoMapper.apply(customer)).thenReturn(customerDto);

        CustomerDto result = customerService.updateCustomer(customerDto);

        assertEquals(customerDto, result);
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void deleteCustomerById() {
        Customer customer = new Customer();
        CustomerDto customerDto = new CustomerDto();
        String customerId = "1";

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerDtoMapper.apply(customer)).thenReturn(customerDto);

        CustomerDto result = customerService.deleteCustomerById(customerId);

        assertEquals(customerDto, result);
        verify(customerRepository, times(1)).delete(customer);
    }

    @Test
    void findCustomerById() {
        Customer customer = new Customer();
        CustomerDto customerDto = new CustomerDto();
        String customerId = "1";

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(customerDtoMapper.apply(customer)).thenReturn(customerDto);

        CustomerDto result = customerService.findCustomerById(customerId);

        assertEquals(customerDto, result);
    }
}
