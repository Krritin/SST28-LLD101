package com.example.reports;

public class QuickCheck {

    public static void main(String[] args) {
        User student = new User("Sneha", "STUDENT");
        User admin   = new User("Vikram", "ADMIN");

        Report restricted  = new ReportProxy("RPT-003", "Financial Report", "ADMIN");
        Report facultyOnly = new ReportProxy("RPT-002", "Semester Analysis", "FACULTY");

        System.out.println("=== Quick Verification ===");
        facultyOnly.show(student);       // denied
        System.out.println();
        restricted.show(admin);          // loads from disk
        System.out.println();
        restricted.show(admin);          // cached — no disk read
    }
}
