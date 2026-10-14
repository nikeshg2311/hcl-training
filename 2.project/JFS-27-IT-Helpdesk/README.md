## JFS-27 Project Integration

### Map-Based Ticket Storage
Updated `TicketService` to store tickets in a `LinkedHashMap`, using ticket IDs as keys. This supports direct ticket lookup while preserving insertion order.

### FR4 – SLA Tracking
Implemented methods to set an SLA deadline, check whether a deadline has been breached, and calculate the remaining hours. The current prototype uses elapsed hours and does not yet exclude non-working hours.

### FR5 – Ticket Reassignment
Added functionality to reassign a ticket to another agent. Team Lead authorization still needs to be integrated.

### FR6 – Knowledge Base Linking
Added functionality to link multiple Knowledge Base article IDs or URLs to a ticket while preventing duplicate links.

### Sorted Reports
Implemented reports that sort tickets by ID and by priority, using Comparators.

### Verification
The Maven build succeeded, and `TicketServiceApp` executed successfully. Existing ticket operations and the new Day 8 features were tested using sample tickets.