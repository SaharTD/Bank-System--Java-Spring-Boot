package com.example.project3.Controller;


import com.example.project3.Api.ApiResponse;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.MyUser;
import com.example.project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/emp")
@RequiredArgsConstructor
public class EmployeeController {


private final EmployeeService employeeService;


    @GetMapping("get-emp")
    public ResponseEntity getAllEmployees (@AuthenticationPrincipal MyUser employee){
        return ResponseEntity.status(200).body(employeeService.getAll(employee.getId()));
    }




    @PostMapping("register")
    public ResponseEntity register (@RequestBody @Valid EmployeeDTO employeeDTO ){
        employeeService.register(employeeDTO);
        return ResponseEntity.status(200).body(new ApiResponse("the employee is register"));
    }



    @PutMapping("update")
    public ResponseEntity updateEmployee(@AuthenticationPrincipal MyUser employee , @Valid @RequestBody EmployeeDTO new_emp ){
        employeeService.updateEmployee(employee.getId(),new_emp);
        return ResponseEntity.status(200).body(new ApiResponse("the employee has been updated successfully "));
    }



    @DeleteMapping("delete")
    public ResponseEntity deleteEmployee(@AuthenticationPrincipal MyUser employee ){
        employeeService.deleteEmployee(employee.getId());
        return ResponseEntity.status(200).body(new ApiResponse("the employee has been deleted successfully "));
    }

    @PutMapping("activate/{accountId}")
    public ResponseEntity activateAccount(@AuthenticationPrincipal MyUser employee , @PathVariable Integer accountId){
        employeeService.activateAccount(employee.getId(),accountId);
        return ResponseEntity.status(200).body(new ApiResponse("the account  has been activated successfully "));
    }


    @PutMapping("block/{accountId}")
    public ResponseEntity blockAccount(@AuthenticationPrincipal MyUser employee , @PathVariable Integer accountId){
        employeeService.blockAccount(employee.getId(),accountId);
        return ResponseEntity.status(200).body(new ApiResponse("the account has been blocked "));
    }

}
