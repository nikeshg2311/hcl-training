import java.util.Scanner;

public class ATMSimulator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        final int CORRECT_PIN = 1234;
        int attempts = 0;
        boolean authenticated = false;

        // 3 PIN attempts
        while (attempts < 3) {

            System.out.print("Enter your PIN: ");
            int pin = scanner.nextInt();

            if (pin == CORRECT_PIN) {
                authenticated = true;
                System.out.println("Login successful!");
                break;
            }

            attempts++;

            if (attempts < 3) {
                System.out.println("Invalid PIN. Try again.");
                continue;
            }

            System.out.println("Too many incorrect attempts.");
        }

        // Stop if authentication failed
        if (!authenticated) {
            scanner.close();
            return;
        }

        int balance = 5000;
        int choice;

        // ATM menu
        do {

            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Mini Statement");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Current Balance: ₹" + balance);
                    break;

                case 2:
                    System.out.print("Enter deposit amount: ");
                    int deposit = scanner.nextInt();

                    if (deposit <= 0) {
                        System.out.println("Invalid deposit amount.");
                        continue;
                    }

                    balance += deposit;
                    System.out.println("Deposit successful.");
                    break;

                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    int withdrawal = scanner.nextInt();

                    if (withdrawal <= 0) {
                        System.out.println("Invalid withdrawal amount.");
                        continue;
                    }

                    if (withdrawal > balance) {
                        System.out.println("Insufficient balance.");
                        break;
                    }

                    balance -= withdrawal;
                    System.out.println("Withdrawal successful.");
                    break;

                case 4:
                    int[] transactions = {1000, -500, 2000, -300};

                    System.out.println("\n===== MINI STATEMENT =====");

                    for (int transaction : transactions) {
                        System.out.println("Transaction: ₹" + transaction);
                    }

                    break;

                case 5:
                    System.out.println("Thank you for using the ATM.");
                    break;

                default:
                    System.out.println("Invalid menu choice.");
            }

        } while (choice != 5);

        scanner.close();
    }
}