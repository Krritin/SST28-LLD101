import java.nio.charset.StandardCharsets;

public class JsonExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        String title = req != null && req.title != null ? req.title : "";
        String body = req != null && req.body != null ? req.body : "";
        String json = "{\"title\":\"" + escape(title) + "\",\"body\":\"" + escape(body) + "\"}";
        return new ExportResult("application/json", json.getBytes(StandardCharsets.UTF_8));
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("\"", "\\\"");
    }
}
