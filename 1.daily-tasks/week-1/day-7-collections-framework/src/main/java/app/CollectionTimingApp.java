
package app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CollectionTimingApp {

    public static void main(String[] args) {

        int n = 100000;

        // Warm up the JVM before measuring
        for (int run = 0; run < 3; run++) {
            List<Integer> warmup = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                warmup.add(i);
            }
        }

        // ArrayList insertion
        List<Integer> arrayList = new ArrayList<>();

        long start = System.nanoTime();

        for (int i = 0; i < n; i++) {
            arrayList.add(i);
        }

        long end = System.nanoTime();

        double arrayListTime = (end - start) / 1_000_000.0;

        // LinkedList insertion
        List<Integer> linkedList = new LinkedList<>();

        start = System.nanoTime();

        for (int i = 0; i < n; i++) {
            linkedList.add(i);
        }

        end = System.nanoTime();

        double linkedListTime = (end - start) / 1_000_000.0;

        System.out.println("===== COLLECTION TIMING =====");
        System.out.println("Number of elements: " + n);

        System.out.printf(
                "ArrayList append time: %.3f ms%n",
                arrayListTime
        );

        System.out.printf(
                "LinkedList append time: %.3f ms%n",
                linkedListTime
        );
    }
}
