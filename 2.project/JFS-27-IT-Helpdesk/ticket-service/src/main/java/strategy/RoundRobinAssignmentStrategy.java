
package strategy;

import model.Ticket;
import java.util.List;

public class RoundRobinAssignmentStrategy implements AssignmentStrategy {

    private int currentIndex = 0;

    @Override
    public String assignTicket(Ticket ticket, List<String> agents) {

        if (agents == null || agents.isEmpty()) {
            throw new IllegalArgumentException(
                    "Agent list cannot be empty."
            );
        }

        String assignedAgent = agents.get(currentIndex);

        currentIndex = (currentIndex + 1) % agents.size();

        System.out.println(
                "Ticket " + ticket.getTicketId()
                        + " assigned to " + assignedAgent
        );

        return assignedAgent;
    }
}
