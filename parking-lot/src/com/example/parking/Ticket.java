package com.example.parking;

import java.time.LocalDateTime;

public class Ticket {

    private final String ticketNo;
    private final Vehicle vehicle;
    private final ParkingSlot slot;
    private final LocalDateTime entryAt;

    public Ticket(String ticketNo, Vehicle vehicle, ParkingSlot slot, LocalDateTime entryAt) {
        this.ticketNo = ticketNo;
        this.vehicle = vehicle;
        this.slot = slot;
        this.entryAt = entryAt;
    }

    public String getTicketNo()       { return ticketNo; }
    public Vehicle getVehicle()       { return vehicle; }
    public ParkingSlot getSlot()      { return slot; }
    public LocalDateTime getEntryAt() { return entryAt; }

    public void print() {
        System.out.println(ticketNo + " | " + vehicle.getPlate()
                + " | Slot-" + slot.getNumber() + " (" + slot.getType()
                + ") | Level-" + slot.getLevel());
    }
}
