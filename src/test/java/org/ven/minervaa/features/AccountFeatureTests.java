package org.ven.minervaa.features;

import org.ven.minervaa.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class AccountFeatureTests {

    @Autowired
    private TestRestTemplate accountApiEndPoint;

    @Test
    @Sql({"classpath:account/account_data.sql"})
    void api_should_return_account_details() {
        ResponseEntity<Account> account = accountApiEndPoint.getForEntity(
                "/api/account/details/1",
                Account.class);
        assertThat(account.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void api_should_not_return_account_details() {
        ResponseEntity<Account> account =
                accountApiEndPoint.getForEntity("/api/account/details/2", Account.class);
        assertThat(account.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
