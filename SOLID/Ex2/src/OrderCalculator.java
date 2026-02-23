import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Single responsibility: compute subtotal and invoice lines from menu + order lines.
 */
public class OrderCalculator {

    public static class Result {
        public final double subtotal;
        public final List<InvoiceLine> lines;

        public Result(double subtotal, List<InvoiceLine> lines) {
            this.subtotal = subtotal;
            this.lines = new ArrayList<>(lines);
        }
    }

    public Result calculate(Map<String, MenuItem> menu, List<OrderLine> orderLines) {
        double subtotal = 0.0;
        List<InvoiceLine> lines = new ArrayList<>();
        for (OrderLine l : orderLines) {
            MenuItem item = menu.get(l.itemId);
            if (item == null) continue;
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            lines.add(new InvoiceLine(item.name, l.qty, lineTotal));
        }
        return new Result(subtotal, lines);
    }
}
