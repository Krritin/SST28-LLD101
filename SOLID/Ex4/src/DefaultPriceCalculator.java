public class DefaultPriceCalculator implements PriceCalculator {
    @Override
    public Money calculateMonthly(BookingRequest req) {
        double base;
        switch (req.roomType) {
            case LegacyRoomTypes.SINGLE -> base = 14000.0;
            case LegacyRoomTypes.DOUBLE -> base = 14000.0;
            case LegacyRoomTypes.TRIPLE -> base = 12000.0;
            default -> base = 16000.0;
        }

        double add = 0.0;
        for (AddOn a : req.addOns) {
            if (a == AddOn.MESS) add += 1000.0;
            else if (a == AddOn.LAUNDRY) add += 1000.0;
            else if (a == AddOn.GYM) add += 300.0;
        }

        return new Money(base + add);
    }
}
