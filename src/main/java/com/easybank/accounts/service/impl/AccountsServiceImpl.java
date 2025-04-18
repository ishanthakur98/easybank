package com.easybank.accounts.service.impl;

import com.easybank.accounts.constants.AccountsConstants;
import com.easybank.accounts.dto.AccountsDto;
import com.easybank.accounts.dto.CustomerDto;
import com.easybank.accounts.dto.ResponseDto;
import com.easybank.accounts.entity.Accounts;
import com.easybank.accounts.entity.Customer;
import com.easybank.accounts.exception.ResourceNotFoundException;
import com.easybank.accounts.exception.CustomerAlreadyExistsException;
import com.easybank.accounts.mapper.AccountsMapper;
import com.easybank.accounts.mapper.CustomerMapper;
import com.easybank.accounts.repository.AccountRepo;
import com.easybank.accounts.repository.CustomerRepo;
import com.easybank.accounts.service.AccountsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements AccountsService {

    private final AccountRepo accountRepo;

    private final CustomerRepo customerRepo;


    /**
     * This method is used to create a new account for a customer.
     *
     * @param customerDto the customer details
     * @return ResponseDto containing the status and message of the operation
     */
    @Override
    @Transactional
    public ResponseDto createAccount(CustomerDto customerDto) {

        customerRepo.findByMobileNumber(customerDto.getMobileNumber()).ifPresent(customer -> {
            throw new CustomerAlreadyExistsException("Customer already exists with this mobile number");
        });
        Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer());
        Customer savedCustomer = customerRepo.save(customer);

        Accounts accounts = createAccount(savedCustomer,customerDto);
        accountRepo.save(accounts);


        return new ResponseDto("Account created","SUCCESS");
    }

    /**
     * This method is used to fetch account details for a customer.
     *
     * @param mobileNumber the mobile number of the customer
     * @return ResponseDto containing the account details
     */
    @Override
    public CustomerDto fetchAccountDetails(String mobileNumber) {
        Customer customer = customerRepo.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer Account","Mobile Number",mobileNumber));
        Accounts accounts = accountRepo.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Account","Customer Id",customer.getCustomerId().toString()));
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer,new CustomerDto());
        AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(accounts,new AccountsDto());
        customerDto.setAccountsDto(accountsDto);
        return customerDto;
    }

    /**
     * This method is used to update account details for a customer.
     *
     * @param customerDto the customer details
     * @return boolean indicating success or failure of the operation
     */
    @Override
    @Transactional
    public boolean updateAccount(CustomerDto customerDto) {
        boolean updateStatus = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto != null){
            Accounts accounts = accountRepo.findById(accountsDto.getAccountNumber()).orElseThrow(() -> new ResourceNotFoundException("Account","Account Number",accountsDto.getAccountNumber().toString()));
            AccountsMapper.mapToAccounts(accountsDto,accounts);
            accountRepo.save(accounts);
            Customer customer = customerRepo.findById(accounts.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Customer","Customer Id",accounts.getCustomerId().toString()));
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepo.save(customer);
            updateStatus = true;
        }
        return updateStatus;
    }

    /**
     * This method is used to delete an account for a customer.
     *
     * @param mobileNumber the mobile number of the customer
     * @return boolean indicating success or failure of the operation
     */
    @Override
    @Transactional
    public boolean deleteAccount(String mobileNumber) {

        Customer customer = customerRepo.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer Account","Mobile Number",mobileNumber));
        customerRepo.deleteById(customer.getCustomerId());
        accountRepo.deleteByCustomerId(customer.getCustomerId());
        return true;
    }


    private Accounts createAccount(Customer customer,CustomerDto customerDto){
        Accounts newAccounts = new Accounts();
        newAccounts.setCustomerId(customer.getCustomerId());
        var randomAccNumber = 1000000000L + new SecureRandom().nextInt(90000000);
        newAccounts.setAccountNumber(randomAccNumber);
        newAccounts.setAccountType(Optional.ofNullable(customerDto).map(CustomerDto::getAccountsDto).map(e -> e.getAccountType().value()).orElseThrow(() -> new ResourceNotFoundException("Account Type","Account Type",null)));
        newAccounts.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccounts;
    }

}


