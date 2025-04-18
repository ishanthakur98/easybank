package com.easybank.accounts.service;

import com.easybank.accounts.dto.CustomerDto;
import com.easybank.accounts.dto.ResponseDto;

public interface AccountsService {

    /**
     * This method is used to create a new account for a customer.
     *
     * @param customerDto the customer details
     * @return ResponseDto containing the status and message of the operation
     */
    ResponseDto createAccount(CustomerDto customerDto);

    /**
     * This method is used to fetch account details for a customer.
     *
     * @param mobileNumber the mobile number of the customer
     * @return ResponseDto containing the account details
     */

    CustomerDto fetchAccountDetails(String mobileNumber);

    /**
     * This method is used to update account details for a customer.
     *
     * @param customerDto the customer details
     * @return boolean indicating success or failure of the operation
     */
    boolean updateAccount(CustomerDto customerDto);


    /**
     * This method is used to delete an account for a customer.
     *
     * @param mobileNumber the mobile number of the customer
     * @return boolean indicating success or failure of the operation
     */
    boolean deleteAccount(String mobileNumber);
}
