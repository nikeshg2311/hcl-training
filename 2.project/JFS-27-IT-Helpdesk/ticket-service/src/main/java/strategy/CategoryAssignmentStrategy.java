
package strategy;

import model.Ticket;
import java.util.List;
import java.util.Map;

public class CategoryAssignmentStrategy implements AssignmentStrategy {

    private final Map<String, String> categoryAgentMap;

    public CategoryAssignmentStrategy(
            Map<String, String> categoryAgentMap) {
        this.categoryAgentMap = categoryAgentMap;
    }

    @Override
    public String assignTicket(Ticket ticket, List<String> agents) {

        if (ticket == null) {
            throw new IllegalArgumentException(
                    "Ticket cannot be null."
            );
        }

        if (agents == null || agents.isEmpty()) {
            throw new IllegalArgumentException(
                    "Agent list cannot be empty."
            );
        }

        String agent = categoryAgentMap.get(ticket.getCategory());

        if (agent != null && agents.contains(agent)) {
            System.out.println(
                    "Ticket " + ticket.getTicketId()
                            + " assigned to " + agent
                            + " based on category "
                            + ticket.getCategory()
            );

            return agent;
        }

        System.out.println(
                "No category-specific agent found. "
                        + "Using the first available agent: "
                        + agents.get(0)
        );

        return agents.get(0);
    }
}
