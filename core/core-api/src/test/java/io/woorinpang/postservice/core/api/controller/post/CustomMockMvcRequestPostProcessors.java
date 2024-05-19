package io.woorinpang.postservice.core.api.controller.post;

import io.woorinpang.postservice.core.api.support.request.LoginUser;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

public class CustomMockMvcRequestPostProcessors {
    public static RequestPostProcessor loginUser() {
        return mockRequest -> {
            LoginUser loginUser = new LoginUser(1L, "spring", "spring@woorinpang.io", "스프링", "ROLE_USER");
            mockRequest.setAttribute("loginUser", loginUser);
            return mockRequest;
        };
    }
}
