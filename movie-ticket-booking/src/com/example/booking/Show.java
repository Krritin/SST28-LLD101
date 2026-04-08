package com.example.booking;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Show {

    private final String showId;
    private final Movie movie;
    private final Screen screen;
    private final String slot;
    private final Map<String, SeatStatus> seatMap = new HashMap<>();
    private final ReentrantLock lock = new ReentrantLock();

    public Show(String showId, Movie movie, Screen screen, String slot) {
        this.showId = showId;
        this.movie = movie;
        this.screen = screen;
        this.slot = slot;
        for (Seat s : screen.getSeats()) seatMap.put(s.getLabel(), SeatStatus.FREE);
    }

    public boolean isFree(String label) {
        return seatMap.getOrDefault(label, null) == SeatStatus.FREE;
    }

    public void holdSeat(String label)    { seatMap.put(label, SeatStatus.HELD); }
    public void sellSeat(String label)    { seatMap.put(label, SeatStatus.SOLD); }
    public void releaseSeat(String label) { seatMap.put(label, SeatStatus.FREE); }

    public Map<String, SeatStatus> getSeatMap() { return seatMap; }
    public ReentrantLock getLock()               { return lock; }
    public String getShowId()                    { return showId; }
    public Movie getMovie()                      { return movie; }
    public Screen getScreen()                    { return screen; }
    public String getSlot()                      { return slot; }

    @Override
    public String toString() { return showId + " " + movie.getTitle() + " @ " + slot; }
}
