import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Single responsibility: validate parsed onboarding input.
 * Returns list of error messages; empty list means valid.
 */
public class OnboardingValidator {
    private final List<String> allowedPrograms;

    public OnboardingValidator(List<String> allowedPrograms) {
        this.allowedPrograms = allowedPrograms != null ? new ArrayList<>(allowedPrograms) : new ArrayList<>();
    }

    /** Default: CSE, AI, SWE */
    public static OnboardingValidator defaultValidator() {
        return new OnboardingValidator(Arrays.asList("CSE", "AI", "SWE"));
    }

    public List<String> validate(ParsedInput input) {
        List<String> errors = new ArrayList<>();
        if (input.name.isBlank()) errors.add("name is required");
        if (input.email.isBlank() || !input.email.contains("@")) errors.add("email is invalid");
        if (input.phone.isBlank() || !input.phone.chars().allMatch(Character::isDigit)) errors.add("phone is invalid");
        if (!allowedPrograms.contains(input.program)) errors.add("program is invalid");
        return errors;
    }
}
