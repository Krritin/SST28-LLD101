package com.example.map;

import java.util.List;

public class App {

    public static void main(String[] args) {
        int count = 25_000;

        MapDataSource source = new MapDataSource();
        List<MapMarker> markers = source.generate(count);

        new MapRenderer().draw(markers);

        System.out.println();
        System.out.println("Run QuickCheck to verify flyweight sharing:");
        System.out.println("  java com.example.map.QuickCheck");
    }
}
