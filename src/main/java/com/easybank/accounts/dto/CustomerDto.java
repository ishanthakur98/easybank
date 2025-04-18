package com.easybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold customer and account details"
)
public class CustomerDto {

    @Schema(
            description = "Name of customer",example = "Josh hazlewood"
    )
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 5,max = 30,message = "Name should be between 5 to 30 characters")
    private String name;

    @Schema(
            description = "Emal of customer",example = "Joshhazlewood@gmail.com"
    )
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(
            description = "Mobile number of customer",example = "9815671234"
    )
    @Digits(message = "Mobile number should be 10 digits",integer = 10,fraction = 0)
    private String mobileNumber;

    @Schema(
            description = "Account details of customer"
    )
    private AccountsDto accountsDto;
}
