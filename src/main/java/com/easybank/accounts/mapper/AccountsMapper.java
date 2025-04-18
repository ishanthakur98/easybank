package com.easybank.accounts.mapper;

import com.easybank.accounts.enums.AccountType;
import com.easybank.accounts.dto.AccountsDto;
import com.easybank.accounts.entity.Accounts;

public class AccountsMapper {

    public static AccountsDto mapToAccountsDto(Accounts accounts,AccountsDto accountsDto){
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType("SA".equalsIgnoreCase(accounts.getAccountType()) ? AccountType.SAVINGS : AccountType.CURRENT);
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

    public static Accounts mapToAccounts(AccountsDto accountsDto,Accounts accounts){
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType().value());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        return accounts;
    }
}
