package com.example.ratelimit;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class SlidingWindowLimiter implements RateLimiter {

    private final RateLimitConfig cfg;
    private final Map<String, LinkedList<Long>> history = new HashMap<>();

    public SlidingWindowLimiter(RateLimitConfig cfg) {
        this.cfg = cfg;
    }

    @Override
    public synchronized boolean tryAcquire(String clientId) {
        long now = System.currentTimeMillis();
        LinkedList<Long> ts = history.computeIfAbsent(clientId, k -> new LinkedList<>());

        // purge expired entries
        while (!ts.isEmpty() && ts.getFirst() <= now - cfg.getWindowMs())
            ts.removeFirst();

        if (ts.size() < cfg.getMaxCalls()) {
            ts.addLast(now);
            return true;
        }
        return false;
    }
}
