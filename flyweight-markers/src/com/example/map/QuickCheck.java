package com.example.map;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuickCheck {

    public static void main(String[] args) {
        int total = 15_000;

        MapDataSource source = new MapDataSource();
        List<MapMarker> markers = source.generate(total);

        Set<Integer> uniqueRefs = new HashSet<>();
        for (MapMarker m : markers) {
            uniqueRefs.add(System.identityHashCode(m.getStyle()));
        }

        int maxPossible = 3 * 4 * 4 * 2;   // icons * colours * sizes * solid/hollow
        System.out.println("Total markers  : " + total);
        System.out.println("Unique styles  : " + uniqueRefs.size());
        System.out.println("Max combos     : " + maxPossible);
        System.out.println("Flyweight OK?  : " + (uniqueRefs.size() <= maxPossible));
    }
}
