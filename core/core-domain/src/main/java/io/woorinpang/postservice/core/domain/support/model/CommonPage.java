package io.woorinpang.postservice.core.domain.support.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonPage<T> {
    private List<T> content;
    private CommonPageInfo page;

    public CommonPage(List<T> content, CommonPageInfo page) {
        this.content = content;
        this.page = page;
    }

    public <U> CommonPage<U> map(Function<? super T, ? extends U> converter) {
        Assert.notNull(converter, "Function must not be null");

        List<U> convertedContent = this.content
                .stream()
                .map(converter)
                .collect(Collectors.toList());
        return new CommonPage<>(convertedContent, this.page);
    }
}
