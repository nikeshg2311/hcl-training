public class MonthlyUsageAnalyzer {

    public static void main(String[] args) {

        // 12 months of usage
        int[] monthlyUsage = {
                120, 150, 100, 180,
                200, 170, 160, 190,
                210, 220, 180, 250
        };

        int total = 0;
        int minimum = monthlyUsage[0];
        int maximum = monthlyUsage[0];

        for (int usage : monthlyUsage) {

            total += usage;

            if (usage < minimum) {
                minimum = usage;
            }

            if (usage > maximum) {
                maximum = usage;
            }
        }

        // Using the constant from Constants.java
        double average = (double) total / Constants.MONTHS;

        System.out.println("===== Monthly Usage Analyzer =====");

        System.out.println("Total Usage   : " + total);
        System.out.println("Average Usage : " + average);
        System.out.println("Minimum Usage : " + minimum);
        System.out.println("Maximum Usage : " + maximum);

        // Integer overflow demonstration
        int maxInt = Integer.MAX_VALUE;

        System.out.println("\n===== Integer Overflow =====");

        System.out.println("Maximum int : " + maxInt);
        System.out.println("After +1   : " + (maxInt + 1));

        // Fix overflow using long
        long safeValue = (long) Integer.MAX_VALUE + 1;

        System.out.println("\n===== Fixed Using long =====");

        System.out.println("Safe value  : " + safeValue);

        // Widening conversion
        int smallNumber = 100;
        long widenedNumber = smallNumber;

        // Narrowing conversion
        long largeNumber = 1000L;
        int narrowedNumber = (int) largeNumber;

        System.out.println("\n===== Type Casting =====");

        System.out.println("Widening int -> long : " + widenedNumber);
        System.out.println("Narrowing long -> int : " + narrowedNumber);

        // Floating-point precision
        double a = 0.1;
        double b = 0.2;

        System.out.println("\n===== Floating Point Precision =====");

        System.out.println("0.1 + 0.2 = " + (a + b));

        // 2-D array for 3 houses and 1 week
        int[][] houseUsage = {
                {10, 12, 11, 15, 13, 14, 16},
                {20, 18, 22, 21, 19, 23, 20},
                {8, 9, 10, 7, 11, 12, 10}
        };

        System.out.println("\n===== Weekly Usage =====");

        for (int house = 0; house < houseUsage.length; house++) {

            System.out.print("House " + (house + 1) + ": ");

            for (int day = 0; day < houseUsage[house].length; day++) {
                System.out.print(houseUsage[house][day] + " ");
            }

            System.out.println();
        }
    }
}