package com.example.reports;

public class ReportProxy implements Report {

    private final String id;
    private final String title;
    private final String level;
    private final AccessControl acl = new AccessControl();
    private RealReport cached = null;     // lazy — only created on first allowed access

    public ReportProxy(String id, String title, String level) {
        this.id = id;
        this.title = title;
        this.level = level;
    }

    @Override
    public void show(User viewer) {
        if (!acl.isAllowed(viewer, level)) {
            System.out.println("** Access Blocked ** " + viewer.getName()
                    + " has no permission for " + level + " report [" + id + "]");
            return;
        }

        if (cached == null) {
            cached = new RealReport(id, title, level);
        }
        cached.show(viewer);
    }
}
