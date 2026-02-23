/**
 * Result of an export. Never null.
 * When success is false, errorMessage is set and bytes may be empty.
 */
public class ExportResult {
    public final String contentType;
    public final byte[] bytes;
    public final boolean success;
    public final String errorMessage;

    public ExportResult(String contentType, byte[] bytes) {
        this(contentType, bytes, true, null);
    }

    public ExportResult(String contentType, byte[] bytes, boolean success, String errorMessage) {
        this.contentType = contentType;
        this.bytes = bytes != null ? bytes : new byte[0];
        this.success = success;
        this.errorMessage = errorMessage;
    }
}
