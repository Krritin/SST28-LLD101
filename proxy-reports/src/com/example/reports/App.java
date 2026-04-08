package com.example.reports;

public class App {

    public static void main(String[] args) {
        User student = new User("Ananya", "STUDENT");
        User faculty = new User("Dr. Sharma", "FACULTY");
        User admin   = new User("Vikram", "ADMIN");

        Report publicDoc  = new ReportProxy("RPT-001", "Welcome Guide", "PUBLIC");
        Report facultyDoc = new ReportProxy("RPT-002", "Semester Analysis", "FACULTY");
        Report adminDoc   = new ReportProxy("RPT-003", "Financial Report", "ADMIN");

        ReportViewer viewer = new ReportViewer();

        System.out.println("=== CampusVault Demo ===\n");

        // student can see public
        viewer.openReport(publicDoc, student);
        System.out.println();

        // student blocked from faculty report
        viewer.openReport(facultyDoc, student);
        System.out.println();

        // faculty can see faculty report
        viewer.openReport(facultyDoc, faculty);
        System.out.println();

        // admin sees admin report (first load)
        viewer.openReport(adminDoc, admin);
        System.out.println();

        // admin sees admin report again (cached, no disk I/O)
        viewer.openReport(adminDoc, admin);
    }
}
