package com.easybank.accounts.dto;


import com.easybank.accounts.enums.AccountType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold account details"
)
public class AccountsDto {

    @Schema(
            description = "Account number of customer"
    )
    @NotEmpty(message = "Account holder name cannot be empty")
    @Digits(message = "Account number should be 10 digits",integer = 10,fraction = 0)
    private Long accountNumber;

    @Schema(
            description = "Account type of customer",example = "SAVING or CURRENT"
    )
    @NotEmpty(message = "Account type cannot be empty")
    private AccountType accountType;

    @Schema(
            description = "Branch of customer"
    )
    @NotEmpty(message = "Branch address cannot be empty")
    private String branchAddress;
}
