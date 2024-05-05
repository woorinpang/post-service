package io.woorinpang.postservice.client.user;

import feign.RetryableException;
import io.woorinpang.postservice.client.ClientUserContextTest;
import io.woorinpang.postservice.client.user.model.FindUserClientResult;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserClientTest extends ClientUserContextTest {
    private final UserClient userClient;

    public UserClientTest(UserClient userClient) {
        this.userClient = userClient;
    }

    @Test
    void shouldBeThrownExceptionWhenUser() {
        try {
            FindUserClientResult user = userClient.findUser("", 1L);
        } catch (Exception e) {
            assertThat(e).isExactlyInstanceOf(RetryableException.class);
        }
    }
}
