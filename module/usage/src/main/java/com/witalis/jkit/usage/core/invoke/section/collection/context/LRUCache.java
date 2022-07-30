package com.witalis.jkit.usage.core.invoke.section.collection.context;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Desc: Simple LRU Cache
 * User: Wellaxis
 * Date: 6/28/2022
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    /**
     * LRU Cache constructor.
     * 1) initial capacity: should be as required plus 1
     * 2) load factor: should be more than 1 to avoid increase of capacity
     * 3) access order: should be activated
     * <p/>
     * @param capacity the required capacity
     */
    public LRUCache(int capacity) {
        super(capacity + 1, 1.1F, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return this.size() > capacity;
    }
}
