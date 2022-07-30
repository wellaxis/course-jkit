package com.witalis.jkit.usage.core.invoke.section.reflection;

import com.witalis.jkit.usage.core.invoke.Invoker;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Desc: Reflection API
 * User: Wellaxis
 * Date: 2021/03/29
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class ReflectionInvoker extends Invoker {

    public ReflectionInvoker() {
        setTitle("Reflection chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
    }

    /**
     * Reflection operations.
     */
    public void invokeBasis() {
        try {
            // class
            Class<?> clazz = Class.forName("java.util.function.BiConsumer");
            log.info(" - Class: {}", clazz.getCanonicalName());

            // modifier
            var modifiers = clazz.getModifiers();
            log.info(" - Modifiers: {}", Modifier.toString(modifiers));
            log.info("   - interface: {}", Modifier.isInterface(modifiers));
            log.info("   - abstract: {}", Modifier.isAbstract(modifiers));
            log.info("   - final: {}", Modifier.isFinal(modifiers));
            log.info("   - public: {}", Modifier.isPublic(modifiers));
            log.info("   - private: {}", Modifier.isPrivate(modifiers));
            log.info("   - protected: {}", Modifier.isProtected(modifiers));
            log.info("   - static: {}", Modifier.isStatic(modifiers));
            log.info("   - native: {}", Modifier.isNative(modifiers));
            log.info("   - strict: {}", Modifier.isStrict(modifiers));

            // annotation
            Annotation[] annotations = clazz.getDeclaredAnnotations();
            log.info(" - Annotations:");
            for (int i = 0; i < annotations.length; i++) {
                log.info(" [{}]: {}", i, annotations[i]);
            }

            // constructor
            Constructor[] constructors = clazz.getDeclaredConstructors();
            log.info(" - Constructors:");
            for (int i = 0; i < constructors.length; i++) {
                log.info(" [{}]: {}", i, constructors[i]);
            }

            // field
            Field[] fields = clazz.getDeclaredFields();
            log.info(" - Fields:");
            for (int i = 0; i < fields.length; i++) {
                log.info(" [{}]: {}", i, fields[i]);
            }

            // method
            Method[] methods = clazz.getDeclaredMethods();
            log.info(" - Methods:");
            for (int i = 0; i < methods.length; i++) {
                log.info(" [{}]: {}", i, methods[i]);
            }
        } catch (ClassNotFoundException e) {
            log.error("Reflection errors, {}", e.getLocalizedMessage());
        }
    }
}
