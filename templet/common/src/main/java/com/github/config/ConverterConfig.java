package com.github.config;

import com.github.util.LocalDateTimeUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author bin
 * @since 2022/11/07
 */
@JsonComponent
public class ConverterConfig {

    @Component
    public static class String2LocalDateTime implements Converter<String, LocalDateTime> {
        @Override
        public LocalDateTime convert(final @NotNull String source) {
            return LocalDateTimeUtil.parseLocalDateTime(source);
        }
    }

    @Component
    public static class String2LocalDate implements Converter<String, LocalDate> {
        @Override
        public LocalDate convert(final @NotNull String source) {
            return LocalDateTimeUtil.parseLocalDate(source);
        }
    }

    @Component
    public static class String2LocalTime implements Converter<String, LocalTime> {
        @Override
        public LocalTime convert(final @NotNull String source) {
            return LocalDateTimeUtil.parseLocalTime(source);
        }
    }

}
