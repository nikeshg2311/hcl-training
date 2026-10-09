
package model;

import payment.Refundable;

public class CardPayment extends Payment implements Refundable {

    private String cardNumber;

    public CardPayment(double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing card payment...");
        System.out.println("Card Number: ****"
                + cardNumber.substring(cardNumber.length() - 4));
        System.out.println("Card payment successful!");
    }

    @Override
    public void refund(double amount) {

        if (amount <= 0 || amount > this.amount) {
            System.out.println("Invalid refund amount.");
            return;
        }

        System.out.println("Refund of " + amount + " processed successfully.");
    }
}
