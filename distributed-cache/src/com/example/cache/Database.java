package com.example.cache;

import java.util.HashMap;
import java.util.Map;

public class Database {

    private final Map<String, String> rows = new HashMap<>();

    public void save(String key, String val) {
        rows.put(key, val);
    }

    public String fetch(String key) {
        if (rows.containsKey(key)) {
            System.out.println("db-read " + key);
            return rows.get(key);
        }
        return null;
    }
}
