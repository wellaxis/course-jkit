package com.witalis.jkit.usage.core.invoke.section.multithreading.content.scenario;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * Desc: Product
 * User: Wellaxis
 * Date: 18.01.2022
 */
@Slf4j
@Data
@Builder
public class TopProduct {
    private UUID id;
    private String name;
    private int price;

    @Override
    public String toString() {
        return "Product: [id=" + id + ", name='" + name + "', price=" + price + "]";
    }
}
