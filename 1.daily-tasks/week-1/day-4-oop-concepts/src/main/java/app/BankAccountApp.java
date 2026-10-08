package app;

import model.BankAccount;

public class BankAccountApp {

    public static void main(String[] args) {

        System.out.println("===== BANK ACCOUNT DEMO =====");

        // Constructor 1: default constructor
        BankAccount account1 = new BankAccount();

        // Constructor 2: account number + holder
        BankAccount account2 =
                new BankAccount("ACC1002", "Nikesh");

        // Constructor 3: all values
        BankAccount account3 =
                new BankAccount("ACC1003", "Rahul", 10000.0);

        System.out.println("\n===== INITIAL ACCOUNTS =====");

        System.out.println(account1);
        System.out.println(account2);
        System.out.println(account3);

        // Deposit
        System.out.println("\n===== DEPOSIT =====");

        account2.deposit(5000);

        System.out.println(
                "Account 2 Balance: ₹" + account2.getBalance()
        );

        // Withdrawal
        System.out.println("\n===== WITHDRAW =====");

        account3.withdraw(2500);

        System.out.println(
                "Account 3 Balance: ₹" + account3.getBalance()
        );

        // Invalid withdrawal
        account3.withdraw(10000);

        // equals()
        System.out.println("\n===== EQUALS =====");

        BankAccount account4 =
                new BankAccount("ACC1002", "Another Person");

        System.out.println(
                "account2 equals account4: "
                        + account2.equals(account4)
        );

        // hashCode()
        System.out.println("\n===== HASH CODE =====");

        System.out.println(
                "Account 2 hashCode: " + account2.hashCode()
        );

        System.out.println(
                "Account 4 hashCode: " + account4.hashCode()
        );

        // Static account counter
        System.out.println("\n===== ACCOUNT COUNT =====");

        System.out.println(
                "Total accounts created: "
                        + BankAccount.getAccountCount()
        );
    }
}