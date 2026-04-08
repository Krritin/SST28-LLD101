package com.example.ratelimit;

public interface RateLimiter {
    boolean tryAcquire(String clientId);
}
