package com.example.project3.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {


    @NotEmpty(message = "the username can not be empty")
    @Size(min = 4, max = 10, message = "the length must be between 4 and 10 characters. ")
    private String username;

    @NotEmpty(message = "the password can not be empty")
    @Size(min = 6, message = "Length must be at least 6 characters.  ")
    private String password;


    @NotEmpty(message = "the name can not be empty")
    @Size(min = 2, max = 20, message = "Length must be between 2 and 20 characters.")
    private String name;


    @NotEmpty(message = "the email can not be empty")
    @Email(message = "Must be a valid email format.")
    private String email;


    @NotEmpty(message = "the position can not be empty")
    private String position;

    @NotNull(message = "the salary can not be empty")
    @Positive(message = "Must be a non-negative decimal number.")
    private Integer salary;
}
