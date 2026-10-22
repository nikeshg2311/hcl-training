# Day 13 — Functional Programming

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
The filter returns Mouse and Headphones because they are electronics, cost less than ₹5,000, and are in stock.

The program also prints product labels, demonstrates a consumer and supplier, and lists all products.

## Compile and Run
```powershell
javac FilterEngine.java
java FilterEngine
```

## Evidence
Store a screenshot of the successful program output in the `evidence` folder.
