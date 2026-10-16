package app;

import model.Ticket;
import model.User;
import repository.CrudRepository;
import repository.InMemoryRepository;

public class GenericRepositoryApp {

    public static void main(String[] args) {

        // ==================================
        // 1. Create a generic repository for Users
        // ==================================

        CrudRepository<User, Long> userRepository =
                new InMemoryRepository<>(User::getUserId);

        User user1 = new User(
                1L,
                "Arun",
                "arun@example.com",
                "EMPLOYEE",
                "IT"
        );

        User user2 = new User(
                2L,
                "Meena",
                "meena@example.com",
                "AGENT",
                "Hardware"
        );

        userRepository.save(user1);
        userRepository.save(user2);

        System.out.println("===== USER REPOSITORY =====");

        System.out.println("All users:");

        for (User user : userRepository.findAll()) {
            System.out.println(user);
        }

        System.out.println("\nFind user with ID 1:");

        userRepository.findById(1L).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("User not found")
        );

        System.out.println("\nFind user with ID 99:");

        userRepository.findById(99L).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("User not found")
        );

        // ==================================
        // 2. Create a generic repository for Tickets
        // ==================================

        CrudRepository<Ticket, Long> ticketRepository =
                new InMemoryRepository<>(Ticket::getTicketId);

        Ticket ticket1 = new Ticket(
        101L,
        "Laptop issue",
        "Laptop is not starting",
        "Hardware",
        "HIGH",
        "OPEN",
        "Arun",
        "Agent1"
);

        Ticket ticket2 = new Ticket(
        102L,
        "Login issue",
        "Unable to log in",
        "Software",
        "MEDIUM",
        "OPEN",
        "Meena",
        "Agent2"
);

        ticketRepository.save(ticket1);
        ticketRepository.save(ticket2);

        System.out.println("\n===== TICKET REPOSITORY =====");

        for (Ticket ticket : ticketRepository.findAll()) {
            System.out.println(ticket);
        }

        System.out.println("\nFind ticket with ID 101:");

        ticketRepository.findById(101L).ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Ticket not found")
        );

        // ==================================
        // 3. Test deletion
        // ==================================

        System.out.println("\n===== DELETE TEST =====");

        boolean deleted = userRepository.deleteById(2L);

        System.out.println("User 2 deleted: " + deleted);
        System.out.println(
                "Remaining users: " + userRepository.findAll().size()
        );

        // ==================================
        // 4. Demonstrate repository reuse
        // ==================================

        System.out.println("\n===== GENERIC REPOSITORY TEST =====");

        System.out.println(
                "User repository type: " +
                userRepository.getClass().getSimpleName()
        );

        System.out.println(
                "Ticket repository type: " +
                ticketRepository.getClass().getSimpleName()
        );

        System.out.println(
                "One repository implementation supports both entity types!"
        );
    }
}