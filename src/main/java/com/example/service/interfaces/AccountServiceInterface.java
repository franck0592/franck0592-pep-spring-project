package com.example.service.interfaces;

import com.example.entity.Account;

public interface AccountServiceInterface {
    public Account create(Account account);
    public Account login(Account account);

}
