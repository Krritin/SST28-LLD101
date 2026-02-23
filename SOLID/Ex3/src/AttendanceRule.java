public class AttendanceRule implements Rule {
    private final RuleInput cfg;
    public AttendanceRule(RuleInput cfg) { this.cfg = cfg; }

    @Override
    public String check(StudentProfile s) {
        if (s.attendancePct < cfg.minAttendance) return "attendance below " + cfg.minAttendance;
        return null;
    }
}
