package app;

import model.Ticket;
import service.TicketService;

public class TicketServiceApp {

    public static void main(String[] args) {

        TicketService service = new TicketService();

        // Create sample tickets
        Ticket ticket1 = new Ticket(
                101L,
                "Laptop not working",
                "Laptop does not start",
                "Hardware",
                "HIGH",
                "OPEN",
                "Employee1",
                null
        );

        Ticket ticket2 = new Ticket(
                102L,
                "Login issue",
                "Unable to log in",
                "Software",
                "MEDIUM",
                "OPEN",
                "Employee2",
                null
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

        // Search for a ticket
        System.out.println("\n===== SEARCH TICKET =====");

        Ticket found = service.getTicketById(101L);

        if (found != null) {
            System.out.println(found);
        } else {
            System.out.println("Ticket not found.");
        }

        // Update ticket status
        System.out.println("\n===== UPDATE STATUS =====");

        boolean updated =
                service.updateTicketStatus(101L, "IN_PROGRESS");

        if (!updated) {
            System.out.println("Ticket not found.");
        }

        // Display updated ticket
        System.out.println("\n===== UPDATED TICKET =====");
        System.out.println(service.getTicketById(101L));

        // Test a ticket that does not exist
        System.out.println("\n===== TEST MISSING TICKET =====");

        Ticket missing = service.getTicketById(999L);

        if (missing == null) {
            System.out.println(
                    "Ticket 999 not found. Handled correctly."
            );
        }

        // Test duplicate ticket ID
        System.out.println("\n===== TEST DUPLICATE ID =====");

        try {
            Ticket duplicate = new Ticket(
                    101L,
                    "Duplicate ticket",
                    "Testing duplicate ID",
                    "Hardware",
                    "LOW",
                    "OPEN",
                    "Employee3",
                    null
            );

            service.createTicket(duplicate);

        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Duplicate rejected: " + e.getMessage()
            );
        }

        // Test ticket deletion
        System.out.println("\n===== TEST DELETE TICKET =====");

        boolean deleted = service.deleteTicket(102L);

        if (!deleted) {
            System.out.println("Ticket not found.");
        }

        // Verify deletion
        System.out.println("\n===== VERIFY DELETION =====");

        if (service.getTicketById(102L) == null) {
            System.out.println("Ticket 102 no longer exists.");
        } else {
            System.out.println("Ticket 102 still exists.");
        }
    }
}