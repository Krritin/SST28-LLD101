package com.example.booking;

public class DemandPricingRule implements PricingRule {

    private final double occupancyThreshold;
    private final double multiplier;

    public DemandPricingRule(double occupancyThreshold, double multiplier) {
        this.occupancyThreshold = occupancyThreshold;
        this.multiplier = multiplier;
    }

    @Override
    public int apply(int base, Show show) {
        int totalSeats = show.getScreen().getSeats().size();
        long soldCount = show.getSeatMap().values().stream()
                .filter(s -> s == SeatStatus.SOLD).count();
        double occupancy = (double) soldCount / totalSeats;
        if (occupancy >= occupancyThreshold)
            return (int)(base * multiplier);
        return base;
    }
}
