package com.example.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapDataSource {

    private static final String[] ICONS   = {"DIAMOND", "PIN", "TRIANGLE"};
    private static final String[] COLOURS = {"YELLOW", "PURPLE", "RED", "CYAN"};
    private static final int[]    SIZES   = {8, 11, 14, 18};

    private final MarkerStyleFactory styleFactory = new MarkerStyleFactory();

    public List<MapMarker> generate(int total) {
        Random random = new Random(42);
        List<MapMarker> markers = new ArrayList<>(total);

        for (int i = 0; i < total; i++) {
            double lat = 28.5000 + random.nextDouble() * 0.15;
            double lng = 77.2000 + random.nextDouble() * 0.15;
            String tag = "MK" + i;

            String icon   = ICONS[random.nextInt(ICONS.length)];
            String colour = COLOURS[random.nextInt(COLOURS.length)];
            int px        = SIZES[random.nextInt(SIZES.length)];
            boolean solid = random.nextBoolean();

            MarkerStyle style = styleFactory.getStyle(icon, colour, px, solid);
            markers.add(new MapMarker(lat, lng, tag, style));
        }
        return markers;
    }
}
