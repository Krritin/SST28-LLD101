/** Abstraction for discount rules so new rules can be added without editing orchestrator. */
public interface DiscountPolicy {
    double getDiscountAmount(String customerType, double subtotal, int lineCount);
}
