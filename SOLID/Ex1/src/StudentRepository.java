/**
 * Abstraction for saving and counting student records.
 * Decouples onboarding flow from concrete storage (e.g. FakeDb).
 */
public interface StudentRepository {
    void save(StudentRecord r);
    int count();
}
