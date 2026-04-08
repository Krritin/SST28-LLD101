package com.example.map;

import java.util.List;

public class MapRenderer {

    public void draw(List<MapMarker> markers) {
        System.out.println("Drawing " + markers.size() + " markers on canvas...");
        int preview = 0;

        for (MapMarker m : markers) {
            if (preview < 6) {
                System.out.printf("  [%s] (%.4f, %.4f) => %s%n",
                        m.getTag(), m.getLatitude(), m.getLongitude(), m.getStyle());
                preview++;
            }
        }

        if (markers.size() > preview) {
            System.out.println("  ... and " + (markers.size() - preview) + " more");
        }
    }
}
