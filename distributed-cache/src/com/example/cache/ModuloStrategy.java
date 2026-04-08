package com.example.cache;

public class ModuloStrategy implements DistributionStrategy {

    @Override
    public int resolveNode(String key, int totalNodes) {
        return Math.abs(key.hashCode()) % totalNodes;
    }
}
