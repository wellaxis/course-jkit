package com.witalis.jkit.usage.core.invoke.section.formats;

import com.witalis.jkit.usage.core.invoke.Invoker;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * Desc: formatting API
 * User: Wellaxis
 * Date: 2021/03/15
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class FormatInvoker extends Invoker {

    public FormatInvoker() {
        setTitle("Formats chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // system
        log.info("## System");
        invokeSystem();
        // tab
        log.info("");
        // formats
        log.info("## Formats");
        invokeFormats();
    }

    /**
     * Basic postulates of formatting.
     */
    private void invokeBasis() {
        // There are different ways in which we can format output in Java. Some of them are given below.
        // * Using System.out.printf()
        // * Using String.format()
        // * Using DecimalFormat class
        // * Using SimpleDateFormat class (for formatting Dates)

        log.info("There are four types of references: strong, weak, soft, phantom.");
    }

    /**
     * System formatting.
     */
    private void invokeSystem() {
        log.info("Formatting output using System.out.printf()");

        // Note: System.out.format() is equivalent to printf() and can also be used.

        System.out.printf("Formatted simple number: ID = %d\n", 100);
        System.out.printf("Formatted with precision: PI = %.2f\n", Math.PI);

        System.out.format("Formatted with specific width: N = %.4f\n", 2.3F);
        System.out.format("Formatted to right margin: T = %10.4f\n", 3.2D);
    }

    /**
     * Formatting functionalities.
     */
    private void invokeFormats() {
        // string format
        {
            var text = "it is the java world :)";
            log.info(" - String: {}", text);

            // format
            {
                var pattern = "Header: '%s', Body: '%s', Footer: '%s'";
                var format = String.format(
                    pattern, "Begin", text, "End"
                );
                log.info(" * Formatting [text]: {}", format);
            }

            // formatter
            {
                StringBuilder builder = new StringBuilder();

                try (Formatter formatter = new Formatter(builder, Locale.getDefault())) {

                    formatter.format("%4$2s %3$2s %2$2s %1$2s", "a", "b", "c", "d");
                    formatter.flush();
                    log.info(" * Formatter [reverse]: {}", builder);
                    builder.setLength(0);

                    formatter.format(Locale.FRANCE, "e = %+10.4f", Math.E);
                    formatter.flush();
                    log.info(" * Formatter [exponent]: {}", builder);
                    builder.setLength(0);

                    formatter.format(Locale.JAPAN, "price = $ %(,.2f", 12345.6789D);
                    formatter.flush();
                    log.info(" * Formatter [money]: {}", builder);
                    builder.setLength(0);
                }
            }
        }

        log.info("");

        // number format
        {
            var number = 1_234.567D;
            log.info(" - Number: {}", String.valueOf(number));

            // format
            {
                var pattern = "%.2f";
                var format = String.format(pattern, number);
                log.info(" * Formatting [number]: {}", format);
            }

            log.info("");

            // decimal format class
            {
                log.info(" - Formatting via decimal format class");

                DecimalFormat format;
                double num = 12345.6789;

                // prints only numeric part of a floating number
                format = new DecimalFormat("####");
                log.info(" * Formatted without fraction part: num = {}", format.format(num));

                // prints upto 2 decimal places
                format = new DecimalFormat("#.##");
                log.info(" * Formatted to give precision: num = {}", format.format(num));

                // automatically appends zero to the rightmost part of decimal
                format = new DecimalFormat("#.000000");
                log.info(" * Formatted with appended zeroes to right: num = {}", format.format(num));

                // automatically appends zero to the left-most of decimal number
                format = new DecimalFormat("00000.00");
                log.info(" * Formatting with appended zeroes to left: num = {}", format.format(num));

                // formatting money in dollars
                format = new DecimalFormat("$###,###.##");
                log.info(" * Formatting with currency symbol: num = {}", format.format(num));
            }
        }

        log.info("");

        // date format
        {
            var date = new Date();
            log.info(" - Date: {}", date.toString());
            DateFormat df;

            // simple
            {
                log.info(" - Simple Instance:");
                var pattern = "G: E - yyyy MMM dd [a] => hh.mm.ss >> zzz";
                df = new SimpleDateFormat(pattern);
                log.info(" * Formatting [{}]: {}", pattern, df.format(new Date()));
            }

            // date
            {
                log.info(" - Date Instance:");
                // instance
                df = DateFormat.getDateInstance();
                log.info(" * Formatting [instance]: {}", df.format(date));
                // default
                df = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
                log.info(" * Formatting [default]: {}", df.format(date));
                // short
                df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE);
                log.info(" * Formatting [short]: {}", df.format(date));
                // medium
                df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.KOREA);
                log.info(" * Formatting [medium]: {}", df.format(date));
                // long
                df = DateFormat.getDateInstance(DateFormat.LONG, Locale.JAPAN);
                log.info(" * Formatting [long]: {}", df.format(date));
                // full
                df = DateFormat.getDateInstance(DateFormat.FULL);
                log.info(" * Formatting [full]: {}", df.format(date));
            }

            // time
            {
                log.info(" - Time Instance:");
                // instance
                df = DateFormat.getTimeInstance();
                log.info(" * Formatting [instance]: {}", df.format(date));
                // default
                df = DateFormat.getTimeInstance(DateFormat.DEFAULT, Locale.getDefault());
                log.info(" * Formatting [default]: {}", df.format(date));
                // short
                df = DateFormat.getTimeInstance(DateFormat.SHORT, Locale.GERMANY);
                log.info(" * Formatting [short]: {}", df.format(date));
                // medium
                df = DateFormat.getTimeInstance(DateFormat.MEDIUM);
                log.info(" * Formatting [medium]: {}", df.format(date));
                // long
                df = DateFormat.getTimeInstance(DateFormat.LONG, Locale.CHINA);
                log.info(" * Formatting [long]: {}", df.format(date));
                // full
                df = DateFormat.getTimeInstance(DateFormat.FULL, Locale.ITALY);
                log.info(" * Formatting [full]: {}", df.format(date));
            }

            // both
            {
                log.info(" - Full Instance:");
                df = DateFormat.getDateTimeInstance();
                log.info(" * Formatting [instance]: {}", df.format(date));
            }

            LocalDateTime local = LocalDateTime.now();
            log.info(" - LocalDate: {}", local.toString());
            DateTimeFormatter dtf;

            // time API - since jdk 8
            {
                log.info(" - Modern Instance:");
                // iso
                dtf = DateTimeFormatter.ISO_DATE_TIME;
                log.info(" * Formatting [iso]: {}", local.format(dtf));
                // time
                var localTime = LocalTime.now();
                dtf = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
                log.info(" * Formatting [time]: {}", localTime.format(dtf));
                // short
                dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
                log.info(" * Formatting [short]: {}", local.format(dtf));
                // medium
                dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
                log.info(" * Formatting [medium]: {}", local.format(dtf));
                // long
                var localDate = LocalDate.now();
                dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
                log.info(" * Formatting [long]: {}", localDate.format(dtf));
                // full
                var localZone = ZonedDateTime.now();
                dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
                log.info(" * Formatting [full]: {}", localZone.format(dtf));
            }

            // pattern
            {
                log.info(" - Pattern Instance:");
                var pattern = "MMMM d',' yyyy h':'mm a";
                dtf = DateTimeFormatter.ofPattern(pattern);
                log.info(" * Formatting [pattern]: {}", local.format(dtf));
            }
        }
    }
}
