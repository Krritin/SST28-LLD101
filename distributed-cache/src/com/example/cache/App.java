package com.example.cache;

public class App {
    public static void main(String[] args) {

        Database db = new Database();
        db.save("emp1", "Aarav");
        db.save("emp2", "Diya");
        db.save("emp3", "Rohan");

        System.out.println("--- modulo strategy ---");
        DistributedCache cache = new DistributedCache(3, 2, new ModuloStrategy(), db);

        cache.put("x1", "alpha");
        cache.put("x2", "beta");
        cache.put("x3", "gamma");
        cache.put("x4", "delta");
        cache.dump();

        System.out.println();
        System.out.println(cache.get("x1"));
        System.out.println(cache.get("x2"));

        System.out.println();
        System.out.println(cache.get("emp1"));
        System.out.println(cache.get("emp2"));
        System.out.println(cache.get("emp1"));   // should be cache-hit now

        cache.dump();

        cache.put("x5", "epsilon");
        cache.put("x6", "zeta");
        cache.put("x7", "eta");
        cache.dump();

        System.out.println();
        System.out.println(cache.get("x1"));

        System.out.println("\n--- consistent hashing ---");
        DistributedCache chCache = new DistributedCache(3, 2, new ConsistentHashStrategy(3, 50), db);
        chCache.put("name", "Aarav");
        chCache.put("age", "22");
        chCache.put("city", "Mumbai");
        chCache.put("email", "a@b.com");
        chCache.put("phone", "98765");
        chCache.put("country", "India");
        chCache.dump();

        System.out.println();
        System.out.println(chCache.get("name"));
        System.out.println(chCache.get("city"));
    }
}
