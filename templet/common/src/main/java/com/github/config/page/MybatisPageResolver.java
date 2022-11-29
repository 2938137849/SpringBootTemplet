package com.github.config.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.OptionalLong;

/**
 * @author bin
 * @since 2022/11/29
 */
@Component
@EnableConfigurationProperties(MybatisPageProperties.class)
public class MybatisPageResolver implements HandlerMethodArgumentResolver {
    private final MybatisPageProperties properties;

    public MybatisPageResolver(MybatisPageProperties properties) {
        this.properties = properties;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return IPage.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public IPage<?> resolveArgument(
            @NotNull MethodParameter parameter, ModelAndViewContainer mavContainer,
            @NotNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory
    ) {
        final long page, size;
        var param = webRequest.getParameter(properties.getPageParameter());
        var pageOptional = parseAndApplyBoundaries(param, Long.MAX_VALUE);
        page = pageOptional.orElse(0);
        param = webRequest.getParameter(properties.getSizeParameter());
        pageOptional = parseAndApplyBoundaries(param, properties.getMaxPageSize());
        size = pageOptional.orElse(properties.getDefaultPageSize());
        return Page.of(page, size);
    }

    private OptionalLong parseAndApplyBoundaries(@Nullable String parameter, long upper) {
        if (!StringUtils.hasText(parameter)) {
            return OptionalLong.empty();
        } else {
            try {
                long parsed = Long.parseLong(parameter);
                return OptionalLong.of(parsed < 0 ? 0 : Math.min(parsed, upper));
            } catch (NumberFormatException var5) {
                return OptionalLong.of(0L);
            }
        }
    }
}
