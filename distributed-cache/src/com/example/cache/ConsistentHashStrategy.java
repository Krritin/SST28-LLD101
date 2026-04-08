package com.example.cache;

import java.util.TreeMap;

public class ConsistentHashStrategy implements DistributionStrategy {

    private final TreeMap<Long, Integer> ring = new TreeMap<>();

    public ConsistentHashStrategy(int nodeCount, int replicas) {
        for (int node = 0; node < nodeCount; node++) {
            for (int r = 0; r < replicas; r++) {
                long h = fnv1a("node" + node + "r" + r);
                ring.put(h, node);
            }
        }
    }

    @Override
    public int resolveNode(String key, int totalNodes) {
        long h = fnv1a(key);
        Long slot = ring.ceilingKey(h);
        if (slot == null) slot = ring.firstKey();
        return ring.get(slot);
    }

    private long fnv1a(String s) {
        long hash = 2166136261L;
        for (int i = 0; i < s.length(); i++) {
            hash ^= s.charAt(i);
            hash *= 16777619;
        }
        return Math.abs(hash);
    }
}
