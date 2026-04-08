package com.example.booking;

public class Movie {

    private final String code;
    private final String title;
    private final int durationMin;

    public Movie(String code, String title, int durationMin) {
        this.code = code;
        this.title = title;
        this.durationMin = durationMin;
    }

    public String getCode()      { return code; }
    public String getTitle()     { return title; }
    public int getDurationMin()  { return durationMin; }

    @Override
    public String toString() { return title + " (" + durationMin + "m)"; }
}
