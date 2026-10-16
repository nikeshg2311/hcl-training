# Day 9 – Generics and Resource Management

## Objective

Learn Java generics, bounded type parameters, wildcards, PECS, `Comparable`, try-with-resources, `AutoCloseable`, and CSV file handling.

## Concepts Practiced

- **Generic classes:** `Pair<K, V>` stores values of different types.
- **Bounded generics:** `Box<T extends Number>` accepts numeric types.
- **Wildcards:** `? extends Number` supports reading numeric values.
- **PECS:** Producer Extends, Consumer Super.
- **Comparable:** Employees are sorted by ID.
- **Try-with-resources:** Resources are closed automatically.
- **AutoCloseable:** A custom resource demonstrates automatic cleanup.
- **CSV reading:** Employee records are read from a CSV file.

## Files

- `src/main/java/app/GenericToolkit.java`
- `employees.csv`

## JFS-27 Project Integration

Implemented a reusable generic repository in the Ticket Service module.

- `repository/CrudRepository.java` defines generic CRUD operations.
- `repository/InMemoryRepository.java` stores entities using a `LinkedHashMap`.
- `app/GenericRepositoryApp.java` tests repository operations with `User` and `Ticket`.
- `app/CsvSeedLoader.java` loads user and ticket seed data from CSV resources.
- `app/CsvSeedLoaderApp.java` verifies the loaded records.
- `src/main/resources/users.csv` contains sample user records.
- `src/main/resources/tickets.csv` contains sample ticket records.

## Validation

The Maven multi-module project builds successfully with `mvn clean package`.

The repository test confirms saving, searching, handling missing records, deleting, and reusing one generic implementation for multiple entity types.

The CSV loader successfully loads two users and two tickets.

## Limitations

The repository stores data in memory, so records do not persist after the application exits. The CSV parser assumes simple comma-separated fields without quoted commas.

## Commit

`day-9: generics`
