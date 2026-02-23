import java.util.List;

/**
 * Single responsibility: format invoice data into printable text.
 * Preserves exact line order and format.
 */
public class InvoiceFormatter {

    public String format(String invId, List<InvoiceLine> lines, double subtotal,
                         double taxPct, double tax, double discount, double total) {
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(invId).append("\n");
        for (InvoiceLine l : lines) {
            out.append(String.format("- %s x%d = %.2f\n", l.name, l.qty, l.lineTotal));
        }
        out.append(String.format("Subtotal: %.2f\n", subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", taxPct, tax));
        out.append(String.format("Discount: -%.2f\n", discount));
        out.append(String.format("TOTAL: %.2f\n", total));
        return out.toString();
    }
}
