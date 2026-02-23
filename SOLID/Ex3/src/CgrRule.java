public class CgrRule implements Rule {
    private final RuleInput cfg;
    public CgrRule(RuleInput cfg) { this.cfg = cfg; }

    @Override
    public String check(StudentProfile s) {
        if (s.cgr < cfg.minCgr) return String.format("CGR below %.1f", cfg.minCgr);
        return null;
    }
}
