public class CreditsRule implements Rule {
    private final RuleInput cfg;
    public CreditsRule(RuleInput cfg) { this.cfg = cfg; }

    @Override
    public String check(StudentProfile s) {
        if (s.earnedCredits < cfg.minCredits) return "credits below " + cfg.minCredits;
        return null;
    }
}
