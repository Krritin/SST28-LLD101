package com.example.ratelimit;

public class App {
    public static void main(String[] args) throws InterruptedException {

        RateLimitConfig cfg = new RateLimitConfig(3, 3000);
        ExternalService payApi = new ExternalService("PaymentGW");

        System.out.println("=== fixed window (3 per 3s) ===");
        InternalService svc = new InternalService(payApi, new FixedWindowLimiter(cfg));

        for (int i = 1; i <= 7; i++) {
            boolean ext = (i != 3 && i != 6);
            System.out.println("#" + i + " ext=" + ext + " => " + svc.handle("client1", "req" + i, ext));
        }

        System.out.println("\nwaiting 3s for window reset...");
        Thread.sleep(3100);
        System.out.println(svc.handle("client1", "post-reset", true));

        System.out.println("\n=== per-client isolation ===");
        for (int i = 1; i <= 4; i++) {
            String who = (i <= 2) ? "alice" : "bob";
            System.out.println(who + " => " + svc.handle(who, "data" + i, true));
        }

        System.out.println("\n=== sliding window (3 per 2s) ===");
        svc.swapLimiter(new SlidingWindowLimiter(new RateLimitConfig(3, 2000)));
        for (int i = 1; i <= 6; i++) {
            System.out.println("sw" + i + " => " + svc.handle("X", "job" + i, true));
            Thread.sleep(500);
        }

        System.out.println("\n=== concurrent access ===");
        RateLimiter shared = new FixedWindowLimiter(new RateLimitConfig(3, 5000));
        InternalService svc2 = new InternalService(payApi, shared);

        Thread w1 = new Thread(() -> {
            for (int i = 0; i < 3; i++)
                System.out.println("  w1: " + svc2.handle("common", "w1-" + i, true));
        });
        Thread w2 = new Thread(() -> {
            for (int i = 0; i < 3; i++)
                System.out.println("  w2: " + svc2.handle("common", "w2-" + i, true));
        });
        w1.start(); w2.start();
        w1.join();  w2.join();
    }
}
