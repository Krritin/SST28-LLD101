package com.example.cache;

public interface EvictionPolicy<K> {
    void touch(K key);
    K selectVictim();
    void discard(K key);
}
