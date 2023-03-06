package com.woorinpang.postservice.core.post.application.dto;

import com.woorinpang.postservice.core.post.application.dto.command.SavePostCommand;
import com.woorinpang.postservice.core.post.domain.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostCommandMapper {
    PostCommandMapper INSTANCE = Mappers.getMapper(PostCommandMapper.class);

    Post toPost(SavePostCommand command);
}
