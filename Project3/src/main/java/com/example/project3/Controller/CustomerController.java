package com.example.project3.Controller;

import com.example.project3.Api.ApiResponse;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Model.Employee;
import com.example.project3.Model.MyUser;
import com.example.project3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {


    private final CustomerService customerService;



    @GetMapping("get-customers")
    public ResponseEntity getAllCustomers (@AuthenticationPrincipal MyUser employee){
        return ResponseEntity.status(200).body(customerService.getAllCustomers(employee.getId()));
    }



    @PostMapping("register")
    public ResponseEntity register (@RequestBody @Valid CustomerDTO customerDTO ){
        customerService.register(customerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("the customer is register"));
    }


    @PutMapping("update")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal MyUser customer , @RequestBody CustomerDTO customerDTO ){
        customerService.updateCustomer(customer.getId(),customerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("the customer has been updated successfully "));
    }



    @DeleteMapping("delete")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal MyUser customer){
        customerService.deleteCustomer(customer.getId());
        return ResponseEntity.status(200).body(new ApiResponse("the customer has been deleted successfully "));
    }











}
