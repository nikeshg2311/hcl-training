
package model;

public abstract class Payment {

    protected double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public abstract void processPayment();

    public void displayAmount() {
        System.out.println("Payment Amount: " + amount);
    }
}
