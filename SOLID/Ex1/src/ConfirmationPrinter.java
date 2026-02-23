import java.util.List;

/**
 * Abstraction for printing onboarding confirmation and errors.
 * Keeps formatting/output separate from business logic.
 */
public interface ConfirmationPrinter {
    void printInput(String raw);
    void printErrors(List<String> errors);
    void printSuccess(StudentRecord rec, int totalCount);
}
