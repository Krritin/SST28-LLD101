package com.example.cache;

import java.util.LinkedList;

public class MRUEviction<K> implements EvictionPolicy<K> {

    private final LinkedList<K> order = new LinkedList<>();

    @Override
    public void touch(K key) {
        order.remove(key);
        order.addLast(key);
    }

    @Override
    public K selectVictim() {
        return order.isEmpty() ? null : order.removeLast();
    }

    @Override
    public void discard(K key) {
        order.remove(key);
    }
}
