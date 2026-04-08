package com.example.reports;

public class AccessControl {

    public boolean isAllowed(User user, String level) {
        String r = user.getRole();

        if ("ADMIN".equals(level))   return "ADMIN".equals(r);
        if ("FACULTY".equals(level)) return "FACULTY".equals(r) || "ADMIN".equals(r);
        if ("PUBLIC".equals(level))  return true;

        return false;
    }
}
