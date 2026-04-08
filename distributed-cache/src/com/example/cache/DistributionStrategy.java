package com.example.cache;

public interface DistributionStrategy {
    int resolveNode(String key, int totalNodes);
}
