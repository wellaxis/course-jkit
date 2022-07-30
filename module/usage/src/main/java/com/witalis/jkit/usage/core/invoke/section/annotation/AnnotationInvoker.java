package com.witalis.jkit.usage.core.invoke.section.annotation;

import com.witalis.jkit.usage.core.invoke.Invoker;
import com.witalis.jkit.usage.core.invoke.section.annotation.content.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.*;
import java.lang.reflect.Constructor;

/**
 * Desc: Annotations
 * User: Wellaxis
 * Date: 2019/11/18
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
public class AnnotationInvoker extends Invoker {

    public AnnotationInvoker() {
        setTitle("Annotation chapter.");
    }

    @Override
    public void invoke() {
        // basis
        log.info("## Basis");
        invokeBasis();
        // tab
        log.info("");
        // metadata
        log.info("## Metadata");
        invokeMetadata();
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @interface Introduce {
        String greeting();
    }

    /**
     * Basic postulates of annotation.
     */
    private void invokeBasis() {
        // Annotations, a form of metadata, provide data about a program
        // that is not part of the program itself.
        // Annotations have no direct effect on the operation of the code they annotate.

        // Annotations use:
        // * information for the compiler
        // * compile-time and deployment-time processing
        // * runtime processing

        @Introduce(greeting = "Greetings!")
        class Introduction {
            private String description;

            public Introduction(String description) {
                this.description = description;
            }

            public String getDescription() {
                return description;
            }
        }

        Introduction introduction = new Introduction("Annotations");
        var greeting = introduction.getClass().getAnnotation(Introduce.class).greeting();
        log.info("Introduction: {} {}!", greeting, introduction.getDescription());
    }

    /**
     * Use annotations via reflection API.
     */
    private void invokeMetadata() {
        // annotations as metadata
        log.info("---- Standard annotations");

        SampleApplication sampleApplication = new SampleApplication(777);
        try {
            log.info("Class: " + sampleApplication.getClass());

            log.info("    Instance: " + sampleApplication);

            log.info("    Annotations:");
            if (sampleApplication.getClass().isAnnotationPresent(MarkerAnnotation.class)) {
                log.info("        Class is marked.");
            }
            for (Annotation annotation : sampleApplication.getClass().getAnnotations()) {
                log.info("        [Class] " + annotation.annotationType() + "/" + annotation);
            }
            DefaultAnnotation defaultAnnotation = sampleApplication.getClass().getAnnotation(DefaultAnnotation.class);
            log.info("    Default Information: " + defaultAnnotation.version() + "/" + defaultAnnotation.comment());

            log.info("    Constructors:");
            for (Constructor<?> constructor: sampleApplication.getClass().getDeclaredConstructors()) {
                var parameterCount = constructor.getParameterCount();
                if (parameterCount == 0) {
                    for (Annotation annotation : constructor.getAnnotations()) {
                        log.info("        [Constructor Default] " + annotation.annotationType() + "/" + annotation);
                    }
                } else {
                    for (Annotation annotation : constructor.getAnnotations()) {
                        log.info("        [Constructor Params] " + annotation.annotationType() + "/" + annotation);
                    }
                }
            }

            log.info("    Methods:");
            for (Annotation annotation : sampleApplication.getClass().getDeclaredMethod("toString", new Class[0]).getAnnotations()) {
                log.info("        [Method info] " + annotation.annotationType() + "/" + annotation);
            }
            for (Annotation annotation : sampleApplication.getClass().getDeclaredMethod("equals", Object.class).getAnnotations()) {
                log.info("        [Method equals] " + annotation.annotationType() + "/" + annotation);
            }
            for (Annotation annotation : sampleApplication.getClass().getDeclaredMethod("hashCode").getAnnotations()) {
                log.info("        [Method hash] " + annotation.annotationType() + "/" + annotation);
            }
            for (Annotation annotation : sampleApplication.getClass().getDeclaredMethod("showInfo", int.class, String.class).getAnnotations()) {
                log.info("        [Method show] " + annotation.annotationType() + "/" + annotation);
                if (annotation instanceof SimpleAnnotation) {
                    SimpleAnnotation simpleAnnotation = (SimpleAnnotation) annotation;
                    log.info("            Metadata: info=" + simpleAnnotation.info() + ", value=" + simpleAnnotation.value());
                }
            }
            for (Annotation annotation : sampleApplication.getClass().getDeclaredMethod("getVersion", int.class).getAnnotations()) {
                log.info("        [Method version] " + annotation.annotationType() + "/" + annotation);
                if (annotation instanceof SingleAnnotation) {
                    SingleAnnotation singleAnnotation = (SingleAnnotation) annotation;
                    log.info("            Metadata: info=" + singleAnnotation.toString() + ", value=" + singleAnnotation.value());
                }
            }

            log.info("    Fields:");
            for (Annotation annotation : sampleApplication.getClass().getDeclaredField("REVISION_UNDEFINED").getAnnotations()) {
                log.info("        [Field constant] " + annotation.annotationType() + "/" + annotation);
            }
            for (Annotation annotation : sampleApplication.getClass().getDeclaredField("revision").getAnnotations()) {
                log.info("        [Field variable] " + annotation.annotationType() + "/" + annotation);
            }
        } catch (NoSuchFieldException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        log.info("");

        // container annotations
        log.info("---- Container annotations");

        ContainerApplication containerApplication = new ContainerApplication();
        log.info("Class: " + containerApplication.getClass());
        log.info("    Instance: " + containerApplication);
        log.info("    Annotations: ");
        for (Annotation annotation : containerApplication.getClass().getAnnotations()) {
            log.info("        [Class] " + annotation.annotationType() + "/" + annotation);
        }
        Annotation[] repeatableAnnotations = containerApplication.getClass().getDeclaredAnnotationsByType(ContainerItem.class);
        log.info("        Repeated Annotations: ");
        for (Annotation repeatableItem : repeatableAnnotations) {
            log.info("            " + repeatableItem.toString());
        }
    }
}
