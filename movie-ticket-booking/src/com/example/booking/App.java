package com.example.booking;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) throws InterruptedException {

        TheatreService theatreSvc = new TheatreService();

        PricingService pricingSvc = new PricingService();
        pricingSvc.addRule(new SlotPricingRule());
        pricingSvc.addRule(new DemandPricingRule(0.7, 1.5));
        pricingSvc.addRule(new WeekendPricingRule());

        BookingService bookingSvc = new BookingService(pricingSvc);

        // screens
        List<Seat> hall1Seats = Arrays.asList(
            new Seat("R1", SeatType.SILVER, 180),
            new Seat("R2", SeatType.SILVER, 180),
            new Seat("R3", SeatType.GOLD, 300),
            new Seat("G1", SeatType.GOLD, 300),
            new Seat("P1", SeatType.PLATINUM, 500)
        );
        Screen hall1 = new Screen("H1", hall1Seats);

        List<Seat> hall2Seats = Arrays.asList(
            new Seat("X1", SeatType.SILVER, 160),
            new Seat("X2", SeatType.SILVER, 160),
            new Seat("X3", SeatType.PLATINUM, 420)
        );
        Screen hall2 = new Screen("H2", hall2Seats);

        Theatre inox = new Theatre("TH1", "INOX Forum", City.MUMBAI);
        inox.addScreen(hall1);
        inox.addScreen(hall2);
        theatreSvc.registerTheatre(inox);

        // movies
        Movie darkKnight = new Movie("MV1", "The Dark Knight", 152);
        Movie dune       = new Movie("MV2", "Dune Part Two", 166);
        Movie rrr        = new Movie("MV3", "RRR", 187);

        // shows
        Show s1 = new Show("S1", darkKnight, hall1, "10:00AM");
        Show s2 = new Show("S2", dune, hall1, "6:00PM");
        Show s3 = new Show("S3", rrr, hall2, "2:00PM");
        Show dup = new Show("S4", rrr, hall1, "10:00AM");   // conflicts

        theatreSvc.scheduleShow(hall1, s1, City.MUMBAI);
        theatreSvc.scheduleShow(hall1, s2, City.MUMBAI);
        theatreSvc.scheduleShow(hall2, s3, City.MUMBAI);
        theatreSvc.scheduleShow(hall1, dup, City.MUMBAI);

        System.out.println("\nMovies: " + theatreSvc.getMovies(City.MUMBAI));
        System.out.println("Theatres: " + theatreSvc.getTheatres(City.MUMBAI));
        System.out.println("DarkKnight shows: " + theatreSvc.findShowsByMovie(City.MUMBAI, darkKnight));
        System.out.println("INOX shows: " + theatreSvc.findShowsByTheatre(inox));

        System.out.println();
        bookingSvc.printSeatMap(s1);

        User u1 = new User("U1", "Aarav", "aarav@mail.com");
        User u2 = new User("U2", "Diya", "diya@mail.com");

        try { new User("U3", "Dup", "aarav@mail.com"); }
        catch (RuntimeException e) { System.out.println(e.getMessage()); }

        System.out.println();
        List<String> held1 = bookingSvc.holdSeats(s1, Arrays.asList("R1", "R2"));
        bookingSvc.printSeatMap(s1);

        MovieTicket t1 = bookingSvc.confirmBooking(s1, held1, u1, PaymentMode.UPI);
        System.out.println(t1);

        List<String> held2 = bookingSvc.holdSeats(s1, Arrays.asList("R1"));  // already sold
        System.out.println("held=" + held2);

        List<String> held3 = bookingSvc.holdSeats(s1, Arrays.asList("R3", "P1"));
        MovieTicket t2 = bookingSvc.confirmBooking(s1, held3, u2, PaymentMode.CREDIT_CARD);
        System.out.println(t2);

        // concurrency test
        System.out.println();
        Thread th1 = new Thread(() -> {
            List<String> h = bookingSvc.holdSeats(s1, Arrays.asList("G1"));
            if (!h.isEmpty()) {
                System.out.println("th1 " + bookingSvc.confirmBooking(s1, h, u1, PaymentMode.UPI));
            } else System.out.println("th1 failed");
        });
        Thread th2 = new Thread(() -> {
            List<String> h = bookingSvc.holdSeats(s1, Arrays.asList("G1"));
            if (!h.isEmpty()) {
                System.out.println("th2 " + bookingSvc.confirmBooking(s1, h, u2, PaymentMode.DEBIT_CARD));
            } else System.out.println("th2 failed");
        });
        th1.start(); th2.start();
        th1.join();  th2.join();

        // cancel + rebook
        System.out.println("\n" + t1);
        bookingSvc.cancelBooking(t1.getBookingId());
        System.out.println(t1);

        List<String> held4 = bookingSvc.holdSeats(s1, Arrays.asList("R1"));
        MovieTicket t3 = bookingSvc.confirmBooking(s1, held4, u2, PaymentMode.WALLET);
        System.out.println(t3);

        // concurrent show scheduling
        System.out.println();
        Show s5 = new Show("S5", dune, hall1, "9:00PM");
        Show s6 = new Show("S6", darkKnight, hall1, "9:00PM");
        Thread a1 = new Thread(() -> theatreSvc.scheduleShow(hall1, s5, City.MUMBAI));
        Thread a2 = new Thread(() -> theatreSvc.scheduleShow(hall1, s6, City.MUMBAI));
        a1.start(); a2.start();
        a1.join();  a2.join();

        // change pricing rules
        pricingSvc.resetRules();
        pricingSvc.addRule(new DemandPricingRule(0.5, 2.0));
        System.out.println();
        bookingSvc.printSeatMap(s1);
    }
}
