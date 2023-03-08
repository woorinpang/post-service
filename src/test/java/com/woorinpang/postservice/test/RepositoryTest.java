package com.woorinpang.postservice.test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles(profiles = "test")
public class RepositoryTest {

    @PersistenceContext protected EntityManager em;
}
