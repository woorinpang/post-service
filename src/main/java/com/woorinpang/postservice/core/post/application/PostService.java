package com.woorinpang.postservice.core.post.application;

import com.woorinpang.postservice.core.post.application.dto.PostCommandMapper;
import com.woorinpang.postservice.core.post.application.dto.command.SavePostCommand;
import com.woorinpang.postservice.core.post.domain.PostRepository;
import com.woorinpang.postservice.core.post.infrastructure.PostQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostQueryRepository postQueryRepository;
    private final PostRepository postRepository;
    private final PostCommandMapper mapper;

    @Transactional
    public Long save(SavePostCommand command) {
        return postRepository.save(PostCommandMapper.INSTANCE.toPost(command)).getId();
    }
}
