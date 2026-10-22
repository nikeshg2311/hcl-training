# Day 13 â€” Functional Programming

## Objective
Practice Java lambda expressions, functional interfaces, method references, streams, and function composition.

## Deliverable
`FilterEngine.java`

## Concepts Implemented
- `Predicate<Product>` for filtering products
- Predicate composition with `and()`
- `Function<Product, String>` for product-name transformation
- Function composition using `andThen()`
- `Consumer<Product>` for printing product details
- `Supplier<Product>` for creating a default product
- Method references and stream operations

## Sample Output
The filter returns Mouse and Headphones because they are electronics, cost less than â‚¹5,000, and are in stock.

The program also prints product labels, demonstrates a consumer and supplier, and lists all products.

## Compile and Run
```powershell
javac FilterEngine.java
java FilterEngine
```

## Evidence
Store a screenshot of the successful program output in the `evidence` folder.

## JFS-27 Project Refactoring

Updated `JFS27Menu.java` to use lambda-based strategy selection with
`Map<Integer, Supplier<AssignmentStrategy>>`.

### Functional Programming Concepts
- `Supplier<AssignmentStrategy>` supplies the selected assignment strategy.
- `RoundRobinAssignmentStrategy::new` demonstrates a constructor method reference.
- `() -> new CategoryAssignmentStrategy(categoryMap)` demonstrates a lambda expression.
- A map connects menu options to strategy suppliers.

### Validation
- `mvn clean package` — BUILD SUCCESS across all five Maven modules.
- Round-robin assignment — Ticket 101 assigned to Agent1.
- Category-based assignment — Software ticket 102 assigned to Agent2.

Existing validation and error-handling conditions were retained.

### Run the JFS-27 Menu
From the project root:
```powershell
mvn -pl ticket-service exec:java "-Dexec.mainClass=JFS27Menu"
