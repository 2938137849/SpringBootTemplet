package com.github.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @author bin
 * @since 2022/11/07
 */
@Component
public class RedisConfig {

    public Jackson2JsonRedisSerializer<Object> serializer(ObjectMapper mapper) {
        final var serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        serializer.setObjectMapper(mapper);
        return serializer;
    }

    @Bean
    @ConditionalOnProperty(name = "spring.redis")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory, ObjectMapper mapper) {
        final var template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setDefaultSerializer(serializer(mapper));
        return template;
    }

}
