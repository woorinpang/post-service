package io.woorinpang.postservice.core.domain.support.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommonPageable {
    public static final int DEFAULT_PAGE = 0;
    public static final int DEFAULT_SIZE = 20;

    private Integer page;

    private Integer size;

    public Pageable toPageable() {
        return PageRequest.of(page == null ? DEFAULT_PAGE : page, size == null ? DEFAULT_SIZE : size);
    }
}
