package com.example.project3.Controller;

import com.example.project3.Api.ApiResponse;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Model.Employee;
import com.example.project3.Model.MyUser;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/account")
@RequiredArgsConstructor
public class AccountController {


    private final AccountService accountService;


    @GetMapping("get-all-accounts")
    public ResponseEntity getAllAccounts(@AuthenticationPrincipal MyUser employee){
        return ResponseEntity.status(200).body(accountService.getAllAccounts(employee.getId()));
    }



    @GetMapping("get-my-accounts")
    public ResponseEntity getMyAccounts(@AuthenticationPrincipal MyUser customer){
        return ResponseEntity.status(200).body(accountService.getMyAccount(customer.getId()));
    }


    @GetMapping("get-one-account/{accountId}")
    public ResponseEntity getSingleAccount(@AuthenticationPrincipal MyUser customer,@PathVariable Integer accountId){
        return ResponseEntity.status(200).body(accountService.getOneAccount(customer.getId(),accountId));
    }



    @PostMapping("add")
    public ResponseEntity addAccount (@AuthenticationPrincipal MyUser customer, @Valid @RequestBody Account account){
        accountService.addAccount(customer.getId(),account);
        return ResponseEntity.status(200).body(new ApiResponse("the account is opened successfully"));
    }


    @DeleteMapping("delete/{accountId}")
    public ResponseEntity deleteAccount(@AuthenticationPrincipal MyUser customer , @PathVariable Integer accountId){
        accountService.deleteAccount(customer.getId(),accountId);
        return ResponseEntity.status(200).body(new ApiResponse("the customer has been deleted successfully "));
    }



    @PutMapping("withdraw/{accountId}/{amount}")
    public ResponseEntity withdraw(@AuthenticationPrincipal MyUser customer ,@PathVariable Integer accountId , @PathVariable Integer amount ){
        accountService.withdraw(customer.getId(),accountId,amount);
        return ResponseEntity.status(200).body(new ApiResponse("the withdraw has been done successfully "));
    }


    @PutMapping("deposit/{accountId}/{amount}")
    public ResponseEntity deposit(@AuthenticationPrincipal MyUser customer ,@PathVariable Integer accountId , @PathVariable Integer amount ){
        accountService.deposit(customer.getId(),accountId,amount);
        return ResponseEntity.status(200).body(new ApiResponse("the deposit has been done successfully "));
    }


    @PutMapping("transferFunds/{accountId}/{accountId2}/{amount}")
    public ResponseEntity transferFunds(@AuthenticationPrincipal MyUser customer ,@PathVariable Integer accountId ,@PathVariable Integer accountId2 , @PathVariable Integer amount ){
        accountService.transferFunds(customer.getId(),accountId,accountId2,amount);
        return ResponseEntity.status(200).body(new ApiResponse("the transfer has been done successfully "));
    }




}
