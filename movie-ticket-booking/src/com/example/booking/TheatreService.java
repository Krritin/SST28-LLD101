package com.example.booking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class TheatreService {

    private final Map<City, List<Theatre>> theatresByCity = new HashMap<>();
    private final Map<City, List<Movie>> moviesByCity = new HashMap<>();
    private final ReentrantLock scheduleLock = new ReentrantLock();

    public void registerTheatre(Theatre theatre) {
        theatresByCity.computeIfAbsent(theatre.getCity(), k -> new ArrayList<>()).add(theatre);
    }

    public void registerMovie(City city, Movie movie) {
        List<Movie> list = moviesByCity.computeIfAbsent(city, k -> new ArrayList<>());
        if (!list.contains(movie)) list.add(movie);
    }

    public List<Theatre> getTheatres(City city) {
        return theatresByCity.getOrDefault(city, List.of());
    }

    public List<Movie> getMovies(City city) {
        return moviesByCity.getOrDefault(city, List.of());
    }

    public void scheduleShow(Screen screen, Show show, City city) {
        scheduleLock.lock();
        try {
            for (Show existing : screen.getSchedule()) {
                if (existing.getSlot().equals(show.getSlot())) {
                    System.out.println("conflict: slot " + show.getSlot() + " already booked");
                    return;
                }
            }
            screen.registerShow(show);
            registerMovie(city, show.getMovie());
            System.out.println("scheduled: " + show);
        } finally {
            scheduleLock.unlock();
        }
    }

    public List<Show> findShowsByMovie(City city, Movie movie) {
        List<Show> result = new ArrayList<>();
        for (Theatre t : getTheatres(city))
            for (Screen scr : t.getScreens())
                for (Show sh : scr.getSchedule())
                    if (sh.getMovie().getCode().equals(movie.getCode()))
                        result.add(sh);
        return result;
    }

    public List<Show> findShowsByTheatre(Theatre theatre) {
        List<Show> result = new ArrayList<>();
        for (Screen scr : theatre.getScreens())
            result.addAll(scr.getSchedule());
        return result;
    }
}
