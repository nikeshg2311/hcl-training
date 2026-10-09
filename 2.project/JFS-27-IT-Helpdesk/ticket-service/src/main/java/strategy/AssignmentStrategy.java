
package strategy;

import model.Ticket;
import java.util.List;

public interface AssignmentStrategy {

    String assignTicket(Ticket ticket, List<String> agents);
}
