package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Generic class: Pair<K, V>
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Pair{key=" + key + ", value=" + value + "}";
    }
}

// Bounded generic: T must extend Number
class Box<T extends Number> {
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public double doubleValue() {
        return value.doubleValue();
    }
}

// Comparable: sort employees by ID
class Employee implements Comparable<Employee> {
    private int id;
    private String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}

// Custom resource that implements AutoCloseable
class HelpdeskResource implements AutoCloseable {

    public HelpdeskResource() {
        System.out.println("Helpdesk resource opened.");
    }

    public void performTask() {
        System.out.println("Processing helpdesk task...");
    }

    @Override
    public void close() {
        System.out.println("Helpdesk resource closed automatically.");
    }
}

public class GenericToolkit {

    // ? extends Number: can read numbers from different numeric lists
    public static double calculateTotal(List<? extends Number> numbers) {
        double total = 0;

        for (Number number : numbers) {
            total += number.doubleValue();
        }

        return total;
    }

    // ? super Integer: can add integers to a list of Integer or its supertypes
    public static void addIntegers(List<? super Integer> numbers) {
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
    }

    // Read a CSV file using try-with-resources
    public static void readCsv(String fileName) {
        System.out.println("\n===== CSV FILE READING =====");

        Path path = Path.of(fileName);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",", -1);

                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].trim();
                }

                System.out.println(String.join(" | ", values));
            }
        } catch (IOException e) {
            System.out.println("Unable to read CSV: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        System.out.println("===== GENERIC PAIR =====");

        Pair<Integer, String> employeePair =
                new Pair<>(101, "Arun");

        System.out.println("Employee ID: " + employeePair.getKey());
        System.out.println("Employee Name: " + employeePair.getValue());

        Pair<String, Double> productPair =
                new Pair<>("Laptop", 55000.0);

        System.out.println(productPair);

        System.out.println("\n===== BOUNDED GENERIC BOX =====");

        Box<Integer> integerBox = new Box<>(100);
        Box<Double> doubleBox = new Box<>(99.5);

        System.out.println("Integer value: " + integerBox.getValue());
        System.out.println("Double value: " + doubleBox.getValue());
        System.out.println("Double conversion: " + doubleBox.doubleValue());

        System.out.println("\n===== WILDCARDS AND PECS =====");

        List<Integer> numbers = List.of(10, 20, 30);
        System.out.println("Total: " + calculateTotal(numbers));

        List<Number> numberList = new ArrayList<>();
        addIntegers(numberList);
        System.out.println("Added integers: " + numberList);

        System.out.println("\n===== COMPARABLE SORTING =====");

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(103, "Kavin"));
        employees.add(new Employee(101, "Arun"));
        employees.add(new Employee(102, "Meena"));

        Collections.sort(employees);

        for (Employee employee : employees) {
            System.out.println(employee);
        }

        System.out.println("\n===== TRY-WITH-RESOURCES =====");

        try (HelpdeskResource resource = new HelpdeskResource()) {
            resource.performTask();
        }

        // Read the CSV file from the current project directory
        readCsv("employees.csv");
    }
}