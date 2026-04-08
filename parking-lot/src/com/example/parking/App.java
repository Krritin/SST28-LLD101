package com.example.parking;

import java.time.LocalDateTime;

public class App {

    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot();

        lot.addSlot(new ParkingSlot(1, SlotType.COMPACT,  0, 1));
        lot.addSlot(new ParkingSlot(2, SlotType.COMPACT,  0, 2));
        lot.addSlot(new ParkingSlot(3, SlotType.REGULAR,  0, 3));
        lot.addSlot(new ParkingSlot(4, SlotType.REGULAR,  0, 4));
        lot.addSlot(new ParkingSlot(5, SlotType.OVERSIZED,0, 5));
        lot.addSlot(new ParkingSlot(6, SlotType.COMPACT,  1, 6));
        lot.addSlot(new ParkingSlot(7, SlotType.REGULAR,  1, 7));
        lot.addSlot(new ParkingSlot(8, SlotType.OVERSIZED,1, 8));

        LocalDateTime now = LocalDateTime.now();

        System.out.println("=== before parking ===");
        lot.printStatus();

        System.out.println("\n=== parking vehicles ===");
        Vehicle bike  = new Vehicle("MH01-AB-1234", VehicleType.BIKE);
        Vehicle car   = new Vehicle("MH02-CD-5678", VehicleType.CAR);
        Vehicle truck = new Vehicle("MH03-EF-9999", VehicleType.TRUCK);

        Ticket t1 = lot.parkVehicle(bike, now, 1);   t1.print();
        Ticket t2 = lot.parkVehicle(car, now, 3);     t2.print();
        Ticket t3 = lot.parkVehicle(truck, now, 1);   t3.print();

        System.out.println("\n=== after parking ===");
        lot.printStatus();

        System.out.println("\n=== checkout ===");
        lot.checkout(t1, now.plusHours(2)).print();
        lot.checkout(t2, now.plusHours(4)).print();
        lot.checkout(t3, now.plusHours(1)).print();

        System.out.println("\n=== after checkout ===");
        lot.printStatus();
    }
}
