package com.example.booking;

import java.util.ArrayList;
import java.util.List;

public class Screen {

    private final String screenId;
    private final List<Seat> seats;
    private final List<Show> schedule = new ArrayList<>();

    public Screen(String screenId, List<Seat> seats) {
        this.screenId = screenId;
        this.seats = seats;
    }

    public void registerShow(Show show) { schedule.add(show); }

    public String getScreenId()    { return screenId; }
    public List<Seat> getSeats()   { return seats; }
    public List<Show> getSchedule(){ return schedule; }
}
