import java.nio.charset.StandardCharsets;

/** CSV encoding: escapes newlines/commas for valid CSV; preserves content in quoted form. */
public class CsvExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        String title = req.title != null ? req.title : "";
        String body = req.body != null ? req.body : "";
        String bodyEscaped = body.replace("\n", " ").replace(",", " ");
        String csv = "title,body\n" + title + "," + bodyEscaped + "\n";
        return new ExportResult("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }
}
