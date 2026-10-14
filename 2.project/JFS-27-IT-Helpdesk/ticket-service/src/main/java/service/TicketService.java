
package service;

import model.Ticket;
import exception.TicketNotFoundException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class TicketService {

    // Ticket ID is the key, making ticket lookup direct.
    private final Map<Long, Ticket> tickets = new LinkedHashMap<>();

    private final Set<String> supportGroups = new LinkedHashSet<>();
    private final Queue<Ticket> pendingTickets = new ArrayDeque<>();

    // FR4: Store each ticket's SLA deadline.
    private final Map<Long, LocalDateTime> slaDeadlines =
            new LinkedHashMap<>();

    // FR6: Store KB article links associated with tickets.
    private final Map<Long, Set<String>> ticketKbArticles =
            new LinkedHashMap<>();

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

        if (tickets.containsKey(ticket.getTicketId())) {
            throw new IllegalArgumentException(
                    "Ticket ID already exists."
            );
        }

        tickets.put(ticket.getTicketId(), ticket);

        System.out.println(
                "Ticket created successfully: "
                        + ticket.getTicketId()
        );
    }

    // View all tickets in insertion order
    public List<Ticket> getAllTickets() {
        return new ArrayList<>(tickets.values());
    }

    // Find a ticket by ID using Map lookup
    public Ticket getTicketById(Long ticketId) {
        Ticket ticket = tickets.get(ticketId);

        if (ticket == null) {
            throw new TicketNotFoundException(ticketId);
        }

        return ticket;
    }

    // Update ticket status
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

    // Delete ticket and associated in-memory data
    public boolean deleteTicket(Long ticketId) {
        Ticket ticket = getTicketById(ticketId);

        pendingTickets.remove(ticket);
        tickets.remove(ticketId);
        slaDeadlines.remove(ticketId);
        ticketKbArticles.remove(ticketId);

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

    // Return support groups in insertion order
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

    // FR4: Set an SLA deadline for a ticket.
    // Example: response/resolution target expressed as hours.
    public void setSlaDeadline(Long ticketId, long targetHours) {
        if (targetHours <= 0) {
            throw new IllegalArgumentException(
                    "SLA target hours must be greater than zero."
            );
        }

        getTicketById(ticketId);

        LocalDateTime deadline =
                LocalDateTime.now().plusHours(targetHours);

        slaDeadlines.put(ticketId, deadline);

        System.out.println(
                "SLA deadline set for ticket " + ticketId
                        + ": " + deadline
        );
    }

    // FR4: Check whether a ticket has an SLA deadline.
    public boolean isSlaBreached(Long ticketId) {
        getTicketById(ticketId);

        LocalDateTime deadline = slaDeadlines.get(ticketId);

        if (deadline == null) {
            throw new IllegalStateException(
                    "SLA deadline has not been set for ticket "
                            + ticketId
            );
        }

        boolean breached = LocalDateTime.now().isAfter(deadline);

        System.out.println(
                "Ticket " + ticketId
                        + " SLA breached: " + breached
        );

        return breached;
    }

    // FR4: Return remaining time until the SLA deadline.
    public long getSlaHoursRemaining(Long ticketId) {
        getTicketById(ticketId);

        LocalDateTime deadline = slaDeadlines.get(ticketId);

        if (deadline == null) {
            throw new IllegalStateException(
                    "SLA deadline has not been set for ticket "
                            + ticketId
            );
        }

        return Duration.between(
                LocalDateTime.now(), deadline
        ).toHours();
    }

    // FR5: Reassign a ticket to another agent.
    // This is a prototype; caller permissions must be enforced separately.
    public void reassignTicket(
            Long ticketId,
            String newAgent) {

        if (newAgent == null || newAgent.isBlank()) {
            throw new IllegalArgumentException(
                    "Agent name cannot be empty."
            );
        }

        Ticket ticket = getTicketById(ticketId);
        ticket.setAssignedTo(newAgent);

        System.out.println(
                "Ticket " + ticketId
                        + " reassigned to " + newAgent
        );
    }

    // FR6: Link a KB article to a ticket.
    // Store the article ID or URL as a string.
    public void linkKbArticle(
            Long ticketId,
            String articleIdOrUrl) {

        if (articleIdOrUrl == null || articleIdOrUrl.isBlank()) {
            throw new IllegalArgumentException(
                    "Knowledge Base article ID or URL cannot be empty."
            );
        }

        getTicketById(ticketId);

        Set<String> articles = ticketKbArticles.computeIfAbsent(
                ticketId, id -> new LinkedHashSet<>()
        );

        boolean added = articles.add(articleIdOrUrl);

        if (added) {
            System.out.println(
                    "KB article linked to ticket " + ticketId
                            + ": " + articleIdOrUrl
            );
        } else {
            System.out.println(
                    "KB article is already linked to ticket "
                            + ticketId
            );
        }
    }

    // FR6: View linked KB articles
    public Set<String> getLinkedKbArticles(Long ticketId) {
        getTicketById(ticketId);

        return new LinkedHashSet<>(
                ticketKbArticles.getOrDefault(
                        ticketId, new LinkedHashSet<>()
                )
        );
    }

    // Day 8: Sorted ticket report by ID
    public List<Ticket> getTicketsSortedById() {
        List<Ticket> sortedTickets =
                new ArrayList<>(tickets.values());

        sortedTickets.sort(
                Comparator.comparing(Ticket::getTicketId)
        );

        return sortedTickets;
    }

    // Day 8: Sorted report by priority, then ticket ID.
    // Assumes priority labels HIGH, MEDIUM, LOW.
    public List<Ticket> getTicketsSortedByPriority() {
        List<Ticket> sortedTickets =
                new ArrayList<>(tickets.values());

        Comparator<Ticket> priorityComparator =
                Comparator.comparingInt(
                        (Ticket ticket) ->
                                getPriorityRank(ticket.getPriority())
                ).reversed()
                 .thenComparing(Ticket::getTicketId);

        sortedTickets.sort(priorityComparator);

        return sortedTickets;
    }

    private int getPriorityRank(String priority) {
        if (priority == null) {
            return 0;
        }

        switch (priority.toUpperCase()) {
            case "HIGH":
                return 3;
            case "MEDIUM":
                return 2;
            case "LOW":
                return 1;
            default:
                return 0;
        }
    }
}
