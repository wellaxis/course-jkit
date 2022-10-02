package com.witalis.jkit.shell;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * Desc: Java Kit - Shell CLI
 * User: Wellaxis
 * Date: 2022/10/02
 */
@Slf4j
@SpringBootApplication
@EqualsAndHashCode(callSuper=false)
@ConfigurationPropertiesScan
public class JkitShellApplication {

    public static void main(String... args) {
        new SpringApplicationBuilder()
            .sources(JkitShellApplication.class)
            .bannerMode(Banner.Mode.LOG)
            .web(WebApplicationType.NONE)
            .headless(false)
            .run(args);
    }
}
