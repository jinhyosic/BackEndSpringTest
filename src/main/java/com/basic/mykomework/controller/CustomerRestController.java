package com.basic.mykomework.controller;

import com.basic.mykomework.dto.CustomerReqDTO;
import com.basic.mykomework.dto.CustomerResDTO;
import com.basic.mykomework.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("con/customer")
@RequiredArgsConstructor
public class CustomerRestController {
    private final CustomerService customerService;

    @PostMapping
    public CustomerResDTO saveCustomer(@RequestBody CustomerReqDTO customerReqDTO){
        return customerService.saveCustomer(customerReqDTO);
    }
    @GetMapping("/{id}")
    public CustomerResDTO getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(id + " User가 삭제처리 되었습니다.");
    }
    @GetMapping
    public List<CustomerResDTO> getCustomers(){
        return customerService.getCustomers();
    }
    @PatchMapping("/{email}")
    public CustomerResDTO updateCustomer(@PathVariable String email, @RequestBody CustomerReqDTO customerReqDTO){
        return customerService.updateCustomer(email,customerReqDTO );
    }
}
