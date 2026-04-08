package com.example.ratelimit;

public class ExternalService {

    private final String serviceName;

    public ExternalService(String serviceName) {
        this.serviceName = serviceName;
    }

    public String invoke(String payload) {
        return serviceName + " handled: " + payload;
    }

    public String getServiceName() { return serviceName; }
}
