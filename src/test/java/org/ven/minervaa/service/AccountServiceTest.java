package org.ven.minervaa.service;

import org.ven.minervaa.entity.Account;
import org.ven.minervaa.exception.AccountNotFoundException;
import org.ven.minervaa.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @MockBean
    private AccountRepository accountRepository;

    @Test
    void should_return_account_details() {
        given(accountRepository.findById(anyLong())).willReturn(java.util.Optional.of(new Account()));
        assertThat(accountService.getAccount(1L)).isNotNull();
    }

    @Test
    void should_not_return_account_details_if_not_present() {
        given(accountRepository.findById(anyLong())).willThrow(AccountNotFoundException.class);
        assertThrows(AccountNotFoundException.class, () -> accountService.getAccount(1L));
    }

}
