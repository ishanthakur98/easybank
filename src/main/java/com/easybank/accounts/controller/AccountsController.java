package com.easybank.accounts.controller;

import com.easybank.accounts.constants.AccountsConstants;
import com.easybank.accounts.dto.CustomerDto;
import com.easybank.accounts.dto.ErrorResponseDto;
import com.easybank.accounts.dto.ResponseDto;
import com.easybank.accounts.service.AccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for accounts service",
        description = "This API is used to perform CRUD operations on accounts"
)
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api",produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsController {

    private final AccountsService accountsService;

    @Operation(
            summary = "Create a new account",
            description = "This API is used to create a new account for a customer"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Account created successfully"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){

        accountsService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.MESSAGE_201,AccountsConstants.STATUS_201));
    }

    @Operation(
            summary = "Fetch account details",
            description = "This API is used to fetch account details for a customer"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Customer details fetched successfully"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                               @Digits(message = "Mobile number should be 10 digits",integer = 10,fraction = 0) String mobileNumber){
        CustomerDto customerDto = accountsService.fetchAccountDetails(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @Operation(
            summary = "Update account details",
            description = "This API is used to update account details for a customer"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account updated successfully"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Account not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto customerDto){

        boolean status = accountsService.updateAccount(customerDto);

        if(!status){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(AccountsConstants.MESSAGE_417_UPDATE,AccountsConstants.STATUS_417));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AccountsConstants.MESSAGE_200,AccountsConstants.STATUS_200));
    }

    @Operation(
            summary = "Delete account",
            description = "This API is used to delete an account for a customer"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Account deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Account not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    } )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                                                         @Digits(message = "Mobile number should be 10 digits",integer = 10,fraction = 0)  String mobileNumber){

        boolean status = accountsService.deleteAccount(mobileNumber);

        if(!status){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(AccountsConstants.MESSAGE_417_DELETE,AccountsConstants.STATUS_417));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AccountsConstants.MESSAGE_200,AccountsConstants.STATUS_200));
    }
}
