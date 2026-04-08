package com.example.ratelimit;

import java.util.HashMap;
import java.util.Map;

public class FixedWindowLimiter implements RateLimiter {

    private final RateLimitConfig cfg;
    private final Map<String, long[]> buckets = new HashMap<>();

    public FixedWindowLimiter(RateLimitConfig cfg) {
        this.cfg = cfg;
    }

    @Override
    public synchronized boolean tryAcquire(String clientId) {
        long now = System.currentTimeMillis();
        long windowStart = now - (now % cfg.getWindowMs());

        long[] bucket = buckets.get(clientId);
        if (bucket == null || bucket[0] != windowStart) {
            buckets.put(clientId, new long[]{windowStart, 1});
            return true;
        }
        if (bucket[1] < cfg.getMaxCalls()) {
            bucket[1]++;
            return true;
        }
        return false;
    }
}
