
## JFS-27 Memory Management Review

### Existing Practices
- TicketService uses LinkedHashMap to store tickets by ID.
- deleteTicket removes ticket data from the ticket map, pending queue,
  SLA deadline map, and linked knowledge-base article map.
- getAllTickets returns a new ArrayList containing references to
  existing Ticket objects.
- Sorted ticket reports create temporary lists before sorting.
- CsvSeedLoader uses try-with-resources to close CSV readers.

### Potential Memory Risks
- In-memory collections can grow without explicit size limits.
- Retained ticket references can keep ticket objects reachable.
- Callers may retain lists returned by getAllTickets or report methods.
- Future ticket-related collections must be cleaned up during deletion.

### Stack and Heap Diagram
The getAllTickets method creates a new ArrayList on the heap.
The list stores references to Ticket objects already stored in the
TicketService map. The list does not clone those Ticket objects.

### Limitations
- Ticket storage is currently in memory.
- Production persistence and pagination are not yet integrated.
- The review identifies potential risks; it does not establish
  that a memory leak is currently occurring.
