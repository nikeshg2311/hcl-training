package app;

import model.Ticket;
import model.User;
import repository.CrudRepository;
import repository.InMemoryRepository;

public class CsvSeedLoaderApp {

    public static void main(String[] args) {

        CrudRepository<User, Long> userRepository =
                new InMemoryRepository<>(User::getUserId);

        CrudRepository<Ticket, Long> ticketRepository =
                new InMemoryRepository<>(Ticket::getTicketId);

        CsvSeedLoader.loadUsers(userRepository);
        CsvSeedLoader.loadTickets(ticketRepository);

        System.out.println("===== USERS LOADED FROM CSV =====");

        for (User user : userRepository.findAll()) {
            System.out.println(user);
        }

        System.out.println("\n===== TICKETS LOADED FROM CSV =====");

        for (Ticket ticket : ticketRepository.findAll()) {
            System.out.println(ticket);
        }

        System.out.println("\n===== SEED DATA SUMMARY =====");
        System.out.println(
                "Users loaded: " + userRepository.findAll().size());
        System.out.println(
                "Tickets loaded: " + ticketRepository.findAll().size());
    }
}
