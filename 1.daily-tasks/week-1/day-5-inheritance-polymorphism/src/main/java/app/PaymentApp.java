
package app;

import model.Payment;
import model.CardPayment;
import model.UPIPayment;
import payment.Refundable;

public class PaymentApp {

    public static void main(String[] args) {

        System.out.println("===== PAYMENT SYSTEM =====");

        Payment payment1 =
                new CardPayment(2000, "1234567812345678");

        Payment payment2 =
                new UPIPayment(1500, "nikesh@upi");

        System.out.println("\n--- Card Payment ---");
        payment1.displayAmount();
        payment1.processPayment();

        System.out.println("\n--- UPI Payment ---");
        payment2.displayAmount();
        payment2.processPayment();

        System.out.println("\n--- Refund ---");

        Refundable refundable = (Refundable) payment1;
        refundable.refund(500);
    }
}
