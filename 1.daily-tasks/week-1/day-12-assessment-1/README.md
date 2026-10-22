# Day 12 — Assessment 1: Core Java Checkpoint

## Objective
Integrate the Core Java concepts learned during Days 1–11 into a console-based JFS-27 IT Helpdesk application.

## Implemented Features
- Console-based menu navigation
- Ticket creation and listing
- Round-robin and category-based ticket assignment
- Ticket status, priority, and notes management
- SLA deadline creation and tracking
- Ticket reassignment
- Knowledge Base article linking
- Ticket reopening within the three-day window
- Agent performance metrics
- CSV seed-data loading into in-memory repositories
- Input validation for numeric menu inputs

## Testing Performed
- Verified agent metrics for Agent2
- Verified ticket listing
- Created ticket ID 1001
- Set a 24-hour SLA deadline
- Checked SLA status and remaining time
- Closed and reopened ticket 101 within the allowed window

## Build and Run

From the JFS-27 project root:

```powershell
mvn clean package
java -cp ticket-service\target\classes JFS27Menu
```

## Limitations
- Ticket data is stored in memory and resets when the application restarts.
- CSV records are loaded into separate repositories and are not imported into the menu's TicketService.
- Authentication and role-based authorization are not implemented in this console prototype.

## Evidence
Screenshots of the successful build and core application flows are stored in the `evidence` folder.
