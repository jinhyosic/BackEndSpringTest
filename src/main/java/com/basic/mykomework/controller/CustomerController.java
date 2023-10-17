package com.basic.mykomework.controller;

import com.basic.mykomework.entity.Customer;
import com.basic.mykomework.exception.BusinessException;
import com.basic.mykomework.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public Customer create(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    @RequestMapping(produces = {"application/json"})
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    @RequestMapping(value = "/{id}")
    public Customer getCustomer(@PathVariable Long id){
        return customerRepository.findById(id).orElseThrow(()->new BusinessException("Customer Not Found",HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/age/{age}", produces = {"application/json"})
    public List<Customer> getCustomersByAge(@PathVariable Long age){
        return customerRepository.findByAge(age);
    }

    @RequestMapping(value = "/email/{email}", produces = {"application/json"})
    public Customer getCustomersByEmail(@PathVariable String email){
        return customerRepository.findByEmail(email).orElseThrow(()->new BusinessException("Customer Not Found",HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(()->new BusinessException("Customer Not Found",HttpStatus.NOT_FOUND));
        customerRepository.delete(customer);
        return ResponseEntity.ok("delete customer success");
    }

}
