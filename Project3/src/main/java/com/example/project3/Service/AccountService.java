package com.example.project3.Service;


import com.example.project3.Api.ApiException;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Model.MyUser;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {


    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;






    public List<Account> getAllAccounts(Integer EmployeeId){
        return accountRepository.findAll();
    }

    public Account getMyAccount(Integer customerId){
        return accountRepository.findAccountByCustomer_Id(customerId);
    }

    public void addAccount(Integer customerId,Account account) {

         Customer customer = customerRepository.findCustomerById(customerId);
if (customer==null){
    throw new ApiException("Customer is not found");

}

account.setCustomer(customer);
accountRepository.save(account);

    }



    public void deleteAccount(Integer customerId,Integer accountId){

        Customer customer= customerRepository.findCustomerById(customerId);
        if (customer==null){
            throw new ApiException("the customer is not found");
        }
        Account account1= accountRepository.findAccountById(accountId);


        if (account1==null){
            throw new ApiException("the account is not found");
        }

        if (account1.getCustomer().getId()!=customerId){
            throw new ApiException("The account does not belong to this customer");

        }


        accountRepository.delete(account1);

    }


/// withdraw  wnd deposit are the update for the account
    public void withdraw(Integer customerId,Integer accountId , Integer amount){
        Customer customer= customerRepository.findCustomerById(customerId);
        if (customer==null){
            throw new ApiException("the customer is not found");
        }
        Account account1= accountRepository.findAccountById(accountId);


        if (account1==null){
            throw new ApiException("the account is not found");
        }
        if (!account1.getIsActive()){
            throw new ApiException("the account is blocked");
        }

        if (account1.getCustomer().getId()!=customerId){
            throw new ApiException("The account does not belong to this customer");
        }

        if (account1.getBalance()<amount){
            throw new ApiException("Insufficient balance ");

        }

        if (amount<=0){
            throw new ApiException("Add a positive amount ");

        }

        account1.setBalance(account1.getBalance()-amount);
        accountRepository.save(account1);

    }



    public void deposit(Integer customerId,Integer accountId , Integer amount){
        Customer customer= customerRepository.findCustomerById(customerId);
        if (customer==null){
            throw new ApiException("the customer is not found");
        }
        Account account1= accountRepository.findAccountById(accountId);


        if (account1==null){
            throw new ApiException("the account is not found");
        }
        if (!account1.getIsActive()){
            throw new ApiException("the account is blocked");
        }

        if (account1.getCustomer().getId()!=customerId){
            throw new ApiException("The account does not belong to this customer");
        }

        if (amount<=0){
            throw new ApiException("Add a positive amount ");
        }
        account1.setBalance(account1.getBalance()+amount);
        accountRepository.save(account1);

    }




    public void transferFunds(Integer customerId,Integer mainAccountId,Integer secondAccountId, Integer amount){

        Customer customer= customerRepository.findCustomerById(customerId);
        if (customer==null){
            throw new ApiException("the customer is not found");
        }
        Account account1= accountRepository.findAccountById(mainAccountId);


        if (account1==null){
            throw new ApiException("the account is not found");
        }
        if (!account1.getIsActive()){
            throw new ApiException("the account is blocked");

        }

        if (account1.getCustomer().getId()!=customerId){
            throw new ApiException("The account does not belong to this customer");

        }

        Account account2= accountRepository.findAccountById(secondAccountId);

        if (account2==null){
            throw new ApiException("the account is not found");
        }

        if (!account2.getIsActive()){
            throw new ApiException("the account you want to transfer to is blocked");

        }

        withdraw(customerId,account1.getId(),amount);
        account2.setBalance(account2.getBalance()+amount);

        accountRepository.save(account1);
        accountRepository.save(account2);

    }









}
