package com.witalis.jkit.usage.core.invoke.section.collection.context;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@ToString
public class Node {
    private int value;
    private Node left;
    private Node right;

    public void print() {
        log.info("Node value: {}", value);
    }
}
