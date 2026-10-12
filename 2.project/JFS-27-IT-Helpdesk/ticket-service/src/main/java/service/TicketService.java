
package service;

import model.Ticket;
import exception.TicketNotFoundException;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class TicketService {

    private final List<Ticket> tickets = new ArrayList<>();
    private final Set<String> supportGroups = new LinkedHashSet<>();
    private final Queue<Ticket> pendingTickets = new ArrayDeque<>();

    // Create a new ticket
    public void createTicket(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException(
                    "Ticket cannot be null."
            );
        }

        if (ticket.getTicketId() == null) {
            throw new IllegalArgumentException(
                    "Ticket ID cannot be null."
            );
        }

        for (Ticket existing : tickets) {
            if (existing.getTicketId().equals(ticket.getTicketId())) {
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
        ticket.setStatus(newStatus);

        System.out.println(
                "Ticket " + ticketId
                        + " status updated to " + newStatus
        );

        return true;
    }

    // Update ticket status, priority, and notes (FR3)
    public void updateTicketDetails(
            Long ticketId,
            String newStatus,
            String newPriority,
            String newNotes) {

        Ticket ticket = getTicketById(ticketId);

        ticket.setStatus(newStatus);
        ticket.setPriority(newPriority);
        ticket.setNotes(newNotes);

        System.out.println(
                "Ticket " + ticketId + " updated successfully."
        );
    }

    // Delete a ticket using its ID
    public boolean deleteTicket(Long ticketId) {
        Ticket ticket = getTicketById(ticketId);

        pendingTickets.remove(ticket);
        tickets.remove(ticket);

        System.out.println(
                "Ticket " + ticketId + " deleted successfully."
        );

        return true;
    }

    // Register a support group without duplicates
    public void addSupportGroup(String groupName) {
        if (groupName == null || groupName.isBlank()) {
            throw new IllegalArgumentException(
                    "Support group name cannot be empty."
            );
        }

        boolean added = supportGroups.add(groupName);

        if (added) {
            System.out.println("Support group added: " + groupName);
        } else {
            System.out.println(
                    "Support group already exists: " + groupName
            );
        }
    }

    // Return registered support groups in insertion order
    public Set<String> getSupportGroups() {
        return new LinkedHashSet<>(supportGroups);
    }

    // Add a ticket to the pending queue
    public void enqueueTicket(Long ticketId) {
        Ticket ticket = getTicketById(ticketId);

        if (pendingTickets.contains(ticket)) {
            System.out.println(
                    "Ticket " + ticketId
                            + " is already in the pending queue."
            );
            return;
        }

        pendingTickets.offer(ticket);

        System.out.println(
                "Ticket " + ticketId
                        + " added to pending queue."
        );
    }

    // Process the oldest pending ticket
    public Ticket processNextPendingTicket() {
        Ticket ticket = pendingTickets.poll();

        if (ticket == null) {
            System.out.println("No pending tickets to process.");
            return null;
        }

        System.out.println(
                "Processing pending ticket: "
                        + ticket.getTicketId()
        );

        return ticket;
    }
}
