package com.github.util;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author ruoyi
 */
public class LocalDateTimeUtil {
    private final static Logger logger = LoggerFactory.getLogger(LocalDateTimeUtil.class);

    public static LocalDateTime toLocalDateTime(Date date) {
        final var instant = date.toInstant();
        final var zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }

    public static Date toDate(LocalDateTime date) {
        final var zoneId = ZoneId.systemDefault();
        final var instant = date.atZone(zoneId).toInstant();
        return Date.from(instant);
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(String str) {
        final var date = parseLocalDateTime(str);
        if (date == null) {
            return null;
        }
        return toDate(date);
    }

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(
            "yyyy[[-][/][.]MM[[-][/][.]dd[ HH[:mm[:ss]]]]]"
    );

    @Nullable
    @Contract(pure = true)
    public static LocalDateTime parseLocalDateTime(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        try {
            return FORMATTER.parse(source, ToLocalDateTime.INSTANCE);
        } catch (DateTimeParseException e) {
            logger.error("格式错误无法转换为 LocalDateTime:{}", source);
            throw new IllegalArgumentException("不受支持的时间格式：" + source);
        }
    }

    @Nullable
    @Contract(pure = true)
    public static LocalDate parseLocalDate(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        try {
            return FORMATTER.parse(source, ToLocalDate.INSTANCE);
        } catch (DateTimeParseException e) {
            logger.error("格式错误无法转换为 LocalDateTime:{}", source);
            throw new IllegalArgumentException("不受支持的时间格式：" + source);
        }
    }

    @Nullable
    @Contract(pure = true)
    public static LocalTime parseLocalTime(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        try {
            return FORMATTER.parse(source, ToLocalTime.INSTANCE);
        } catch (DateTimeParseException e) {
            logger.error("格式错误无法转换为 LocalDateTime:{}", source);
            throw new IllegalArgumentException("不受支持的时间格式：" + source);
        }
    }

    private static final class ToLocalDateTime implements TemporalQuery<LocalDateTime> {
        public static final ToLocalDateTime INSTANCE = new ToLocalDateTime();

        private ToLocalDateTime() {
        }

        @Override
        public LocalDateTime queryFrom(final TemporalAccessor temporal) {
            final var localDate = temporal.query(TemporalQueries.localDate());
            if (localDate == null) {
                return LocalDateTime.of(ToLocalDate.parseDate(temporal), LocalTime.MIN);
            }
            final var localTime = temporal.query(TemporalQueries.localTime());
            if (localTime == null) {
                return LocalDateTime.of(localDate, ToLocalTime.parseTime(temporal));
            }
            return LocalDateTime.of(localDate, localTime);
        }
    }

    private static final class ToLocalDate implements TemporalQuery<LocalDate> {
        public static final ToLocalDate INSTANCE = new ToLocalDate();

        private ToLocalDate() {
        }

        @Override
        public LocalDate queryFrom(final TemporalAccessor temporal) {
            final var localDate = temporal.query(TemporalQueries.localDate());
            if (localDate == null) {
                return parseDate(temporal);
            }
            return localDate;
        }

        public static LocalDate parseDate(final TemporalAccessor temporal) {
            final var ints = new int[]{0, 1};
            int i = 0;
            if (temporal.isSupported(ChronoField.YEAR)) {
                ints[i++] = temporal.get(ChronoField.YEAR);
                if (temporal.isSupported(ChronoField.MONTH_OF_YEAR)) {
                    ints[i] = temporal.get(ChronoField.MONTH_OF_YEAR);
                }
            }
            return LocalDate.of(ints[0], ints[1], 1);
        }
    }

    private static final class ToLocalTime implements TemporalQuery<LocalTime> {
        public static final ToLocalTime INSTANCE = new ToLocalTime();

        private ToLocalTime() {
        }

        @Override
        public LocalTime queryFrom(final TemporalAccessor temporal) {
            final var localDate = temporal.query(TemporalQueries.localTime());
            if (localDate == null) {
                return parseTime(temporal);
            }
            return localDate;
        }

        public static LocalTime parseTime(final TemporalAccessor temporal) {
            final var ints = new int[2];
            int i = 0;
            if (temporal.isSupported(ChronoField.HOUR_OF_DAY)) {
                ints[i++] = temporal.get(ChronoField.HOUR_OF_DAY);
                if (temporal.isSupported(ChronoField.MINUTE_OF_HOUR)) {
                    ints[i] = temporal.get(ChronoField.MINUTE_OF_HOUR);
                }
            }
            return LocalTime.of(ints[0], ints[1], 0);
        }
    }
}
