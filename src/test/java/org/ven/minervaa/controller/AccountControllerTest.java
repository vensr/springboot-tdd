package org.ven.minervaa.controller;

import org.ven.minervaa.exception.AccountNotFoundException;
import org.ven.minervaa.factory.AccountFactory;
import org.ven.minervaa.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class AccountControllerTest {

    @Autowired
    private MockMvc mockRequest;

    @MockBean
    private AccountService accountService;

    private JsonPathResultMatchers account(String id) {
        return jsonPath(id);
    }

    private void requestIsDocumented(ResultActions accountRequest) throws Exception {
        accountRequest
                .andDo(print())
                .andDo(document("account_details"));

    }

    private void detailsAreGood(ResultActions accountRequest) throws Exception {
        accountRequest
                .andExpect(account("id").value(1))
                .andExpect(account("name").value("PS Demo Account"))
                .andExpect(account("accessKey").isNotEmpty())
                .andExpect(account("secretKey").isNotEmpty());
    }

    private void statusIsOk(ResultActions actions) throws Exception {
        actions.andExpect(status().isOk());
    }

    private ResultActions makeAccountDetailsRequest(String uri) throws Exception {
        return mockRequest.perform(get(uri));
    }

    @Test
    void api_should_return_account_details_for_a_given_ids() throws Exception {
        given(accountService.getAccount(1L)).willReturn(AccountFactory.account());

        ResultActions forAccountResponse = makeAccountDetailsRequest("/api/account/details/1");
        statusIsOk(forAccountResponse);
        detailsAreGood(forAccountResponse);
        requestIsDocumented(forAccountResponse);
    }

    @Test
    void api_should_not_return_account_if_not_found() throws Exception {
        given(accountService.getAccount(100L)).willThrow(AccountNotFoundException.class);

        makeAccountDetailsRequest("/api/account/details/100")
                .andExpect(status().isNotFound());
    }
}
