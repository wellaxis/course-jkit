package com.witalis.jkit.spring.data.jpa.projection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Desc: Java Kit - Spring Data - Projection functionality
 * User: Wellaxis
 * Date: 2022/08/14
 */
@Slf4j
@SpringBootApplication
public class JkitDataJpaProjection {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
            .sources(JkitDataJpaProjection.class)
            .run(args);
        log.info("Application [jpa/projection]: In Action...");
    }
}
