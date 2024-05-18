package io.woorinpang.postservice.core.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.woorinpang.postservice.core.api.support.error.CoreApiException;
import io.woorinpang.postservice.core.api.support.error.ErrorType;
import io.woorinpang.postservice.core.api.support.request.LoginUser;
import io.woorinpang.postservice.core.api.support.response.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static org.springframework.util.StringUtils.hasText;

@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice {
    private final ObjectMapper objectMapper;

    public ApiControllerAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ModelAttribute("loginUser")
    public LoginUser loginUser(HttpServletRequest request) {
        String header = request.getHeader("Authenticated-User");

        if (hasText(header)) {
            try {
                String decodedUser = URLDecoder.decode(header, StandardCharsets.UTF_8);
                return objectMapper.readValue(decodedUser, LoginUser.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @ExceptionHandler(CoreApiException.class)
    public ResponseEntity<ApiResponse<?>> handleCoreApiException(CoreApiException e) {
        switch (e.getErrorType().getLogLevel()) {
            case ERROR -> log.error("CoreApiException : {}", e.getMessage(), e);
            case WARN -> log.warn("CoreApiException : {}", e.getMessage(), e);
            default -> log.info("CoreApiException : {}", e.getMessage(), e);
        }
        return new ResponseEntity<>(ApiResponse.error(e.getErrorType(), e.getData()), e.getErrorType().getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception e) {
        log.error("Exception : {}", e.getMessage(), e);
        return new ResponseEntity<>(ApiResponse.error(ErrorType.DEFAULT_ERROR), ErrorType.DEFAULT_ERROR.getStatus());
    }
}
