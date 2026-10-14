
package app;

import model.Ticket;
import service.TicketService;
import exception.TicketNotFoundException;

public class TicketServiceApp {

    public static void main(String[] args) {

        TicketService service = new TicketService();

        // Create sample tickets
        Ticket ticket1 = new Ticket(
                101L, "Laptop not working",
                "Laptop does not start", "Hardware",
                "HIGH", "OPEN", "Employee1", null
        );

        Ticket ticket2 = new Ticket(
                102L, "Login issue",
                "Unable to log in", "Software",
                "MEDIUM", "OPEN", "Employee2", null
        );

        // Create tickets
        System.out.println("===== CREATE TICKETS =====");
        service.createTicket(ticket1);
        service.createTicket(ticket2);

        // Display all tickets
        System.out.println("\n===== ALL TICKETS =====");
        for (Ticket ticket : service.getAllTickets()) {
            System.out.println(ticket);
        }

        // Search for an existing ticket
        System.out.println("\n===== SEARCH TICKET =====");
        System.out.println(service.getTicketById(101L));

        // Update ticket status
        System.out.println("\n===== UPDATE STATUS =====");
        boolean updated =
                service.updateTicketStatus(101L, "IN_PROGRESS");

        if (!updated) {
            System.out.println("Ticket not found.");
        }

        System.out.println("\n===== UPDATED TICKET =====");
        System.out.println(service.getTicketById(101L));

        // Test a missing ticket
        System.out.println("\n===== TEST MISSING TICKET =====");
        try {
            service.getTicketById(999L);
        } catch (TicketNotFoundException e) {
            System.out.println("Handled correctly: " + e.getMessage());
        }

        // Test duplicate ticket ID
        System.out.println("\n===== TEST DUPLICATE ID =====");
        try {
            Ticket duplicate = new Ticket(
                    101L, "Duplicate ticket",
                    "Testing duplicate ID", "Hardware",
                    "LOW", "OPEN", "Employee3", null
            );

            service.createTicket(duplicate);

        } catch (IllegalArgumentException e) {
            System.out.println("Duplicate rejected: " + e.getMessage());
        }

        // Test support group Set
        System.out.println("\n===== TEST SUPPORT GROUP SET =====");
        service.addSupportGroup("Hardware");
        service.addSupportGroup("Software");
        service.addSupportGroup("Hardware");

        System.out.println(
                "Registered groups: " + service.getSupportGroups()
        );

        // Test pending ticket Queue
        System.out.println("\n===== TEST PENDING TICKET QUEUE =====");
        service.enqueueTicket(101L);
        service.enqueueTicket(102L);

        service.processNextPendingTicket();
        service.processNextPendingTicket();
        service.processNextPendingTicket();

        // Test FR3: update status, priority, and notes
        System.out.println(
                "\n===== TEST UPDATE STATUS, PRIORITY AND NOTES ====="
        );

        service.updateTicketDetails(
                101L,
                "IN_PROGRESS",
                "HIGH",
                "Assigned to hardware team for investigation."
        );

        Ticket updatedTicket = service.getTicketById(101L);

        System.out.println("Updated ticket: " + updatedTicket);
        System.out.println("Updated notes: " + updatedTicket.getNotes());

        // FR4: Test SLA tracking
        System.out.println("\n===== FR4: SLA TRACKING =====");

        service.setSlaDeadline(101L, 24);

        System.out.println(
                "Hours remaining: "
                        + service.getSlaHoursRemaining(101L)
        );

        System.out.println(
                "SLA breached: " + service.isSlaBreached(101L)
        );

        // FR5: Test ticket reassignment
        System.out.println("\n===== FR5: TEAM LEAD REASSIGNMENT =====");

        service.reassignTicket(101L, "Agent2");

        System.out.println(
                "Ticket after reassignment: "
                        + service.getTicketById(101L)
        );

        // FR6: Test Knowledge Base article linking
        System.out.println("\n===== FR6: KNOWLEDGE BASE LINKING =====");

        service.linkKbArticle(
                101L, "KB-001: Laptop Troubleshooting"
        );

        service.linkKbArticle(
                101L, "https://help.example.com/laptop"
        );

        System.out.println(
                "Linked KB articles: "
                        + service.getLinkedKbArticles(101L)
        );

        // Day 8: Test tickets sorted by ID
        System.out.println("\n===== TICKETS SORTED BY ID =====");

        for (Ticket ticket : service.getTicketsSortedById()) {
            System.out.println(ticket);
        }

        // Day 8: Test tickets sorted by priority
        System.out.println("\n===== TICKETS SORTED BY PRIORITY =====");

        for (Ticket ticket : service.getTicketsSortedByPriority()) {
            System.out.println(ticket);
        }

        // Test ticket deletion
        System.out.println("\n===== TEST DELETE TICKET =====");

        boolean deleted = service.deleteTicket(102L);

        if (!deleted) {
            System.out.println("Ticket not found.");
        }

        // Verify deletion using the custom exception
        System.out.println("\n===== VERIFY DELETION =====");

        try {
            service.getTicketById(102L);
        } catch (TicketNotFoundException e) {
            System.out.println("Handled correctly: " + e.getMessage());
        }
    }
}
