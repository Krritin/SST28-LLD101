package com.example.booking;

import java.util.HashSet;
import java.util.Set;

public class User {

    private static final Set<String> registeredEmails = new HashSet<>();

    private final String id;
    private final String name;
    private final String email;

    public User(String id, String name, String email) {
        if (registeredEmails.contains(email))
            throw new RuntimeException("email already registered: " + email);
        this.id = id;
        this.name = name;
        this.email = email;
        registeredEmails.add(email);
    }

    public String getId()    { return id; }
    public String getName()  { return name; }
    public String getEmail() { return email; }

    @Override
    public String toString() { return name + " <" + email + ">"; }
}
