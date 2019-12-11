package org.ven.minervaa.controller;

import org.ven.minervaa.entity.Account;
import org.ven.minervaa.exception.AccountNotFoundException;
import org.ven.minervaa.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/api/account/details/{id}")
    public Account getDetails(@PathVariable Long id) {
        return accountService.getAccount(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void accountNotFound(AccountNotFoundException e) {
        log.error("Account not found", e);
    }

}
