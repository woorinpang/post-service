package com.woorinpang.postservice.core.post.presentation.dto;

import com.woorinpang.postservice.core.post.application.dto.command.SavePostCommand;
import com.woorinpang.postservice.core.post.presentation.dto.request.SavePostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostDtoMapper {
    PostDtoMapper INSTANCE = Mappers.getMapper(PostDtoMapper.class);

    @Mappings({
            @Mapping(source = "request.postTitle", target = "title"),
            @Mapping(source = "request.postContent", target = "content")
    })
    SavePostCommand toCommand(SavePostRequest request);
}
