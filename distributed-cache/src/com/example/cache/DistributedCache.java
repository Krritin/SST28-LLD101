package com.example.cache;

import java.util.ArrayList;
import java.util.List;

public class DistributedCache {

    private final List<CacheNode<String>> nodes;
    private final DistributionStrategy strategy;
    private final Database db;
    private final int nodeCount;

    public DistributedCache(int nodeCount, int capacityPerNode, DistributionStrategy strategy, Database db) {
        this.nodeCount = nodeCount;
        this.strategy = strategy;
        this.db = db;
        this.nodes = new ArrayList<>();
        for (int i = 0; i < nodeCount; i++) {
            nodes.add(new CacheNode<>("N" + i, capacityPerNode, new LRUEviction<>()));
        }
    }

    public String get(String key) {
        int idx = strategy.resolveNode(key, nodeCount);
        CacheNode<String> node = nodes.get(idx);
        String val = node.lookup(key);
        if (val != null) {
            System.out.println("cache-hit " + key + " @ " + node.getNodeId());
            return val;
        }
        System.out.println("cache-miss " + key + " @ " + node.getNodeId());
        val = db.fetch(key);
        if (val != null) node.insert(key, val);
        return val;
    }

    public void put(String key, String val) {
        int idx = strategy.resolveNode(key, nodeCount);
        nodes.get(idx).insert(key, val);
        db.save(key, val);
    }

    public void dump() {
        for (CacheNode<String> nd : nodes) {
            System.out.println("  " + nd);
        }
    }
}
