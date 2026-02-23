public interface Rule {
    /**
     * Returns a reason string when the rule fails, otherwise null.
     */
    String check(StudentProfile s);
}
