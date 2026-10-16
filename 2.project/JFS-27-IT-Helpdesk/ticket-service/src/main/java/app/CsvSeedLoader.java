package app;

import model.Ticket;
import model.User;
import repository.CrudRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CsvSeedLoader {

    public static void loadUsers(
            CrudRepository<User, Long> repository) {

        try (BufferedReader reader =
                     openCsv("users.csv")) {

            String line;
            boolean headerSkipped = false;

            while ((line = reader.readLine()) != null) {

                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                if (line.isBlank()) {
                    continue;
                }

                String[] data = line.split(",", -1);

                if (data.length != 5) {
                    throw new IllegalArgumentException(
                            "Invalid user CSV row: " + line);
                }

                User user = new User(
                        Long.parseLong(data[0].trim()),
                        data[1].trim(),
                        data[2].trim(),
                        data[3].trim(),
                        data[4].trim()
                );

                repository.save(user);
            }

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Unable to load users.csv", e);
        }
    }

    public static void loadTickets(
            CrudRepository<Ticket, Long> repository) {

        try (BufferedReader reader =
                     openCsv("tickets.csv")) {

            String line;
            boolean headerSkipped = false;

            while ((line = reader.readLine()) != null) {

                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                if (line.isBlank()) {
                    continue;
                }

                String[] data = line.split(",", -1);

                if (data.length != 8) {
                    throw new IllegalArgumentException(
                            "Invalid ticket CSV row: " + line);
                }

                Ticket ticket = new Ticket(
                        Long.parseLong(data[0].trim()),
                        data[1].trim(),
                        data[2].trim(),
                        data[3].trim(),
                        data[4].trim(),
                        data[5].trim(),
                        data[6].trim(),
                        data[7].trim()
                );

                repository.save(ticket);
            }

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Unable to load tickets.csv", e);
        }
    }

    private static BufferedReader openCsv(String fileName) {

        InputStream input = CsvSeedLoader.class
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (input == null) {
            throw new IllegalStateException(
                    "CSV resource not found: " + fileName);
        }

        return new BufferedReader(
                new InputStreamReader(
                        input, StandardCharsets.UTF_8));
    }
}