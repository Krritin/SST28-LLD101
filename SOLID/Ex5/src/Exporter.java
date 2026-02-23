/**
 * Base contract: export(req) never throws.
 * Precondition: req is not null (title/body may be null, treated as empty).
 * Postcondition: returns non-null ExportResult; on failure success=false and errorMessage set.
 */
public abstract class Exporter {
    public abstract ExportResult export(ExportRequest req);
}
