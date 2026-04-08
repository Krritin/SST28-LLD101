package com.example.booking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingService {

    private final Map<String, MovieTicket> ledger = new HashMap<>();
    private int bookingSeq = 0;
    private int paymentSeq = 0;
    private final PricingService pricing;

    public BookingService(PricingService pricing) {
        this.pricing = pricing;
    }

    public void printSeatMap(Show show) {
        System.out.println("Seat map for " + show);
        for (Seat s : show.getScreen().getSeats()) {
            SeatStatus st = show.getSeatMap().get(s.getLabel());
            int p = pricing.computePrice(s, show);
            System.out.println("  " + s.getLabel() + " " + s.getType() + " Rs" + p + " " + st);
        }
    }

    public List<String> holdSeats(Show show, List<String> labels) {
        show.getLock().lock();
        try {
            List<String> held = new ArrayList<>();
            for (String lbl : labels) {
                if (show.isFree(lbl)) {
                    show.holdSeat(lbl);
                    held.add(lbl);
                } else {
                    System.out.println(lbl + " unavailable");
                }
            }
            return held;
        } finally {
            show.getLock().unlock();
        }
    }

    private void releaseHeldSeats(Show show, List<String> labels) {
        for (String lbl : labels) {
            if (show.getSeatMap().get(lbl) == SeatStatus.HELD)
                show.releaseSeat(lbl);
        }
    }

    public MovieTicket confirmBooking(Show show, List<String> heldLabels,
                                      User buyer, PaymentMode mode) {
        show.getLock().lock();
        try {
            for (String lbl : heldLabels) {
                if (show.getSeatMap().get(lbl) != SeatStatus.HELD) {
                    System.out.println(lbl + " not held — aborting");
                    releaseHeldSeats(show, heldLabels);
                    return null;
                }
            }

            List<Seat> soldSeats = new ArrayList<>();
            int grandTotal = 0;
            for (Seat seat : show.getScreen().getSeats()) {
                if (heldLabels.contains(seat.getLabel())) {
                    int price = pricing.computePrice(seat, show);
                    show.sellSeat(seat.getLabel());
                    soldSeats.add(seat);
                    grandTotal += price;
                }
            }

            paymentSeq++;
            Payment pay = new Payment("TXN" + paymentSeq, grandTotal, mode);
            System.out.println("payment OK: " + pay);

            bookingSeq++;
            MovieTicket ticket = new MovieTicket("BK" + bookingSeq, show, soldSeats,
                    grandTotal, buyer, pay);
            ledger.put(ticket.getBookingId(), ticket);
            return ticket;
        } finally {
            show.getLock().unlock();
        }
    }

    public int cancelBooking(String bookingId) {
        MovieTicket ticket = ledger.get(bookingId);
        if (ticket == null) { System.out.println("booking not found: " + bookingId); return 0; }
        if (ticket.getStatus() == BookingStatus.CANCELLED) { System.out.println("already cancelled"); return 0; }

        Show show = ticket.getShow();
        show.getLock().lock();
        try {
            for (Seat s : ticket.getSeats()) show.releaseSeat(s.getLabel());
            ticket.cancel();
            ticket.getPayment().refund();
            System.out.println("refunded Rs" + ticket.getTotal() + " via " + ticket.getPayment().getMode());
            return ticket.getTotal();
        } finally {
            show.getLock().unlock();
        }
    }
}
