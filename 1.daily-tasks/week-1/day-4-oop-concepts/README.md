# Day 4 – OOP Concepts + IDE & Debugging

## Topics Covered

- Classes and Objects
- Fields and Methods
- Constructors
- Constructor Overloading
- Constructor Chaining using `this()`
- Encapsulation
- Access Modifiers
- Packages
- Static vs Instance Members
- `equals()`
- `hashCode()`
- VS Code Debugging
- Breakpoints
- Step Over
- Variables
- Call Stack

## Hands-on: BankAccount

The `BankAccount` class demonstrates:

- Private fields for encapsulation
- Three overloaded constructors
- Constructor chaining using `this()`
- Deposit and withdrawal validation
- Static account counter
- `equals()` for account identity comparison
- `hashCode()` consistent with `equals()`
- `toString()` for object representation

## Package Structure

```text
src/main/java/
├── app/
│   └── BankAccountApp.java
├── model/
│   └── BankAccount.java
└── service/