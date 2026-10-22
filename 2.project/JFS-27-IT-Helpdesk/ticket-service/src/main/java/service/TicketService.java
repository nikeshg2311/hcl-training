
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

    private final Map<Long, Ticket> tickets = new LinkedHashMap<>();
    private final Set<String> supportGroups = new LinkedHashSet<>();
    private final Queue<Ticket> pendingTickets = new ArrayDeque<>();

    // FR4: SLA deadlines
    private final Map<Long, LocalDateTime> slaDeadlines =
            new LinkedHashMap<>();

    // FR6: Knowledge Base article links
    private final Map<Long, Set<String>> ticketKbArticles =
            new LinkedHashMap<>();

    // Create a ticket
    public void createTicket(Ticket ticket) {
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket cannot be null.");
        }

        if (ticket.getTicketId() == null) {
            throw new IllegalArgumentException("Ticket ID cannot be null.");
        }

        if (tickets.containsKey(ticket.getTicketId())) {
            throw new IllegalArgumentException("Ticket ID already exists.");
        }

        tickets.put(ticket.getTicketId(), ticket);

        System.out.println("Ticket created successfully: "
                + ticket.getTicketId());
    }

    // View all tickets
    public List<Ticket> getAllTickets() {
        return new ArrayList<>(tickets.values());
    }

    // Find a ticket by ID
    public Ticket getTicketById(Long ticketId) {
        Ticket ticket = tickets.get(ticketId);

        if (ticket == null) {
            throw new TicketNotFoundException(ticketId);
        }

        return ticket;
    }

    // Update status
    public boolean updateTicketStatus(Long ticketId, String newStatus) {
        Ticket ticket = getTicketById(ticketId);
        ticket.setStatus(newStatus);

        // Keep closure time consistent when status changes.
        if ("CLOSED".equalsIgnoreCase(newStatus)) {
            ticket.setClosedAt(LocalDateTime.now());
        } else if (ticket.getClosedAt() != null) {
            ticket.setClosedAt(null);
        }

        System.out.println("Ticket " + ticketId
                + " status updated to " + newStatus);

        return true;
    }

    // FR3: Update status, priority, and notes
    public void updateTicketDetails(Long ticketId,
                                    String newStatus,
                                    String newPriority,
                                    String newNotes) {
        updateTicketStatus(ticketId, newStatus);

        Ticket ticket = getTicketById(ticketId);
        ticket.setPriority(newPriority);
        ticket.setNotes(newNotes);

        System.out.println("Ticket " + ticketId + " updated successfully.");
    }

    // Delete ticket and associated in-memory data
    public boolean deleteTicket(Long ticketId) {
        Ticket ticket = getTicketById(ticketId);

        pendingTickets.remove(ticket);
        tickets.remove(ticketId);
        slaDeadlines.remove(ticketId);
        ticketKbArticles.remove(ticketId);

        System.out.println("Ticket " + ticketId + " deleted successfully.");
        return true;
    }

    // Register a support group
    public void addSupportGroup(String groupName) {
        if (groupName == null || groupName.isBlank()) {
            throw new IllegalArgumentException(
                    "Support group name cannot be empty.");
        }

        if (supportGroups.add(groupName)) {
            System.out.println("Support group added: " + groupName);
        } else {
            System.out.println("Support group already exists: " + groupName);
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
            System.out.println("Ticket " + ticketId
                    + " is already in the pending queue.");
            return;
        }

        pendingTickets.offer(ticket);
        System.out.println("Ticket " + ticketId + " added to pending queue.");
    }

    // Process the oldest pending ticket
    public Ticket processNextPendingTicket() {
        Ticket ticket = pendingTickets.poll();

        if (ticket == null) {
            System.out.println("No pending tickets to process.");
            return null;
        }

        System.out.println("Processing pending ticket: "
                + ticket.getTicketId());
        return ticket;
    }

    // FR4: Set an SLA deadline
    public void setSlaDeadline(Long ticketId, long targetHours) {
        if (targetHours <= 0) {
            throw new IllegalArgumentException(
                    "SLA target hours must be greater than zero.");
        }

        getTicketById(ticketId);

        LocalDateTime deadline = LocalDateTime.now().plusHours(targetHours);
        slaDeadlines.put(ticketId, deadline);

        System.out.println("SLA deadline set for ticket " + ticketId
                + ": " + deadline);
    }

    // FR4: Check SLA breach
    public boolean isSlaBreached(Long ticketId) {
        getTicketById(ticketId);

        LocalDateTime deadline = slaDeadlines.get(ticketId);

        if (deadline == null) {
            throw new IllegalStateException(
                    "SLA deadline has not been set for ticket " + ticketId);
        }

        return LocalDateTime.now().isAfter(deadline);
    }

    // FR4: Get remaining SLA hours
    public long getSlaHoursRemaining(Long ticketId) {
        getTicketById(ticketId);

        LocalDateTime deadline = slaDeadlines.get(ticketId);

        if (deadline == null) {
            throw new IllegalStateException(
                    "SLA deadline has not been set for ticket " + ticketId);
        }

        return Duration.between(LocalDateTime.now(), deadline).toHours();
    }

    // FR5: Reassign a ticket
    public void reassignTicket(Long ticketId, String newAgent) {
        if (newAgent == null || newAgent.isBlank()) {
            throw new IllegalArgumentException(
                    "Agent name cannot be empty.");
        }

        Ticket ticket = getTicketById(ticketId);
        ticket.setAssignedTo(newAgent);

        System.out.println("Ticket " + ticketId
                + " reassigned to " + newAgent);
    }

    // FR6: Link a Knowledge Base article
    public void linkKbArticle(Long ticketId, String articleIdOrUrl) {
        if (articleIdOrUrl == null || articleIdOrUrl.isBlank()) {
            throw new IllegalArgumentException(
                    "Knowledge Base article ID or URL cannot be empty.");
        }

        getTicketById(ticketId);

        Set<String> articles = ticketKbArticles.computeIfAbsent(
                ticketId, id -> new LinkedHashSet<>());

        if (articles.add(articleIdOrUrl)) {
            System.out.println("KB article linked to ticket " + ticketId
                    + ": " + articleIdOrUrl);
        } else {
            System.out.println("KB article is already linked to ticket "
                    + ticketId);
        }
    }

    // FR6: View linked articles
    public Set<String> getLinkedKbArticles(Long ticketId) {
        getTicketById(ticketId);

        return new LinkedHashSet<>(
                ticketKbArticles.getOrDefault(
                        ticketId, new LinkedHashSet<>()));
    }

    // Day 8: Sort tickets by ID
    public List<Ticket> getTicketsSortedById() {
        List<Ticket> sortedTickets = new ArrayList<>(tickets.values());

        sortedTickets.sort(Comparator.comparing(Ticket::getTicketId));
        return sortedTickets;
    }

    // Day 8: Sort by priority, then ticket ID
    public List<Ticket> getTicketsSortedByPriority() {
        List<Ticket> sortedTickets = new ArrayList<>(tickets.values());

        Comparator<Ticket> priorityComparator =
                Comparator.comparingInt(
                        (Ticket ticket) -> getPriorityRank(ticket.getPriority()))
                        .reversed()
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

    // =========================================================
    // FR7: Close a ticket and record its closure time
    // =========================================================
    public void closeTicket(Long ticketId) {
        Ticket ticket = getTicketById(ticketId);

        if ("CLOSED".equalsIgnoreCase(ticket.getStatus())) {
            throw new IllegalStateException("Ticket is already closed.");
        }

        ticket.setStatus("CLOSED");
        ticket.setClosedAt(LocalDateTime.now());

        System.out.println("Ticket " + ticketId + " closed successfully.");
    }

    // FR7: Reopen a ticket within 72 hours of closure
    public void reopenTicket(Long ticketId) {
        Ticket ticket = getTicketById(ticketId);

        if (!"CLOSED".equalsIgnoreCase(ticket.getStatus())) {
            throw new IllegalStateException(
                    "Only closed tickets can be reopened.");
        }

        LocalDateTime closedAt = ticket.getClosedAt();

        if (closedAt == null) {
            throw new IllegalStateException(
                    "Ticket closure time is not available.");
        }

        LocalDateTime deadline = closedAt.plusDays(3);

        if (LocalDateTime.now().isAfter(deadline)) {
            throw new IllegalStateException(
                    "The three-day reopening window has expired.");
        }

        ticket.setStatus("OPEN");
        ticket.setClosedAt(null);

        System.out.println("Ticket " + ticketId + " reopened successfully.");
    }

    // =========================================================
    // FR8: Count resolved or closed tickets for an agent
    // =========================================================
    public long getResolvedTicketCount(String agentName) {
        validateAgentName(agentName);

        return tickets.values().stream()
                .filter(ticket -> agentName.equalsIgnoreCase(
                        ticket.getAssignedTo()))
                .filter(ticket ->
                        "RESOLVED".equalsIgnoreCase(ticket.getStatus())
                        || "CLOSED".equalsIgnoreCase(ticket.getStatus()))
                .count();
    }

    // FR8: Count assigned tickets whose SLA deadlines have passed
    public long getSlaBreachCount(String agentName) {
        validateAgentName(agentName);

        LocalDateTime now = LocalDateTime.now();

        return tickets.values().stream()
                .filter(ticket -> agentName.equalsIgnoreCase(
                        ticket.getAssignedTo()))
                .filter(ticket -> {
                    LocalDateTime deadline =
                            slaDeadlines.get(ticket.getTicketId());

                    return deadline != null && now.isAfter(deadline);
                })
                .count();
    }

    // FR8: Print a simple agent performance report
    public void printAgentMetrics(String agentName) {
        validateAgentName(agentName);

        System.out.println("===== Agent Performance =====");
        System.out.println("Agent: " + agentName);
        System.out.println("Resolved tickets: "
                + getResolvedTicketCount(agentName));
        System.out.println("SLA breaches: "
                + getSlaBreachCount(agentName));
    }

    private void validateAgentName(String agentName) {
        if (agentName == null || agentName.isBlank()) {
            throw new IllegalArgumentException(
                    "Agent name cannot be empty.");
        }
    }
}
