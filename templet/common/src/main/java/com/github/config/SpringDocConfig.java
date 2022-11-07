package com.github.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author bin
 */
@Component
public class SpringDocConfig {

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("用户模块")
                .pathsToMatch("/**")
                .packagesToScan("com.starhz.control.controller")
                .build();
    }

    @Bean
    public OpenAPI springOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("储能监控-模型导入 接口")
                        .description("储能监控-模型导入 相关 API")
                        .version("v1.0")
                )
                .components(new Components()
                        .addSecuritySchemes("Authorization", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                )
                .externalDocs(new ExternalDocumentation()
                        .description("替代 Springfox 的 SpringDOC 入门 文档")
                        .url("https://www.cnblogs.com/jddreams/p/15922674.html")
                );
    }
}
