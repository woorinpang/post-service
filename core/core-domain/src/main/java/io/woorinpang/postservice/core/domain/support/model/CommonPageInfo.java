package io.woorinpang.postservice.core.domain.support.model;

public record CommonPageInfo(
        int number,
        int size,
        long totalElements,
        int totalPages,
        boolean isFirst,
        boolean isLast
) {
}
