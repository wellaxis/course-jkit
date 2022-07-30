package com.witalis.jkit.usage.core.invoke.section.multithreading.content.lock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Desc: Atomic updater
 * User: Wellaxis
 * Date: 4/30/2022
 */
public class AtomicUpdater {
    private volatile AtomicContent content;

    public AtomicUpdater(AtomicContent content) {
        this.content = content;
    }

    private final AtomicReferenceFieldUpdater<AtomicUpdater, AtomicContent> updater =
        AtomicReferenceFieldUpdater.newUpdater(
            AtomicUpdater.class,
            AtomicContent.class,
            "content"
        );

    public AtomicContent getContent() {
        return content;
    }

    public void setContent(AtomicContent inUse) {
        updater.compareAndSet(this, this.content, inUse);
    }
}
