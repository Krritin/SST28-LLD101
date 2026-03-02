import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Demo showing immutable tickets:
 * - building a ticket
 * - "updating" by creating new instances
 * - external modifications to tags do not affect the ticket
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t = service.createTicket(
                "TCK-1001",
                "reporter@example.com",
                "Payment failing on checkout");
        System.out.println("Created: " + t);

        // Service "updates" now return new instances
        t = service.assign(t, "agent@example.com");
        t = service.escalateToCritical(t);
        System.out.println("\nAfter service updates (new instance): " + t);

        // External mutation of tags should NOT affect the ticket
        List<String> tags = t.getTags();
        tags.add("HACKED_FROM_OUTSIDE");
        System.out.println("\nAfter external tag mutation attempt: " + t);
    }
}

