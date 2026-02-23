import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {
    private static final int MAX_BODY_LENGTH = 20;

    @Override
    public ExportResult export(ExportRequest req) {
        String body = req.body != null ? req.body : "";
        if (body.length() > MAX_BODY_LENGTH) {
            return new ExportResult("application/pdf", new byte[0], false, "PDF cannot handle content > 20 chars");
        }
        String title = req.title != null ? req.title : "";
        String fakePdf = "PDF(" + title + "):" + body;
        return new ExportResult("application/pdf", fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}
