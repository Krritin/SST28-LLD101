import java.util.*;

/**
 * Orchestrates checkout only: delegates pricing, tax, discount, formatting, persistence.
 * Does not encode tax/discount specifics nor format strings itself.
 */
public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final OrderCalculator calculator = new OrderCalculator();
    private final TaxPolicy taxPolicy;
    private final DiscountPolicy discountPolicy;
    private final InvoiceFormatter formatter;
    private final InvoiceStore store;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(TaxPolicy taxPolicy, DiscountPolicy discountPolicy,
                           InvoiceFormatter formatter, InvoiceStore store) {
        this.taxPolicy = taxPolicy;
        this.discountPolicy = discountPolicy;
        this.formatter = formatter;
        this.store = store;
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);

        OrderCalculator.Result calc = calculator.calculate(menu, lines);
        double subtotal = calc.subtotal;
        double taxPct = taxPolicy.getTaxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);
        double discount = discountPolicy.getDiscountAmount(customerType, subtotal, lines.size());
        double total = subtotal + tax - discount;

        String printable = formatter.format(invId, calc.lines, subtotal, taxPct, tax, discount, total);
        System.out.print(printable);
        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
