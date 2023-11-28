package com.example.ParcialSabado28.controller;

import com.example.ParcialSabado28.services.ICustomerService;
import com.example.ParcialSabado28.controller.CustomerDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class CustomerController {

    ICustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK); // 200 OK
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable String customerId) {
        try {
            CustomerDto customer = customerService.findCustomerById(customerId);
            return new ResponseEntity<>(customer, HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto createdCustomer = customerService.createCustomer(customerDto);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED); // 201 Created
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String customerId,
                                                      @RequestBody CustomerDto customerDto) {
        try {
            customerDto.setCustomerId(customerId);
            CustomerDto updatedCustomer = customerService.updateCustomer(customerDto);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{customerId}")
    public ResponseEntity<CustomerDto> deleteCustomer(@PathVariable String customerId) {
        try {
            CustomerDto deletedCustomer = customerService.deleteCustomerById(customerId);
            return new ResponseEntity<>(deletedCustomer, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
