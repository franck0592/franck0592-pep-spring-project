package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.service.interfaces.AccountServiceInterface;

@Service
public class AccountService implements AccountServiceInterface{

    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }

    @Override
    public Account create(Account account) {
        // Implementation of create method from interface for registering request

        // checking blank username and password length no over 4
        if(account.getUsername().isBlank()){
            return null;
        }
        if(account.getPassword().length()<=4){
            return null;
        }
         // checking for existing account
        Account existingAccount=accountRepository.findByUsername(account.getUsername());
        if(existingAccount!=null){
            return null;
        }else{
            return accountRepository.save(account);
        }

    }

    @Override
    public Account login(Account account) {
          // Implementation of login method from interface for logging request
          Account accountRetreived=accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
          if(accountRetreived!=null){
            return accountRetreived;
          }else{
            return null;
          }    
    }

}
