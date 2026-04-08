package com.example.cache;

import java.util.HashMap;
import java.util.Map;

public class CacheNode<V> {

    private final String nodeId;
    private final int capacity;
    private final Map<String, V> store = new HashMap<>();
    private final EvictionPolicy<String> evictor;

    public CacheNode(String nodeId, int capacity, EvictionPolicy<String> evictor) {
        this.nodeId = nodeId;
        this.capacity = capacity;
        this.evictor = evictor;
    }

    public V lookup(String key) {
        if (!store.containsKey(key)) return null;
        evictor.touch(key);
        return store.get(key);
    }

    public void insert(String key, V val) {
        if (store.containsKey(key)) {
            store.put(key, val);
            evictor.touch(key);
            return;
        }
        if (store.size() >= capacity) {
            String victim = evictor.selectVictim();
            if (victim != null) {
                store.remove(victim);
                System.out.println(nodeId + " evicted " + victim);
            }
        }
        store.put(key, val);
        evictor.touch(key);
    }

    public String getNodeId() { return nodeId; }

    @Override
    public String toString() {
        return nodeId + " => " + store;
    }
}
