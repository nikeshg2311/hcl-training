\# JFS-27 - IT Helpdesk \& Service Desk Ticketing



\## Project Overview



JFS-27 is an IT Helpdesk and Service Desk Ticketing application.



The system allows employees to raise IT support tickets and enables

support agents and team leads to manage, assign, resolve, and monitor

those tickets.



\## Domain



IT Operations



\## User Roles



\- Employee

\- Agent

\- Team Lead

\- Admin



\## Key Features



\- Ticket creation

\- Automatic ticket assignment

\- SLA tracking

\- SLA breach detection

\- Ticket reassignment

\- Knowledge Base integration

\- Ticket reopening

\- CSAT survey

\- Agent performance dashboard



\## Planned Technology Stack



\- Java

\- Spring Boot

\- Spring Security

\- React

\- PostgreSQL

\- REST APIs

\- Microservices

\- Git and GitHub



\## Project Architecture



The project will eventually be divided into functional services such as:



\- Authentication/User Service

\- Ticket Service

\- Assignment Service

\- Knowledge Base Service

\- SLA Service

\- Reporting Service



\## Current Status



Requirements analysis and initial project setup.


## OOP Design and Ticket Assignment

### BaseEntity
A shared abstract class containing creation and update timestamps.

### Inheritance
`Ticket`, `User`, and `SupportGroup` extend `BaseEntity` to reuse common timestamp functionality.

### Strategy Pattern
The ticket service supports two assignment strategies:

- `RoundRobinAssignmentStrategy` — assigns tickets to agents in rotation.
- `CategoryAssignmentStrategy` — selects an agent based on the ticket category.

Both implement the `AssignmentStrategy` interface, allowing assignment behavior to be selected through a common contract.

### Verification
- `mvn clean package` — BUILD SUCCESS
- Round-robin assignment demonstrated with multiple agents.
- Category-based assignment demonstrated for Hardware and Software tickets.

