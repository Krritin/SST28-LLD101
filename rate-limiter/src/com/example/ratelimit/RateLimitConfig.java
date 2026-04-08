package com.example.ratelimit;

public class RateLimitConfig {

    private final int maxCalls;
    private final long windowMs;

    public RateLimitConfig(int maxCalls, long windowMs) {
        this.maxCalls = maxCalls;
        this.windowMs = windowMs;
    }

    public int getMaxCalls()  { return maxCalls; }
    public long getWindowMs() { return windowMs; }
}
