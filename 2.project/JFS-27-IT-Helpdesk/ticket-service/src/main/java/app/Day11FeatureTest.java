
package app;

import model.Ticket;
import service.TicketService;

public class Day11FeatureTest {

    public static void main(String[] args) {

        TicketService service = new TicketService();

        // Create tickets assigned to different agents
        service.createTicket(new Ticket(
                201L, "Laptop issue", "Laptop not starting",
                "Hardware", "HIGH", "OPEN", "Arun", "Agent1"));

        service.createTicket(new Ticket(
                202L, "Login issue", "Cannot log in",
                "Software", "MEDIUM", "OPEN", "Meena", "Agent1"));

        service.createTicket(new Ticket(
                203L, "Keyboard issue", "Keyboard not working",
                "Hardware", "LOW", "OPEN", "Ravi", "Agent2"));

        System.out.println("\n===== FR7: REOPEN TICKET =====");

        service.closeTicket(201L);
        service.reopenTicket(201L);

        System.out.println("Ticket 201 status: "
                + service.getTicketById(201L).getStatus());

        System.out.println("\n===== FR8: AGENT METRICS =====");

        service.updateTicketStatus(202L, "RESOLVED");

        service.printAgentMetrics("Agent1");
        service.printAgentMetrics("Agent2");
    }
}
