package com.witalis.jkit.jooq.utils;

import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.DSL;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("unchecked")
public final class JooqUtils {

    private JooqUtils() {
        super();
    }

    public static <R extends UpdatableRecord<R>> void insert(UpdatableRecord<R> record) {
        record.store();
    }

    public static <R extends UpdatableRecord<R>> void insert(List<? extends UpdatableRecord<R>> records) {
        records.forEach(UpdatableRecord::store);
    }

    public static <R extends Record> int count(DSLContext context, Table<R> table) {
        return context.fetchCount(DSL.selectFrom(table));
    }

    public static <R extends Record> R getOne(DSLContext context, Table<R> table, Condition condition) {
        return context.fetchOne(table, condition);
    }

    public static <R extends Record> Optional<R> getOptional(DSLContext context, Table<R> table, Condition condition) {
        return context.fetchOptional(table, condition);
    }

    public static <R extends Record> Result<R> getAll(DSLContext context, Table<R> table) {
        return (Result<R>) context.select().from(table).fetch();
    }

    public static <R extends Record> Result<R> getSet(DSLContext context, Table<R> table, Condition condition) {
        return (Result<R>) context.select().from(table).where(condition).fetch();
    }

    public static <T, R extends Record> R getById(DSLContext context, Table<R> table, Field<T> field, T value) {
        return context.fetchOne(table, field.eq(value));
    }

    public static <R extends Record> Result<R> getView(DSLContext context, Table<R> table, SelectFieldOrAsterisk... fields) {
        return (Result<R>) context.select(fields).from(table).fetch();
    }

    public static <R extends UpdatableRecord<R>> void update(UpdatableRecord<R> record) {
        record.update();
    }

    public static <T> void update(DSLContext context, Table<? extends Record> table, Map<Field<? extends T>, T> values) {
        context.update(table)
            .set(values)
            .execute();
    }

    public static <T> void update(DSLContext context, Table<? extends Record> table, Map<Field<? extends T>, T> values, Condition condition) {
        context.update(table)
            .set(values)
            .where(condition)
            .execute();
    }

    public static <R extends UpdatableRecord<R>> void delete(UpdatableRecord<R> record) {
        record.delete();
    }

    public static void delete(DSLContext context, Table<? extends Record> table) {
        context.delete(table)
            .execute();
    }

    public static void delete(DSLContext context, Table<? extends Record> table, Condition condition) {
        context.delete(table)
            .where(condition)
            .execute();
    }
}
