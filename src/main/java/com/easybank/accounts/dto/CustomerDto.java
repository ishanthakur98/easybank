package com.easybank.accounts.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 5,max = 30,message = "Name should be between 5 to 30 characters")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @Digits(message = "Mobile number should be 10 digits",integer = 10,fraction = 0)
    private String mobileNumber;

    private AccountsDto accountsDto;
}
