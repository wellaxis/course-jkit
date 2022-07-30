package com.witalis.jkit.usage.core.process;

import com.witalis.jkit.usage.core.action.IFormalizable;
import com.witalis.jkit.usage.core.action.IProcessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Objects;

/**
 * Desc: Java Kit Processor
 * User: Wellaxis
 * Date: 4/3/2022
 */
@Slf4j
public class AppProcessor implements IProcessor, IFormalizable {
    public static final String PREFIX = "Application [usage]: ";

    private final String name;
    private final ApplicationContext context;

    public AppProcessor(final String name, final ApplicationContext context) {
        this.name = name;
        this.context = context;
    }

    @Override
    public void process() {
        var processors = List.of(
            context.getBean("usageInvoker"),
            context.getBean("usageHandler")
        );
        output();
        header();
        process(processors);
        footer();
    }

    @Override
    public boolean active() {
        return true;
    }

    @Override
    public void output() {
        log.info(PREFIX + name);
    }

    @Override
    public void header() {
        log.info(PREFIX + "BEGIN.");
    }

    @Override
    public void footer() {
        log.info(PREFIX + "END.");
    }

    private void process(List<Object> processors) {
        if (!processors.isEmpty()) {
            processors.stream()
                .filter(Objects::nonNull)
                .forEach(
                    p -> ((IProcessor) p).process()
                );
        }
    }
}
