package com.example.map;

// immutable flyweight — shared across thousands of markers
public class MarkerStyle {

    private final String icon;
    private final String colour;
    private final int px;
    private final boolean solid;

    public MarkerStyle(String icon, String colour, int px, boolean solid) {
        this.icon = icon;
        this.colour = colour;
        this.px = px;
        this.solid = solid;
    }

    public String getIcon()   { return icon; }
    public String getColour() { return colour; }
    public int getPx()        { return px; }
    public boolean isSolid()  { return solid; }

    @Override
    public String toString() {
        return icon + "-" + colour + "-" + px + "-" + (solid ? "S" : "H");
    }
}
