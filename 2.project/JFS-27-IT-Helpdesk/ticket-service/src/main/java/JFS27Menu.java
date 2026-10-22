
import app.CsvSeedLoader;
import exception.TicketNotFoundException;
import model.Ticket;
import model.User;
import repository.CrudRepository;
import repository.InMemoryRepository;
import service.TicketService;
import strategy.AssignmentStrategy;
import strategy.CategoryAssignmentStrategy;
import strategy.RoundRobinAssignmentStrategy;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

public class JFS27Menu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final TicketService service = new TicketService();
    private static long nextTicketId = 1001L;

    private static final List<String> agents =
            Arrays.asList("Agent1", "Agent2", "Agent3");

    private static AssignmentStrategy assignmentStrategy =
            new RoundRobinAssignmentStrategy();

    public static void main(String[] args) {
        seedData();

        int choice = 0;

        do {
            System.out.println("\n===== JFS-27 IT HELPDESK =====");
            System.out.println("1. Create IT Ticket");
            System.out.println("2. Automatic Ticket Assignment");
            System.out.println("3. Manage Ticket");
            System.out.println("4. Track SLA");
            System.out.println("5. Reassign Ticket");
            System.out.println("6. Link Knowledge Base Article");
            System.out.println("7. Reopen Ticket");
            System.out.println("8. View Agent Metrics");
            System.out.println("9. View All Tickets");
            System.out.println("10. Load CSV Seed Data");
            System.out.println("11. Exit");

            try {
                choice = readInt("Enter your choice: ");

                switch (choice) {
                    case 1: createTicket(); break;
                    case 2: assignTicket(); break;
                    case 3: manageTicket(); break;
                    case 4: trackSla(); break;
                    case 5: reassignTicket(); break;
                    case 6: linkKnowledgeBase(); break;
                    case 7: reopenTicket(); break;
                    case 8: viewAgentMetrics(); break;
                    case 9: viewAllTickets(); break;
                    case 10: loadCsvData(); break;
                    case 11:
                        System.out.println("Exiting JFS-27 Helpdesk...");
                        break;
                    default:
                        System.out.println("Invalid choice. Choose 1–11.");
                }
            } catch (IllegalArgumentException |
                     IllegalStateException |
                     TicketNotFoundException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        } while (choice != 11);

        scanner.close();
    }

    private static void createTicket() {
        String title = readText("Ticket title: ");
        String description = readText("Description: ");
        String category = readText("Category (Hardware/Software): ");
        String priority = readText("Priority (LOW/MEDIUM/HIGH): ")
                .toUpperCase();
        String createdBy = readText("Created by: ");

        Ticket ticket = new Ticket(
                nextTicketId++, title, description, category,
                priority, "OPEN", createdBy, null
        );

        service.createTicket(ticket);
        System.out.println("Created ticket: " + ticket);
    }

    
private static void assignTicket() {
    long id = readLong("Ticket ID: ");
    Ticket ticket = service.getTicketById(id);

    System.out.println("1. Round-robin assignment");
    System.out.println("2. Category-based assignment");
    int type = readInt("Choose strategy: ");

    Map<String, String> categoryMap = new LinkedHashMap<>();
    categoryMap.put("Hardware", "Agent1");
    categoryMap.put("Software", "Agent2");

    Map<Integer, Supplier<AssignmentStrategy>> strategies =
            new LinkedHashMap<>();

    strategies.put(1, RoundRobinAssignmentStrategy::new);
    strategies.put(2, () -> new CategoryAssignmentStrategy(categoryMap));

    Supplier<AssignmentStrategy> selectedStrategy = strategies.get(type);

    if (selectedStrategy == null) {
        System.out.println("Invalid strategy.");
        return;
    }

    assignmentStrategy = selectedStrategy.get();

    String agent = assignmentStrategy.assignTicket(ticket, agents);
    service.reassignTicket(id, agent);
}


    private static void manageTicket() {
        long id = readLong("Ticket ID: ");
        System.out.println("1. View ticket");
        System.out.println("2. Update status, priority and notes");
        int action = readInt("Choose action: ");

        if (action == 1) {
            System.out.println(service.getTicketById(id));
        } else if (action == 2) {
            String status = readText(
                    "Status (OPEN/IN_PROGRESS/RESOLVED/CLOSED): ")
                    .toUpperCase();
            String priority = readText("Priority (LOW/MEDIUM/HIGH): ")
                    .toUpperCase();
            String notes = readText("Notes: ");

            service.updateTicketDetails(id, status, priority, notes);
        } else {
            System.out.println("Invalid action.");
        }
    }

    private static void trackSla() {
        long id = readLong("Ticket ID: ");
        System.out.println("1. Set SLA deadline");
        System.out.println("2. Check existing SLA");
        int action = readInt("Choose action: ");

        if (action == 1) {
            long hours = readLong("Target hours: ");
            service.setSlaDeadline(id, hours);
        } else if (action == 2) {
            System.out.println("Hours remaining: "
                    + service.getSlaHoursRemaining(id));
            System.out.println("SLA breached: "
                    + service.isSlaBreached(id));
        } else {
            System.out.println("Invalid action.");
        }
    }

    private static void reassignTicket() {
        long id = readLong("Ticket ID: ");
        String agent = readText("New agent name: ");
        service.reassignTicket(id, agent);
    }

    private static void linkKnowledgeBase() {
        long id = readLong("Ticket ID: ");
        String article = readText("Article ID or URL: ");
        service.linkKbArticle(id, article);

        System.out.println("Linked articles: "
                + service.getLinkedKbArticles(id));
    }

    private static void reopenTicket() {
        long id = readLong("Closed ticket ID: ");
        service.reopenTicket(id);
    }

    private static void viewAgentMetrics() {
        String agent = readText("Agent name: ");
        service.printAgentMetrics(agent);
    }

    private static void viewAllTickets() {
        List<Ticket> tickets = service.getAllTickets();

        if (tickets.isEmpty()) {
            System.out.println("No tickets available.");
            return;
        }

        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    private static void seedData() {
        service.addSupportGroup("Hardware");
        service.addSupportGroup("Software");

        service.createTicket(new Ticket(
                101L, "Laptop issue", "Laptop not starting",
                "Hardware", "HIGH", "OPEN", "Employee1", "Agent1"
        ));

        service.createTicket(new Ticket(
                102L, "Login issue", "Unable to log in",
                "Software", "MEDIUM", "RESOLVED", "Employee2", "Agent2"
        ));

        nextTicketId = 1001L;
    }

    private static void loadCsvData() {
        CrudRepository<User, Long> users =
                new InMemoryRepository<>(User::getUserId);
        CrudRepository<Ticket, Long> tickets =
                new InMemoryRepository<>(Ticket::getTicketId);

        CsvSeedLoader.loadUsers(users);
        CsvSeedLoader.loadTickets(tickets);

        System.out.println("Users loaded: " + users.findAll().size());
        System.out.println("Tickets loaded: " + tickets.findAll().size());
        System.out.println("CSV data loaded into separate repositories.");
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static long readLong(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
