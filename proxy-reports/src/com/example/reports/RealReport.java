package com.example.reports;

public class RealReport implements Report {

    private final String id;
    private final String title;
    private final String level;
    private String body = null;   // loaded lazily

    public RealReport(String id, String title, String level) {
        this.id = id;
        this.title = title;
        this.level = level;
    }

    @Override
    public void show(User viewer) {
        if (body == null) {
            body = fetchFromDisk();
        }
        System.out.println("--- Report ---");
        System.out.println("  id=" + id + "  title=" + title
                + "  level=" + level + "  viewer=" + viewer.getName());
        System.out.println("  Body: " + body);
    }

    public String getLevel() { return level; }

    private String fetchFromDisk() {
        System.out.println("[I/O] reading report " + id + " from disk ...");
        try { Thread.sleep(80); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return "Confidential content for \"" + title + "\"";
    }
}
