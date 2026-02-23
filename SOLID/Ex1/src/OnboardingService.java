/**
 * Orchestrates onboarding: parse -> validate -> create record -> save -> confirm.
 * Does not format output nor depend on concrete storage; uses abstractions.
 */
public class OnboardingService {
    private final StudentRepository repo;
    private final RawInputParser parser;
    private final OnboardingValidator validator;
    private final ConfirmationPrinter printer;

    public OnboardingService(StudentRepository repo, RawInputParser parser,
                             OnboardingValidator validator, ConfirmationPrinter printer) {
        this.repo = repo;
        this.parser = parser;
        this.validator = validator;
        this.printer = printer;
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        ParsedInput parsed = parser.parse(raw);
        java.util.List<String> errors = validator.validate(parsed);

        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(repo.count());
        StudentRecord rec = new StudentRecord(id, parsed.name, parsed.email, parsed.phone, parsed.program);
        repo.save(rec);
        printer.printSuccess(rec, repo.count());
    }
}
