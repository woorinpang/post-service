package com.woorinpang.postservice.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Profile(("!test"))
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
}
