package com.basic.mykomework.service;

import com.basic.mykomework.dto.CustomerReqDTO;
import com.basic.mykomework.dto.CustomerResDTO;
import com.basic.mykomework.entity.Customer;
import com.basic.mykomework.exception.BusinessException;
import com.basic.mykomework.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerResDTO saveCustomer(CustomerReqDTO customerReqDto) {
        //reqDto => entity
        Customer customer = modelMapper.map(customerReqDto, Customer.class);
        Customer saveCustomer = customerRepository.save(customer);
        //entity => resDto
        return modelMapper.map(saveCustomer, CustomerResDTO.class);
    }
    @Transactional(readOnly = true)
    public CustomerResDTO getCustomerById(Long id){
        Customer customerEntity = customerRepository.findById(id)
                .orElseThrow(()->new BusinessException(id+"user not found", HttpStatus.NOT_FOUND));

        CustomerResDTO customerResDTO = modelMapper.map(customerEntity, CustomerResDTO.class);

        return customerResDTO;
    }

    public void deleteCustomer(Long id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new BusinessException(id + " User Not Found", HttpStatus.NOT_FOUND));
        customerRepository.delete(customer);
    }
    @Transactional(readOnly = true)
    public List<CustomerResDTO> getCustomers(){
        List<Customer> customerList = customerRepository.findAll();

        List<CustomerResDTO> customerResDTOList = customerList.stream()
                .map(customer->modelMapper.map(customer, CustomerResDTO.class))
                .collect(Collectors.toList());

        return customerResDTOList;

    }
    public CustomerResDTO updateCustomer(String email, CustomerReqDTO customerReqDto){

        Customer existCustomer = customerRepository.findByEmail(email)
                .orElseThrow(() ->
                        new BusinessException(email + " User Not Found", HttpStatus.NOT_FOUND));
        existCustomer.setAge((long) customerReqDto.getAge());
        return modelMapper.map(existCustomer, CustomerResDTO.class);
    }

}
