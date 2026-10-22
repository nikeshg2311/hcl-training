
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

class Product {
    private String name;
    private String category;
    private double price;
    private boolean inStock;

    public Product(String name, String category,
                   double price, boolean inStock) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.inStock = inStock;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isInStock() {
        return inStock;
    }

    @Override
    public String toString() {
        return name + " | " + category
                + " | Rs." + price
                + " | In stock: " + inStock;
    }
}

public class FilterEngine {

    public static void main(String[] args) {

        List<Product> products = new ArrayList<>();

        products.add(new Product("Laptop", "Electronics", 55000, true));
        products.add(new Product("Mouse", "Electronics", 800, true));
        products.add(new Product("Chair", "Furniture", 3500, false));
        products.add(new Product("Desk", "Furniture", 7000, true));
        products.add(new Product("Headphones", "Electronics", 2000, true));

        // Predicate: checks a condition
        Predicate<Product> affordable =
                p -> p.getPrice() < 5000;

        Predicate<Product> electronics =
                p -> p.getCategory().equalsIgnoreCase("Electronics");

        Predicate<Product> available =
                Product::isInStock;

        // Combine predicates using and()
        Predicate<Product> filtered =
                affordable.and(electronics).and(available);

        System.out.println("=== FILTERED PRODUCTS ===");
        products.stream()
                .filter(filtered)
                .forEach(System.out::println);

        // Function: transforms one value into another
        Function<Product, String> getProductName =
                Product::getName;

        Function<String, String> addLabel =
                name -> "Product: " + name;

        // Compose functions using andThen()
        Function<Product, String> productLabel =
                getProductName.andThen(addLabel);

        System.out.println("\n=== FUNCTION COMPOSITION ===");
        products.stream()
                .map(productLabel)
                .forEach(System.out::println);

        // Consumer: performs an action
        Consumer<Product> printProduct =
                product -> System.out.println(product);

        System.out.println("\n=== CONSUMER ===");
        printProduct.accept(products.get(0));

        // Supplier: creates or supplies a value
        Supplier<Product> defaultProduct =
                () -> new Product("Notebook", "Stationery", 100, true);

        System.out.println("\n=== SUPPLIER ===");
        System.out.println(defaultProduct.get());

        // Method reference example
        System.out.println("\n=== ALL PRODUCTS ===");
        products.forEach(System.out::println);
    }
}
