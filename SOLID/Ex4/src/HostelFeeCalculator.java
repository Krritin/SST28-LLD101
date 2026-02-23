import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final PriceCalculator priceCalculator;

    public HostelFeeCalculator(FakeBookingRepo repo, PriceCalculator priceCalculator) {
        this.repo = repo;
        this.priceCalculator = priceCalculator;
    }

    public void process(BookingRequest req) {
        Money monthly = priceCalculator.calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + 7781;
        repo.save(bookingId, req, monthly, deposit);
    }
}
