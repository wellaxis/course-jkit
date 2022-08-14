package com.witalis.jkit.spring.data.jpa.unidirectional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Desc: Java Kit - Spring Data - Unidirectional relationship
 * User: Wellaxis
 * Date: 2022/08/14
 */
@Slf4j
@SpringBootApplication
public class JkitDataJpaUnidirectional {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
            .sources(JkitDataJpaUnidirectional.class)
            .run(args);
        log.info("Application [jpa/unidirectional]: In Action...");
    }
}
