package com.witalis.jkit.spring.data.jpa.pagination;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Desc: Java Kit - Spring Data - Pagination functionality
 * User: Wellaxis
 * Date: 2022/08/14
 */
@Slf4j
@SpringBootApplication
public class JkitDataJpaPagination {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
            .sources(JkitDataJpaPagination.class)
            .run(args);
        log.info("Application [jpa/pagination]: In Action...");
    }
}
