package com.witalis.jkit.spring.data.jpa.nplusone;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Desc: Java Kit - Spring Data - N+1 problem
 * User: Wellaxis
 * Date: 2022/08/14
 */
@Slf4j
@SpringBootApplication
public class JkitDataJpaNPlusOne {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
            .sources(JkitDataJpaNPlusOne.class)
            .run(args);
        log.info("Application [jpa/nplusone]: In Action...");
    }
}
