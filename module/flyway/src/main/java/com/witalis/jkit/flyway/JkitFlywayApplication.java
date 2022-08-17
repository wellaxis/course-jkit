package com.witalis.jkit.flyway;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Desc: Java Kit - Database tracker - Flyway
 * User: Wellaxis
 * Date: 2022/08/15
 */
@Slf4j
@SpringBootApplication
@EnableTransactionManagement
@EqualsAndHashCode(callSuper=false)
@ConfigurationPropertiesScan
public class JkitFlywayApplication {

    public static void main(String... args) {
        new SpringApplicationBuilder()
            .sources(JkitFlywayApplication.class)
            .web(WebApplicationType.NONE)
            .headless(false)
            .run(args);
        log.info("Application [flyway]: In Action...");
    }
}
