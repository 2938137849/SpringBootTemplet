package com.github.config.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author bin
 * @since 2022/11/29
 */
@Component
@RequiredArgsConstructor
public class MybatisPageWebMvcConfigurer implements WebMvcConfigurer {
    private final MybatisPageResolver pageResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(pageResolver);
    }
}
