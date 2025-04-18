package com.easybank.accounts.dto;


import com.easybank.accounts.enums.AccountType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty(message = "Account holder name cannot be empty")
    @Digits(message = "Account number should be 10 digits",integer = 10,fraction = 0)
    private Long accountNumber;

    @NotEmpty(message = "Account type cannot be empty")
    private AccountType accountType;

    @NotEmpty(message = "Branch address cannot be empty")
    private String branchAddress;
}
