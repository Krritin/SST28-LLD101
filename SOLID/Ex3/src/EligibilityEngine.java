import java.util.*;

public class EligibilityEngine {
    private final FakeEligibilityStore store;

    public EligibilityEngine(FakeEligibilityStore store) { this.store = store; }

    public void runAndPrint(StudentProfile s) {
        ReportPrinter p = new ReportPrinter();
        EligibilityEngineResult r = evaluate(s);
        p.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {
        RuleInput cfg = new RuleInput();
        List<Rule> rules = List.of(
                new DisciplinaryRule(),
                new CgrRule(cfg),
                new AttendanceRule(cfg),
                new CreditsRule(cfg)
        );

        List<String> reasons = new ArrayList<>();
        for (Rule r : rules) {
            String reason = r.check(s);
            if (reason != null) {
                reasons.add(reason);
                break; // report first failure only to match sample output
            }
        }

        String status = reasons.isEmpty() ? "ELIGIBLE" : "NOT_ELIGIBLE";
        return new EligibilityEngineResult(status, reasons);
    }
}

class EligibilityEngineResult {
    public final String status;
    public final List<String> reasons;
    public EligibilityEngineResult(String status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }
}
