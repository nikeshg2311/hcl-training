package app;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.PriorityQueue;
import java.util.Comparator;

public class LibraryWaitlistApp {

    // Represents a library request
    static class LibraryRequest {

        String name;
        boolean faculty;
        boolean overdue;

        LibraryRequest(String name, boolean faculty,
                       boolean overdue) {
            this.name = name;
            this.faculty = faculty;
            this.overdue = overdue;
        }

        @Override
        public String toString() {
            return name
                    + " [Faculty=" + faculty
                    + ", Overdue=" + overdue + "]";
        }
    }

    public static void main(String[] args) {

        // 1. ArrayList: Library catalogue
        List<String> catalogue = new ArrayList<>();

        catalogue.add("Java Programming");
        catalogue.add("Python Basics");
        catalogue.add("Data Structures");
        catalogue.add("Java Programming");

        System.out.println("===== LIBRARY CATALOGUE =====");
        System.out.println(catalogue);

        // 2. LinkedHashSet: Remove duplicates
        Set<String> uniqueBooks =
                new LinkedHashSet<>(catalogue);

        System.out.println("\n===== UNIQUE BOOKS =====");
        System.out.println(uniqueBooks);

        // 3. ArrayDeque: Normal waitlist
        Queue<String> waitlist = new ArrayDeque<>();

        waitlist.offer("Arun");
        waitlist.offer("Priya");
        waitlist.offer("Kavin");

        System.out.println("\n===== WAITLIST =====");
        System.out.println(waitlist);

        String served = waitlist.poll();

        System.out.println("Served next: " + served);
        System.out.println("Remaining waitlist: " + waitlist);

        // 4. PriorityQueue: Faculty-first requests
        Comparator<LibraryRequest> requestComparator =
                (a, b) -> {

                    if (a.faculty != b.faculty) {
                        return a.faculty ? -1 : 1;
                    }

                    if (a.overdue != b.overdue) {
                        return a.overdue ? -1 : 1;
                    }

                    return a.name.compareTo(b.name);
                };

        PriorityQueue<LibraryRequest> requests =
                new PriorityQueue<>(requestComparator);

        requests.offer(new LibraryRequest("Arun", false, true));
        requests.offer(new LibraryRequest("Dr. Ravi", true, false));
        requests.offer(new LibraryRequest("Priya", false, false));
        requests.offer(new LibraryRequest("Dr. Meena", true, true));

        System.out.println("\n===== PRIORITY REQUESTS =====");

        while (!requests.isEmpty()) {
            System.out.println(requests.poll());
        }
    }
}