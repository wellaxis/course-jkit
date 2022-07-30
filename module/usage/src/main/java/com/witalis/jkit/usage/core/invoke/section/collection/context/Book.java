package com.witalis.jkit.usage.core.invoke.section.collection.context;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Desc: Book model
 * User: Wellaxis
 * Date: 4/10/2022
 */
@AllArgsConstructor
@Getter
@ToString
public class Book implements Comparable<Book> {
    private int id;
    private String name;
    private BigDecimal price;

    @Override
    public int compareTo(Book book) {
        if (this.getPrice() == null) return -1;
        if (book.getPrice() == null) return 1;
        return this.getPrice().compareTo(book.getPrice());
    }
}
