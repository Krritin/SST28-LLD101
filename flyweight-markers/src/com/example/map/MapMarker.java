package com.example.map;

public class MapMarker {

    private final double latitude;
    private final double longitude;
    private final String tag;
    private final MarkerStyle style;   // shared flyweight ref

    public MapMarker(double latitude, double longitude, String tag, MarkerStyle style) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.tag = tag;
        this.style = style;
    }

    public double getLatitude()  { return latitude; }
    public double getLongitude() { return longitude; }
    public String getTag()       { return tag; }
    public MarkerStyle getStyle(){ return style; }
}
