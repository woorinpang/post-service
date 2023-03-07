package com.woorinpang.postservice.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woorinpang.postservice.core.post.application.PostService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
//@AutoConfigureRestDocs(uriScheme = "http", uriHost = "woorinpang.post.com", uriPort = 9010)
@AutoConfigureRestDocs
@ActiveProfiles(profiles = "test")
@Transactional
public class IntegrationTest {

    @PersistenceContext protected EntityManager em;
    @Autowired protected MockMvc mockMvc;
    @Autowired protected ObjectMapper objectMapper;

}


