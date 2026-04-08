package com.example.ratelimit;

public class InternalService {

    private final ExternalService downstream;
    private RateLimiter limiter;

    public InternalService(ExternalService downstream, RateLimiter limiter) {
        this.downstream = downstream;
        this.limiter = limiter;
    }

    public String handle(String clientId, String payload, boolean callExternal) {
        String result = "internal OK: " + payload;

        if (callExternal) {
            if (limiter.tryAcquire(clientId)) {
                result += " | " + downstream.invoke(payload);
            } else {
                result += " | THROTTLED — external call skipped";
            }
        }
        return result;
    }

    public void swapLimiter(RateLimiter newLimiter) {
        this.limiter = newLimiter;
    }
}
