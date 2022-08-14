package com.witalis.jkit.spring.data.jpa.bidirectional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Desc: Java Kit - Spring Data - Bidirectional relationship
 * User: Wellaxis
 * Date: 2022/08/14
 */
@Slf4j
@SpringBootApplication
public class JkitDataJpaBidirectional {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
            .sources(JkitDataJpaBidirectional.class)
            .run(args);
        log.info("Application [jpa/bidirectional]: In Action...");
    }
}
