package com.witalis.jkit.usage.core.invoke.section.lambda.content.pattern.builder;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Slf4j
@Data
@ToString
public class FluentMailer {
    private String from;
    private String to;
    private String subject;
    private String body;

    // constructor is private
    private FluentMailer() {}

    public FluentMailer from(final String address) {
        setFrom(address);
        return this;
    }

    public FluentMailer to(final String address) {
        setTo(address);
        return this;
    }

    public FluentMailer subject(final String line) {
        setSubject(line);
        return this;
    }

    public FluentMailer body(final String message) {
        setBody(message);
        return this;
    }

    public static void send(final Consumer<FluentMailer> block) {
        final FluentMailer mailer = new FluentMailer();
        block.accept(mailer);
        log.info("Sending information:\t {}", mailer);
    }
}
