package com.example.reports;

// legacy concrete class (before Proxy refactor)
public class ReportFile {

    private final String id;
    private final String title;
    private final String level;

    public ReportFile(String id, String title, String level) {
        this.id = id;
        this.title = title;
        this.level = level;
    }

    public void show(User viewer) {
        String body = fetchFromDisk();
        System.out.println("--- Report ---");
        System.out.println("  id=" + id + "  title=" + title
                + "  level=" + level + "  viewer=" + viewer.getName());
        System.out.println("  Body: " + body);
    }

    private String fetchFromDisk() {
        System.out.println("[I/O] reading report " + id + " from disk ...");
        try { Thread.sleep(80); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return "Confidential content for \"" + title + "\"";
    }
}
