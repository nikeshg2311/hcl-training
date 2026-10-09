
# Day 5 – Inheritance, Polymorphism and Git

## Topics Covered
- Inheritance using `extends`
- Abstract classes and methods
- Method overriding using `@Override`
- Runtime polymorphism
- Interfaces using `implements`
- `super()` constructor chaining
- Git branching and merging

## Hands-on: Payment System

Implemented a payment system using Java OOP concepts.

### Classes
- `Payment` – abstract parent class
- `CardPayment` – processes card payments and supports refunds
- `UPIPayment` – processes UPI payments
- `Refundable` – interface defining refund behavior
- `PaymentApp` – application entry point

## Project Structure

```text
src/main/java/
├── app/
│   └── PaymentApp.java
├── model/
│   ├── Payment.java
│   ├── CardPayment.java
│   └── UPIPayment.java
└── payment/
    └── Refundable.java
```

## Compile and Run

From the `src/main/java` directory:

```bash
javac -d out model/Payment.java model/CardPayment.java model/UPIPayment.java payment/Refundable.java app/PaymentApp.java
java -cp out app.PaymentApp
```

## Expected Results
- Card payment succeeds
- UPI payment succeeds
- Card number is masked except for the last four digits
- A valid refund request displays a success message

## Evidence

See `evidence/payment-output.png` for the successful execution output.

## Git Practice

Feature branch: `feature/day-5-payment-system`

Practised creating a feature branch and preparing changes for merging into `main`.
