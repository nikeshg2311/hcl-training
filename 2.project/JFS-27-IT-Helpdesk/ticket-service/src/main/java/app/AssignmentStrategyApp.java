
package app;

import model.Ticket;
import strategy.AssignmentStrategy;
import strategy.RoundRobinAssignmentStrategy;
import strategy.CategoryAssignmentStrategy;

import java.util.List;
import java.util.Map;

public class AssignmentStrategyApp {

    public static void main(String[] args) {

        List<String> agents = List.of("Asha", "Ravi", "Priya");

        Ticket ticket1 = new Ticket(
                101L, "Laptop issue", "Laptop won't start",
                "Hardware", "HIGH", "OPEN", "Employee1", null
        );

        Ticket ticket2 = new Ticket(
                102L, "Login issue", "Cannot log in",
                "Software", "MEDIUM", "OPEN", "Employee2", null
        );

        System.out.println("===== ROUND-ROBIN ASSIGNMENT =====");

        AssignmentStrategy roundRobin =
                new RoundRobinAssignmentStrategy();

        roundRobin.assignTicket(ticket1, agents);
        roundRobin.assignTicket(ticket2, agents);

        System.out.println("\n===== CATEGORY-BASED ASSIGNMENT =====");

        AssignmentStrategy categoryBased =
                new CategoryAssignmentStrategy(
                        Map.of(
                                "Hardware", "Asha",
                                "Software", "Ravi",
                                "Network", "Priya"
                        )
                );

        categoryBased.assignTicket(ticket1, agents);
        categoryBased.assignTicket(ticket2, agents);
    }
}
