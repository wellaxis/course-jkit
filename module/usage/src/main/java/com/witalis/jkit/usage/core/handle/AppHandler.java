package com.witalis.jkit.usage.core.handle;

import com.witalis.jkit.usage.core.action.IHandler;

import com.witalis.jkit.usage.core.config.UsageProcess;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Desc: Java Kit handler
 * User: Wellaxis
 * Date: 2019/11/16
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class AppHandler extends Handler {
    private UsageProcess properties;

    public AppHandler(UsageProcess properties) {
        this.properties = properties;
    }

    public static void main(String[] args) {
        UsageProcess process = UsageProcess.builder()
            .handler(
                UsageProcess.Handler.builder()
                    .activate(true)
                    .sections(List.of("all"))
                    .build()
            )
            .build();

        AppHandler handler = new AppHandler(process);
        handler.header();
        handler.handle();
        handler.footer();
    }

    @Override
    public void header() {
        log.info("BEGIN.");
    }

    @Override
    public void handle() {
        // define
        List<IHandler> handlers = List.of(
        );
        // handle
        handlers.stream()
            .filter(IHandler::active)
            .forEach(
                handler -> {
                    output();
                    handler.header();
                    handler.handle();
                    handler.footer();
                    output();
            }
        );
    }

    @Override
    public void footer() {
        log.info("END.");
    }

    @Override
    public void output() {
        log.info("");
    }
}
