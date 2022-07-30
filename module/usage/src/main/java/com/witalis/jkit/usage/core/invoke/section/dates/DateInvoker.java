package com.witalis.jkit.usage.core.invoke.section.dates;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.bits.BitInvoker;
import com.witalis.jkit.usage.core.invoke.section.numbers.NumberInvoker;
import com.witalis.jkit.usage.core.invoke.section.primitives.PrimitiveInvoker;
import com.witalis.jkit.usage.core.invoke.section.strings.StringInvoker;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * Desc: Date declaration
 * User: Wellaxis
 * Date: 2019/11/16
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class DateInvoker extends Invoker {

    public DateInvoker() {
        setTitle("Dates chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // legacy
        log.info("## Legacy");
        invokeLegacy();
        // tab
        log.info("");
        // sql dates
        log.info("## SQL Dates");
        invokeSql();
        // tab
        log.info("");
        // time API
        log.info("## Time API");
        invokeTimeApi();
        // tab
        log.info("");
        // utilities
        log.info("## Utils");
        invokeUtils();
    }

    /**
     * Basic data types definitions.
     */
    private void invokeBasis() {
        // Before JDK 7 - all date classes are not thread safe.
        // Since JDK 8 - new Java Date Time API - classes are immutable.

        log.info("The class Date represents a specific instant in time, with millisecond precision.");
    }

    /**
     * Operations with legacy dates.
     */
    private void invokeLegacy() {
        long currentTime = System.currentTimeMillis();

        // util date
        {
            // java.util.date - a lot of methods are @Deprecated
            Date utilDate = new Date(currentTime);
            log.info("Util date: " + utilDate);
            // support
            Instant utilInstant = utilDate.toInstant();
            log.info("Date instance: {}", utilInstant);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            TimeZone defaultTimeZone = TimeZone.getDefault();
            TimeZone timeZone = new SimpleTimeZone(0, "GMT");
            sdf.setTimeZone(timeZone);
            log.info("SimpleDateFormat: {}", sdf.format(utilDate));
        }

        // calendar
        {
            Calendar calendar = new GregorianCalendar();
            // era - After Christ
            calendar.set(Calendar.ERA, GregorianCalendar.AD);
            // date/time interpretation - strict mode
            calendar.setLenient(false);
            var calDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
            var calDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            var calDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            calendar.roll(Calendar.MINUTE, 30);
            log.info("Calendar: {}", calendar);
            log.info("    Calendar date: {}", calendar.getTime());
            // support
            Instant calendarInstant = calendar.toInstant();
            log.info("    Calendar instance: {}", calendarInstant);
        }
    }

    /**
     * Operations with SQL dates.
     */
    private void invokeSql() {
        long currentTime = System.currentTimeMillis();

        // sql date
        {
            // java.sql.date - a lot of methods are @Deprecated
            java.sql.Date sqlDate = new java.sql.Date(currentTime);
            log.info("SQL date: " + sqlDate);
            // support
            try {
                Instant sqlInstant = sqlDate.toInstant();
                log.info("SQL date instance: " + sqlInstant);
            } catch (UnsupportedOperationException uoe) {
                log.error("SQL date doesn't contain time: " + uoe.getClass().getName());
            }
        }
    }

    /**
     * Operations with Time API.
     */
    public void invokeTimeApi() {
        long currentTime = System.currentTimeMillis();

        // time API -- since JDK 8 [project Joda-Time]

        // instant
        {
            // unix timestamp - only seconds
            Instant instant = Instant.now();
            Date instantDate = Date.from(instant);
            log.info("Instance: " + instant);
            log.info("    Instance date: " + instantDate);
            var instantGet = instant.get(ChronoField.NANO_OF_SECOND);
            log.info("    Constants:");
            log.info("    - EPOCH: " + Instant.EPOCH);
            log.info("    - MIN: " + Instant.MIN);
            log.info("    - MAX: " + Instant.MAX);
        }

        // local date
        {
            // year, month, day - no hour, minute, second, zone
            LocalDate localDate = LocalDate.now();
            log.info("Local date: " + localDate);
            var localDayOfYear = localDate.getDayOfYear();
            var localDayOfMonth = localDate.getDayOfMonth();
            var localDayOfWeek = localDate.getDayOfWeek().getValue();
            log.info(
                "    Info: " +
                    localDayOfYear + ", " +
                    localDayOfMonth + ", " +
                    localDayOfWeek
            );
            LocalDate localOf = LocalDate.of(2020, Month.JANUARY, 20);
            log.info("    Local date of: " + localOf);
            TemporalAdjuster adjuster = TemporalAdjusters.next(DayOfWeek.SUNDAY);
            LocalDate nextSunday = localOf.with(adjuster);
            log.info("    Local next sunday: " + nextSunday);
            // samples
            LocalDate tomorrow = LocalDate.now().plusDays(1);
            DayOfWeek sunday = LocalDate.parse("2016-06-12").getDayOfWeek();
            var localDateGet = localDate.get(ChronoField.DAY_OF_MONTH);
        }

        // local time
        {
            // only hour, minute, second
            LocalTime localTime = LocalTime.now();
            log.info("Local time: " + localTime);
            var localTimeHour = localTime.getHour();
            var localTimeMinute = localTime.getMinute();
            var localTimeSecond = localTime.getSecond();
            var localTimeNano = localTime.getNano();
            log.info(
                "    Info: " +
                    localTimeHour + ", " +
                    localTimeMinute + ", " +
                    localTimeSecond + ", " +
                    localTimeNano
            );
            // samples
            LocalTime sixThirty = LocalTime.of(6, 30);
            LocalTime maxTime = LocalTime.MAX;
            int six = LocalTime.parse("06:30").getHour();
            var localTimeGet = localTime.get(ChronoField.SECOND_OF_DAY);
        }

        // local date time
        {
            // year, month, day, hour, minute, second - no zone
            LocalDateTime localDateTime = LocalDateTime.now();
            log.info("Local date time: " + localDateTime);
            var localDateTimeDayOfYear = localDateTime.getDayOfYear();
            var localDateTimeDayOfMonth = localDateTime.getDayOfMonth();
            var localDateTimeDayOfWeek = localDateTime.getDayOfWeek().getValue();
            var localDateTimeHour = localDateTime.getHour();
            var localDateTimeMinute = localDateTime.getMinute();
            var localDateTimeSecond = localDateTime.getSecond();
            var localDateTimeNano = localDateTime.getNano();
            log.info(
                "    Info: " +
                    localDateTimeDayOfYear + ", " +
                    localDateTimeDayOfMonth + ", " +
                    localDateTimeDayOfWeek + ", " +
                    localDateTimeHour + ", " +
                    localDateTimeMinute + ", " +
                    localDateTimeSecond + ", " +
                    localDateTimeNano
            );
            // samples
            var todayDateTime = LocalDateTime.of(
                LocalDate.now(),
                LocalTime.now()
            );
            var ldfParse = LocalDateTime.parse("2015-02-20T06:30:00");
            var dateTime = LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);
            var localDateTimeGet = localDateTime.get(ChronoField.YEAR_OF_ERA);
        }

        // zoned date time
        {
            // all date time parameters include zone
            ZonedDateTime zonedDateTime = ZonedDateTime.now();
            log.info("Zoned date time: " + zonedDateTime);
            var zone = zonedDateTime.getZone();
            log.info("    Info: " + zone);
            // samples
            var zdtParse = ZonedDateTime.parse("2015-05-03T10:15:30+01:00[Europe/Paris]");
            var zonedDateTimeGet = zonedDateTime.get(ChronoField.MONTH_OF_YEAR);
        }

        // offset date time
        {
            // offset date time
            LocalDateTime initialTime = LocalDateTime.of(2019, Month.MAY, 7, 13, 13, 13);
            ZoneOffset reqOffset = ZoneOffset.of("-05:00");
            OffsetDateTime offsetDateTime = OffsetDateTime.of(initialTime, reqOffset);
            log.info("Offset date time: " + offsetDateTime);
            log.info("    Offset value: " + reqOffset);
            log.info("    Initial time: " + initialTime);
        }

        // year
        {
            // java.time.Year
            Year year = Year.of(2020);
            log.info("Year: " + year);
            var leap = year.isLeap();
            log.info("    Leap year: " + leap);
        }

        // month
        {
            // java.time.Month
            log.info("Month values:");
            var monthBuilder = new StringBuilder("    ");
            for (Month month : Month.values()) {
                monthBuilder.append(month.name()).append(",");
            }
            log.info(monthBuilder.deleteCharAt(monthBuilder.length() - 1).toString());
        }

        // year month
        {
            // java.time.YearMonth
            YearMonth yearMonth1 = YearMonth.now();
            log.info("YearMonth: " + yearMonth1);
            log.info(String.format("    Current - %s: %d", yearMonth1, yearMonth1.lengthOfMonth()));
            YearMonth yearMonth2 = YearMonth.of(2010, Month.FEBRUARY);
            log.info(String.format("    2010FEB - %s: %d", yearMonth2, yearMonth2.lengthOfMonth()));
            YearMonth yearMonth3 = YearMonth.of(2012, Month.FEBRUARY);
            log.info(String.format("    2012FEB - %s: %d", yearMonth3, yearMonth3.lengthOfMonth()));
        }

        // month day
        {
            // java.time.MonthDay
            MonthDay monthDay = MonthDay.of(Month.FEBRUARY, 29);
            log.info("MonthDay: " + monthDay);
            var mdMonth = monthDay.getMonth();
            var mdDayOfMonth = monthDay.getDayOfMonth();
            boolean leapYear = monthDay.isValidYear(2012);
            log.info("    Leap year: " + leapYear);
        }

        // day of week
        {
            // java.time.DayOfWeek
            log.info("DayOfWeek values:");
            var dayOfWeekBuilder = new StringBuilder("    ");
            for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
                dayOfWeekBuilder.append(dayOfWeek.name()).append(",");
            }
            log.info(dayOfWeekBuilder.deleteCharAt(dayOfWeekBuilder.length() - 1).toString());
        }

        // zone offset
        {
            // java.time.ZoneId vs java.time.ZoneOffset
            Set<String> zones = ZoneId.getAvailableZoneIds();
            ZoneId zoneId = ZoneId.of("Europe/Kiev");
            log.info("ZoneId: " + zoneId);
            LocalDateTime ldt = LocalDateTime.now();
            ZonedDateTime zdt = ldt.atZone(zoneId);
            ZoneOffset zoneOffset = zdt.getOffset();
            log.info("ZoneOffset: " + zoneOffset);
            var totalSeconds = zoneOffset.getTotalSeconds();
            log.info("    Offset: " + totalSeconds);
            log.info("    Available zones: " + zones.size());
        }

        // date time formatter
        {
            // java.time.format.DateTimeFormatter
            var formatter = DateTimeFormatter.ofPattern("yyyy MMM dd - hh:mm:ss a");
            ZoneId kyivZone = ZoneId.of("Europe/Kiev");
            ZonedDateTime kyivTime = ZonedDateTime.of(
                2020, Month.APRIL.getValue(), 15, 12, 30, 45, 0, kyivZone
            );
            var kyivFormat = kyivTime.format(formatter);
            var kyivParse = formatter.parse(kyivFormat);
            log.info(String.format("Formatter: %s (%s)", kyivFormat, kyivZone));
        }

        // duration
        {
            // java.time.Duration
            Instant t1 = Instant.now();
            Instant t2 = Instant.parse("2018-11-30T18:35:24.00Z");
            var duration = Duration.between(t2, t1);
            log.info("Duration: " + duration);
            log.info("    Seconds: " + duration.getSeconds());
            // samples
            var plus10secs = LocalTime.now().plus(Duration.ofSeconds(10));
        }

        // period
        {
            // java.time.Period
            Instant t1 = Instant.now();
            Instant t2 = Instant.parse("2018-11-30T18:35:24.00Z");
            var period = Period.between(
                LocalDate.ofInstant(t2, ZoneId.systemDefault()),
                LocalDate.ofInstant(t1, ZoneId.systemDefault())
            );
            log.info("Period: " + period);
            log.info("    Days: " + period.getDays());
            // samples
            var plus10days = LocalDate.now().plus(Period.ofDays(10));
        }
    }

    /**
     * Date utilities.
     */
    private void invokeUtils() {
        // Joda-Time dates library

        log.info("Joda-Time uses immutable classes for handling date and time.");
        log.info("Joda-Time offers a clean and fluent API for handling dates and time.");
    }
}
