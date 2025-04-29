package com.example.project3.Service;


import com.example.project3.Api.ApiException;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Model.MyUser;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {


    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;







    public List<Customer> getAllCustomers(Integer employeeId){
        return customerRepository.findAll();
    }

    public void register(CustomerDTO customerDTO) {
        MyUser user = new MyUser();

        user.setRole("CUSTOMER");
        user.setUsername(customerDTO.getUsername());
        String hashPassword = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
        user.setPassword(hashPassword);

        user.setName(customerDTO.getName());
        user.setEmail(customerDTO.getEmail());

        Customer customer = new Customer();
        customer.setPhoneNumber(customerDTO.getPhoneNumber());


        user.setCustomer(customer);

        authRepository.save(user);
        customerRepository.save(customer);
    }


    public void updateCustomer(Integer customerId,CustomerDTO customerDTO){

        Customer customer= customerRepository.findCustomerById(customerId);

        if (customer==null){
            throw new ApiException("the customer is not found");
        }

        customer.getMyUser().setName(customerDTO.getName());
        customer.getMyUser().setEmail(customerDTO.getEmail());
        customer.getMyUser().setUsername(customerDTO.getUsername());

        String hashPassword = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
        customer.getMyUser().setPassword(hashPassword);


        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        customerRepository.save(customer);


    }



    public void deleteCustomer(Integer customerId ){

        Customer customer= customerRepository.findCustomerById(customerId);
        if (customer==null){
            throw new ApiException("the customer is not found");
        }
        customerRepository.delete(customer);

    }





}
