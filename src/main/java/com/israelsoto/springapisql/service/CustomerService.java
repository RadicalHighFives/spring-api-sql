package com.israelsoto.springapisql.service;

import com.israelsoto.springapisql.model.Customer;
import com.israelsoto.springapisql.repository.ICustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CustomerService {

    private final ICustomerRepository customerRepository;


    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers(){
        List<Customer> customerList = new ArrayList<Customer>();
        Iterable<Customer> customers= customerRepository.findAll();
        customers.forEach(customer -> {
            customerList.add(customer);
        });
        return customerList;
    }

    public void addCustomer(Customer customer){

        if(null == customer){
            throw new RuntimeException("Customer cannot be null.");
        }
        this.customerRepository.save(customer);
    }

    public void deleteCustomer(UUID customerId){
        boolean exists = customerRepository.existsById(customerId);
        if(!exists){
            throw new IllegalStateException("Customer with id " + customerId + "does not exist. ");
        }

        customerRepository.deleteById(customerId);
    }

    @Transactional
    public void updateCustomer(UUID id, String firstName, String email, long phone){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Customer with id " + id + "does not exist"));

        if(firstName != null
                && firstName.length() > 0
                && !Objects.equals(customer.getName(), firstName)){
            customer.setName(firstName);
        }

        if(email != null
                && email.length() > 0
                && !Objects.equals(customer.getEmail(), email)){
            Optional<Customer> memberOptional = this.customerRepository.findCustomerByEmail(email);
            if(memberOptional.isPresent()){
                throw new IllegalStateException("Email is Taken!");
            }
            customer.setEmail(email);
        }

        if(phone != 0
                && phone > 0
                && !Objects.equals(customer.getPhone(), phone)){
            customer.setPhone(phone);
        }
    }
}
