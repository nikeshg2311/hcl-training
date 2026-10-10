package service;

import model.Ticket;
import java.util.ArrayList;
import java.util.List;
import exception.TicketNotFoundException;

public class TicketService {

    private final List<Ticket> tickets = new ArrayList<>();

    // Create a new ticket
    public void createTicket(Ticket ticket) {

        if (ticket == null) {
            throw new IllegalArgumentException(
                    "Ticket cannot be null."
            );
        }

        for (Ticket existing : tickets) {
            if (existing.getTicketId()
                    .equals(ticket.getTicketId())) {
                throw new IllegalArgumentException(
                        "Ticket ID already exists."
                );
            }
        }

        tickets.add(ticket);

        System.out.println(
                "Ticket created successfully: "
                        + ticket.getTicketId()
        );
    }

    // View all tickets
    public List<Ticket> getAllTickets() {
        return new ArrayList<>(tickets);
    }

    // Find a ticket using its ID
public Ticket getTicketById(Long ticketId) {

    for (Ticket ticket : tickets) {
        if (ticket.getTicketId().equals(ticketId)) {
            return ticket;
        }
    }

    throw new TicketNotFoundException(ticketId);
}

    // Update a ticket's status
    public boolean updateTicketStatus(
            Long ticketId, String newStatus) {

        Ticket ticket = getTicketById(ticketId);

        if (ticket == null) {
            return false;
        }

        ticket.setStatus(newStatus);

        System.out.println(
                "Ticket " + ticketId
                        + " status updated to "
                        + newStatus
        );

        return true;
    }
    // Delete a ticket using its ID
public boolean deleteTicket(Long ticketId) {

    Ticket ticket = getTicketById(ticketId);

    if (ticket == null) {
        return false;
    }

    tickets.remove(ticket);

    System.out.println(
            "Ticket " + ticketId + " deleted successfully."
    );

    return true;
}
}