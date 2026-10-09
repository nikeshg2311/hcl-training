
package model;

public class UPIPayment extends Payment {

    private String upiId;

    public UPIPayment(double amount, String upiId) {
        super(amount);
        this.upiId = upiId;
    }

    @Override
    public void processPayment() {
        System.out.println("Processing UPI payment...");
        System.out.println("UPI ID: " + upiId);
        System.out.println("UPI payment successful!");
    }
}
