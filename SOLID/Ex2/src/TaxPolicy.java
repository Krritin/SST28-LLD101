/** Abstraction for tax rules so new rules can be added without editing the checkout orchestrator. */
public interface TaxPolicy {
    double getTaxPercent(String customerType);
}

