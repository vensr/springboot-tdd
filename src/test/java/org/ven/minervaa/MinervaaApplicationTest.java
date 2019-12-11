package org.ven.minervaa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MinervaaApplicationTest {

    @Test
    void should_start_minervaa_application_successfully() {
        MinervaaApplication.main(new String[]{});
    }
}
