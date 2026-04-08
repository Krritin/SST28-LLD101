package com.example.reports;

public class User {

    private final String name;
    private final String role;   // STUDENT, FACULTY, ADMIN

    public User(String name, String role) {
        this.name = name;
        this.role = role.toUpperCase();
    }

    public String getName() { return name; }
    public String getRole() { return role; }

    @Override
    public String toString() { return name + " [" + role + "]"; }
}
