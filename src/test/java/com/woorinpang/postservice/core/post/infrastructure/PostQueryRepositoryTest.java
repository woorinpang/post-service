package com.woorinpang.postservice.core.post.infrastructure;

import com.woorinpang.postservice.core.post.PostTestConfig;
import com.woorinpang.postservice.test.RepositoryTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

//@Import(PostTestConfig.class)
class PostQueryRepositoryTest extends RepositoryTest {

    @Autowired protected PostQueryRepository postQueryRepository;

    @Test
    void test() {

    }

}