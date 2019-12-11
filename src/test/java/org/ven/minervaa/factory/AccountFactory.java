package org.ven.minervaa.factory;

import org.ven.minervaa.entity.Account;

public class AccountFactory {

    public AccountFactory() {
    }

    public static Account account() {
        return new Account(1L, "PS Demo Account", "accessKey", "secretKey");
    }
}