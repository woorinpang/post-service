package com.woorinpang.postservice.core.post.application.dto.command;

import lombok.Builder;

@Builder
public record SavePostCommand(
        String title,
        String content
) {
}
