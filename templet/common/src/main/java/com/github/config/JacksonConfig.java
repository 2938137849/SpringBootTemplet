package com.github.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.module.kotlin.KotlinFeature;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import com.github.constant.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author bin
 * @since 2022/11/07
 */
@Component
public class JacksonConfig {
	@Bean
    public ObjectMapper objectMapper() {
        final var objectMapper = new ObjectMapper();
        final var simpleModule = new SimpleModule();
        final var formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_FORMAT);
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance)
                .addSerializer(Long.TYPE, ToStringSerializer.instance)
                .addSerializer(new LocalDateTimeSerializer(formatter))
                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));
        objectMapper.registerModule(simpleModule)
                .registerModule(
                        new KotlinModule.Builder()
                                .configure(KotlinFeature.NullToEmptyCollection, true)
                                .configure(KotlinFeature.NullToEmptyMap, true)
                                .configure(KotlinFeature.NullIsSameAsDefault, true)
                                .build()
                )
//         .registerModule(new JavaTimeModule())
//         .registerModule(new ParameterNamesModule())
//         .registerModule(new Jdk8Module())
        ;
        return objectMapper;
    }
}
