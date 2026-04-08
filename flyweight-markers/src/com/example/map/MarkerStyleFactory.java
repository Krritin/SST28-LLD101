package com.example.map;

import java.util.HashMap;
import java.util.Map;

public class MarkerStyleFactory {

    private final Map<String, MarkerStyle> pool = new HashMap<>();

    public MarkerStyle getStyle(String icon, String colour, int px, boolean solid) {
        String cacheKey = icon + "-" + colour + "-" + px + "-" + (solid ? "S" : "H");
        MarkerStyle existing = pool.get(cacheKey);
        if (existing != null) return existing;

        MarkerStyle fresh = new MarkerStyle(icon, colour, px, solid);
        pool.put(cacheKey, fresh);
        return fresh;
    }

    public int poolSize() {
        return pool.size();
    }
}
