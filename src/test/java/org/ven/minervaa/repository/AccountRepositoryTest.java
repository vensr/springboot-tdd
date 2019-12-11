package org.ven.minervaa.repository;

import org.ven.minervaa.entity.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.ven.minervaa.factory.AccountFactory;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository.save(AccountFactory.account());
    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }

    @Test
    void should_return_account_details() {
        Optional<Account> account = accountRepository.findById(1L);
        assertThat(account.isPresent()).isTrue();
        assertThat(account.get().getName()).isEqualTo("PS Demo Account");
        assertThat(account.get().getAccessKey()).isNotEmpty();
        assertThat(account.get().getSecretKey()).isNotEmpty();
    }
}
