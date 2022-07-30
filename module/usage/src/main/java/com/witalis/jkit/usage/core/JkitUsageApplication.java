package com.witalis.jkit.usage.core;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * Desc: Java Kit - Usage
 * User: Wellaxis
 * Date: 2019/11/17
 */
@Slf4j
@SpringBootApplication
@EqualsAndHashCode(callSuper=false)
@ConfigurationPropertiesScan
public class JkitUsageApplication {

    public static void main(String... args) {
        new SpringApplicationBuilder()
            .sources(JkitUsageApplication.class)
            .bannerMode(Banner.Mode.LOG)
            .web(WebApplicationType.NONE)
            .headless(false)
            .run(args);
    }
}
