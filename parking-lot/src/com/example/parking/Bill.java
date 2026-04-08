package com.example.parking;

import java.time.LocalDateTime;

public class Bill {

    private final Ticket ticket;
    private final LocalDateTime exitAt;
    private final long hours;
    private final double charge;

    public Bill(Ticket ticket, LocalDateTime exitAt, long hours, double charge) {
        this.ticket = ticket;
        this.exitAt = exitAt;
        this.hours = hours;
        this.charge = charge;
    }

    public double getCharge() { return charge; }

    public void print() {
        System.out.println(ticket.getTicketNo() + " | " + ticket.getVehicle().getPlate()
                + " | " + hours + "h | Rs " + charge);
    }
}
