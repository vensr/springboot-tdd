package org.ven.minervaa.service;

import org.springframework.stereotype.Service;
import org.ven.minervaa.entity.Account;
import org.ven.minervaa.exception.AccountNotFoundException;
import org.ven.minervaa.repository.AccountRepository;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
    }
}