
package app;

import java.util.ArrayList;
import java.util.List;

public class MemoryLab {

    // Experiment 1: Stack overflow
    private static void recursiveMethod() {
        recursiveMethod();
    }

    private static void testStack() {
        System.out.println("===== STACK EXPERIMENT =====");

        try {
            recursiveMethod();
        } catch (StackOverflowError e) {
            System.out.println("StackOverflowError caught.");
            System.out.println("The thread stack limit was reached.");
        }
    }

    // Experiment 2: Heap exhaustion
    private static void testHeap() {
        System.out.println("===== HEAP EXPERIMENT =====");

        List<byte[]> memory = new ArrayList<>();
        int count = 0;

        try {
            while (true) {
                memory.add(new byte[1024 * 1024]);
                count++;
            }
        } catch (OutOfMemoryError e) {
            System.out.println("OutOfMemoryError caught.");
            System.out.println("Approximate MiB allocated: " + count);
        }
    }

    // Experiment 3: String vs StringBuilder
    private static void testStrings() {
        System.out.println("===== STRING EXPERIMENT =====");

        int limit = 20000;

        long start = System.nanoTime();
        String text = "";

        for (int i = 0; i < limit; i++) {
            text += "a";
        }

        long stringTime = System.nanoTime() - start;

        start = System.nanoTime();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < limit; i++) {
            builder.append("a");
        }

        long builderTime = System.nanoTime() - start;

        System.out.println("String length: " + text.length());
        System.out.println("String concatenation time: "
                + stringTime / 1_000_000.0 + " ms");
        System.out.println("StringBuilder length: " + builder.length());
        System.out.println("StringBuilder time: "
                + builderTime / 1_000_000.0 + " ms");
    }

    // Experiment 4: Integer cache
    private static void testIntegerCache() {
        System.out.println("===== INTEGER CACHE EXPERIMENT =====");

        Integer a = 100;
        Integer b = 100;

        Integer x = 200;
        Integer y = 200;

        System.out.println("100 using == : " + (a == b));
        System.out.println("200 using == : " + (x == y));

        System.out.println("200 using equals(): " + x.equals(y));
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Choose: stack, heap, strings, or integer");
            return;
        }

        switch (args[0].toLowerCase()) {
            case "stack":
                testStack();
                break;

            case "heap":
                testHeap();
                break;

            case "strings":
                testStrings();
                break;

            case "integer":
                testIntegerCache();
                break;

            default:
                System.out.println("Unknown experiment.");
        }
    }
}
