package com.example.parking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {

    private final List<ParkingSlot> slots = new ArrayList<>();
    private final Map<String, Ticket> activeTickets = new HashMap<>();
    private final Map<SlotType, Double> hourlyRate = new HashMap<>();
    private int ticketSeq = 0;

    public ParkingLot() {
        hourlyRate.put(SlotType.COMPACT, 15.0);
        hourlyRate.put(SlotType.REGULAR, 25.0);
        hourlyRate.put(SlotType.OVERSIZED, 40.0);
    }

    public void addSlot(ParkingSlot slot) { slots.add(slot); }

    private boolean fits(VehicleType v, SlotType s) {
        if (v == VehicleType.BIKE)  return true;                                       // bike fits anywhere
        if (v == VehicleType.CAR)   return s == SlotType.REGULAR || s == SlotType.OVERSIZED;
        if (v == VehicleType.TRUCK) return s == SlotType.OVERSIZED;
        return false;
    }

    public Ticket parkVehicle(Vehicle vehicle, LocalDateTime entry, int gateId) {
        ParkingSlot nearest = null;
        int minDist = Integer.MAX_VALUE;

        for (ParkingSlot s : slots) {
            if (s.isTaken()) continue;
            if (!fits(vehicle.getType(), s.getType())) continue;
            int d = Math.abs(s.getProximity() - gateId);
            if (d < minDist) { minDist = d; nearest = s; }
        }

        if (nearest == null) {
            System.out.println("no available slot for " + vehicle.getPlate());
            return null;
        }

        nearest.markTaken();
        ticketSeq++;
        Ticket t = new Ticket("P-" + ticketSeq, vehicle, nearest, entry);
        activeTickets.put(t.getTicketNo(), t);
        return t;
    }

    public Bill checkout(Ticket ticket, LocalDateTime exitTime) {
        Ticket t = activeTickets.get(ticket.getTicketNo());
        if (t == null) { System.out.println("unknown ticket"); return null; }

        t.getSlot().markFree();
        activeTickets.remove(t.getTicketNo());

        long hrs = ChronoUnit.HOURS.between(t.getEntryAt(), exitTime);
        if (hrs < 1) hrs = 1;

        double rate = hourlyRate.get(t.getSlot().getType());
        return new Bill(t, exitTime, hrs, hrs * rate);
    }

    public void printStatus() {
        Map<SlotType, int[]> stats = new HashMap<>();
        for (SlotType st : SlotType.values()) stats.put(st, new int[]{0, 0});

        for (ParkingSlot s : slots) {
            int[] arr = stats.get(s.getType());
            arr[1]++;
            if (!s.isTaken()) arr[0]++;
        }

        for (SlotType st : SlotType.values()) {
            int[] arr = stats.get(st);
            System.out.println(st + ": " + arr[0] + "/" + arr[1] + " free");
        }
    }
}
