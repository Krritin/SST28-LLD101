package com.example.booking;

import java.util.List;

public class MovieTicket {

    private final String bookingId;
    private final Show show;
    private final List<Seat> seats;
    private final int total;
    private final User buyer;
    private final Payment payment;
    private BookingStatus status;

    public MovieTicket(String bookingId, Show show, List<Seat> seats,
                       int total, User buyer, Payment payment) {
        this.bookingId = bookingId;
        this.show = show;
        this.seats = seats;
        this.total = total;
        this.buyer = buyer;
        this.payment = payment;
        this.status = BookingStatus.ACTIVE;
    }

    public void cancel() { this.status = BookingStatus.CANCELLED; }

    public String getBookingId()     { return bookingId; }
    public Show getShow()            { return show; }
    public List<Seat> getSeats()     { return seats; }
    public int getTotal()            { return total; }
    public BookingStatus getStatus() { return status; }
    public User getBuyer()           { return buyer; }
    public Payment getPayment()      { return payment; }

    @Override
    public String toString() {
        return bookingId + " | " + buyer.getName() + " | " + show.getMovie().getTitle()
                + " | seats=" + seats + " | Rs" + total + " | " + status;
    }
}
