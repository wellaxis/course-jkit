package com.witalis.jkit.jooq;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Desc: Java Kit - Database mapping - jOOQ
 * User: Wellaxis
 * Date: 2022/09/06
 */
@Slf4j
@SpringBootApplication
@EnableTransactionManagement
@EqualsAndHashCode(callSuper=false)
@ConfigurationPropertiesScan
public class JkitJooqApplication {

    public static void main(String... args) {
        new SpringApplicationBuilder()
            .sources(JkitJooqApplication.class)
            .web(WebApplicationType.NONE)
            .headless(false)
            .run(args);
        log.info("Application [jooq]: In Action...");
    }
}
