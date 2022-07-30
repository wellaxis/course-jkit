package com.witalis.jkit.usage.core.invoke.section.references.content;

import lombok.extern.slf4j.Slf4j;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

@Slf4j
public class ReferenceHandler {
    List<ReferenceObject> strongRefs = new ArrayList<>();
    List<Reference<ReferenceObject>> softRefs = new ArrayList<>();
    List<Reference<ReferenceObject>> weakRefs = new ArrayList<>();
    List<Reference<ReferenceObject>> phantomRefs = new ArrayList<>();
    ReferenceQueue<ReferenceObject> queue = new ReferenceQueue<>();

    public void handle() {
        System.gc();
        log.info("GC invoked");
        print();
    }

    public void initialization() {
        log.info("Reference: [initialization]");
        IntStream.rangeClosed(0, 5)
            .boxed()
            .forEach(i -> {
                strongRefs.add(new ReferenceObject(i));
                softRefs.add(new SoftReference<>(new ReferenceObject(i)));
                weakRefs.add(new WeakReference<>(new ReferenceObject(i)));
                phantomRefs.add(new PhantomReference<>(new ReferenceObject(i), queue));
            });
    }

    public void print() {
        log.info("Reference: [printing]");
        log.info("- Strong references:");
        printList(strongRefs);
        log.info("- Soft references:");
        printRefList(softRefs);
        log.info("- Weak references:");
        printRefList(weakRefs);
        log.info("- Phantom references:");
        printRefList(phantomRefs);
    }

    private void printList(List<ReferenceObject> list) {
        StringBuilder builder = new StringBuilder("objects: ");
        list.forEach(
            referenceObject -> builder.append(referenceObject.toString()).append(", ")
        );
        log.info(builder.toString());
    }

    private void printRefList(List<Reference<ReferenceObject>> list) {
        StringBuilder builder = new StringBuilder("objects: ");
        list.forEach(
            ref -> {
                var refObject = ref.get();
                if (Objects.isNull(refObject)) {
                    builder.append(ReferenceObject.toNullString()).append(", ");
                } else {
                    builder.append(Objects.requireNonNull(ref.get())).append(", ");
                }
            }
        );
        log.info(builder.toString());
    }

    public void memoryLoading() {
        final var size = 1_000_000;

        log.info("Reference: [loading]");
        List<String> objects = new ArrayList<>();
        var count = IntStream.range(0, size).boxed()
            .map(i -> {
                var object = new String("" + i);
                objects.add(object);
                return object;
            })
            .toList();
        log.info("Reference: [loaded]: {}", count.size());
    }
}
