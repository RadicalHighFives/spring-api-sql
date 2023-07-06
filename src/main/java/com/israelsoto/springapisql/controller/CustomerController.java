package com.israelsoto.springapisql.controller;

import com.israelsoto.springapisql.model.Customer;
import com.israelsoto.springapisql.repository.ICustomerRepository;
import com.israelsoto.springapisql.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService1) {
        this.customerService = customerService1;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return this.customerService.getCustomers();
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer){
        this.customerService.addCustomer(customer);
    }

    @DeleteMapping("/customers/{customerId}")
    public void deleteCustomer(@PathVariable("customerId") UUID id){

        customerService.deleteCustomer(id);
    }

    @PutMapping("/customers/{customerId}")
    public void updateCustomer(@PathVariable("customerId")UUID customerId,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String email,
                               @RequestParam(required = false) long phone) {
        customerService.updateCustomer(customerId, name, email, phone);

    }

}
