package com.example.reports;

public class ReportViewer {

    public void openReport(Report report, User viewer) {
        report.show(viewer);
    }
}
