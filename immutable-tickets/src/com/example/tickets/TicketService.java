package com.example.tickets;

/**
 * Service layer that creates and \"updates\" tickets.
 *
 * With immutable IncidentTicket, all updates return NEW instances.
 */
public class TicketService {

    public IncidentTicket createTicket(String id, String reporterEmail, String title) {
        // Validation is delegated to the builder.
        return IncidentTicket.builder()
                .id(id)
                .reporterEmail(reporterEmail)
                .title(title)
                .priority("MEDIUM")
                .source("CLI")
                .customerVisible(false)
                .addTag("NEW")
                .build();
    }

    public IncidentTicket escalateToCritical(IncidentTicket t) {
        return t.toBuilder()
                .priority("CRITICAL")
                .addTag("ESCALATED")
                .build();
    }

    public IncidentTicket assign(IncidentTicket t, String assigneeEmail) {
        return t.toBuilder()
                .assigneeEmail(assigneeEmail)
                .build();
    }
}

