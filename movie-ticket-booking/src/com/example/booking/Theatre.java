package com.example.booking;

import java.util.ArrayList;
import java.util.List;

public class Theatre {

    private final String id;
    private final String name;
    private final City city;
    private final List<Screen> screens = new ArrayList<>();

    public Theatre(String id, String name, City city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public void addScreen(Screen scr) { screens.add(scr); }

    public String getId()            { return id; }
    public String getName()          { return name; }
    public City getCity()            { return city; }
    public List<Screen> getScreens() { return screens; }

    @Override
    public String toString() { return name + " (" + city + ")"; }
}
