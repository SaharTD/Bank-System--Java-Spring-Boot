package com.example.project3.Service;


import com.example.project3.Api.ApiException;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Model.Employee;
import com.example.project3.Model.MyUser;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import com.example.project3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {


    private final AuthRepository authRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;



    public List<Employee> getAll(Integer employeeId){
        return employeeRepository.findAll();
    }

    public void register(EmployeeDTO employeeDTO) {
        MyUser user = new MyUser();

        user.setRole("EMPLOYEE");
        user.setUsername(employeeDTO.getUsername());
        String hashPassword = new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
        user.setPassword(hashPassword);

        user.setName(employeeDTO.getName());
        user.setEmail(employeeDTO.getEmail());

        Employee employee = new Employee();
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());


        employee.setMyUser(user);

        authRepository.save(user);
        employeeRepository.save(employee);
    }


    public void updateEmployee(Integer employeeId,EmployeeDTO employeeDTO){

        Employee employee= employeeRepository.findEmployeeById(employeeId);

        if (employee==null){
            throw new ApiException("the employee is not found");
        }

        employee.getMyUser().setName(employeeDTO.getName());
        employee.getMyUser().setEmail(employeeDTO.getEmail());
        employee.getMyUser().setUsername(employeeDTO.getUsername());

        String hashPassword = new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
        employee.getMyUser().setPassword(hashPassword);


        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());


        employeeRepository.save(employee);


    }



    public void deleteEmployee(Integer employeeId){

        Employee employee= employeeRepository.findEmployeeById(employeeId);

        if (employee==null){
            throw new ApiException("the employee is not found");
        }

        authRepository.delete(employee.getMyUser());


    }



    /// takes the customer id and bank account id to activate it
  public void activateAccount(Integer employeeId,Integer customerId,Integer accountId){

      Employee employee= employeeRepository.findEmployeeById(employeeId);

      if (employee==null){
          throw new ApiException("the employee is not found");
      }


      Customer customer= customerRepository.findCustomerById(customerId);
      if (customer==null){
          throw new ApiException("the customer is not found");
      }

      Account account = accountRepository.findAccountById(accountId);

      if (account.getCustomer().getId()!=customerId){
          throw new ApiException("the account does not belong to customer");
      }
      if (account.getIsActive()){
          throw new ApiException("the account is already active ");
      }

      account.setIsActive(true);
      accountRepository.save(account);


  }

    public void blockAccount(Integer employeeId, Integer customerId, Integer accountId){

        Employee employee= employeeRepository.findEmployeeById(employeeId);

        if (employee==null){
            throw new ApiException("the employee is not found");
        }


        Customer customer= customerRepository.findCustomerById(customerId);
        if (customer==null){
            throw new ApiException("the customer is not found");
        }

        Account account = accountRepository.findAccountById(accountId);

        if (account.getCustomer().getId()!=customerId){
            throw new ApiException("the account does not belong to customer");
        }

        if (!account.getIsActive()){
            throw new ApiException("the account is already blocked ");
        }


        account.setIsActive(false);
        accountRepository.save(account);

    }



}
