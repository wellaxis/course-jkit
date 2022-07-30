package com.witalis.jkit.usage.core.invoke.section.lambda.content.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * Desc: Reference method - constructor mode
 * User: Wellaxis
 * Date: 5/21/2022
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceMethodConstructor<T> {
    public static final String DEF_NAME = "Test";
    public static final boolean DEF_STATE = true;

    private UUID id;
    private String name;
    private boolean active;
    private T value;

    public ReferenceMethodConstructor(T value) {
        this.value = value;
    }

    public interface ConstructorNoParameters {
        ReferenceMethodConstructor get();
    }

    public interface ConstructorOneParameter<T> {
        ReferenceMethodConstructor get(T value);
    }

    public interface ConstructorWithParameters<T> {
        ReferenceMethodConstructor get(UUID id, String name, boolean active, T value);
    }
}
