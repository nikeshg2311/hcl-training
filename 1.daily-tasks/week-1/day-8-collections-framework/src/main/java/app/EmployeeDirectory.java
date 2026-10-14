
package app;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class EmployeeDirectory {

    static class Employee {
        private int id;
        private String name;
        private String department;
        private double salary;

        public Employee(int id, String name,
                        String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public double getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return "Employee{id=" + id
                    + ", name='" + name + '\''
                    + ", department='" + department + '\''
                    + ", salary=" + salary + '}';
        }

        // Two employees are equal if their IDs match
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Employee other = (Employee) obj;
            return this.id == other.id;
        }

        // Equal employees must have the same hash code
        @Override
        public int hashCode() {
            return Integer.hashCode(id);
        }
    }

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(104, "Priya", "IT", 55000));
        employees.add(new Employee(101, "Arun", "HR", 40000));
        employees.add(new Employee(103, "Kavin", "IT", 65000));
        employees.add(new Employee(102, "Meena", "Finance", 50000));
        employees.add(new Employee(105, "Ravi", "HR", 60000));
        employees.add(new Employee(106, "Divya", "Finance", 70000));

        System.out.println("===== EMPLOYEE DIRECTORY =====");
        for (Employee employee : employees) {
            System.out.println(employee);
        }

        // HashMap: Find an employee using ID
        Map<Integer, Employee> employeeMap = new HashMap<>();

        for (Employee employee : employees) {
            employeeMap.put(employee.getId(), employee);
        }

        System.out.println("\n===== EMPLOYEE LOOKUP =====");

        int searchId = 103;
        Employee found = employeeMap.get(searchId);

        if (found != null) {
            System.out.println("Employee found: " + found);
        } else {
            System.out.println("Employee not found: " + searchId);
        }

        System.out.println("\n===== MISSING EMPLOYEE =====");

        Employee missing = employeeMap.get(999);

        if (missing == null) {
            System.out.println("Employee 999 not found.");
        }

        // TreeMap + merge(): Count employees by department
        System.out.println("\n===== DEPARTMENT FREQUENCY =====");

        Map<String, Integer> departmentCount = new TreeMap<>();

        for (Employee employee : employees) {
            departmentCount.merge(
                    employee.getDepartment(),
                    1,
                    Integer::sum
            );
        }

        departmentCount.forEach((department, count) ->
                System.out.println(department + ": " + count)
        );

        // Comparator: Sort employees by ID
        System.out.println("\n===== EMPLOYEES SORTED BY ID =====");

        List<Employee> sortedById = new ArrayList<>(employees);

        sortedById.sort(Comparator.comparing(Employee::getId));

        for (Employee employee : sortedById) {
            System.out.println(
                    employee.getId() + " - " + employee.getName()
            );
        }

        // Comparator: Top five salaries, highest first
        System.out.println("\n===== TOP 5 SALARIES =====");

        List<Employee> topSalaries = new ArrayList<>(employees);

        Comparator<Employee> salaryComparator =
                Comparator.comparingDouble(Employee::getSalary)
                        .reversed()
                        .thenComparing(Employee::getName);

        topSalaries.sort(salaryComparator);

        for (int i = 0; i < Math.min(5, topSalaries.size()); i++) {
            Employee employee = topSalaries.get(i);

            System.out.println(
                    employee.getName() + " - " + employee.getSalary()
            );
        }

        // LinkedHashMap: Preserve insertion order
        System.out.println("\n===== LINKEDHASHMAP DIRECTORY =====");

        Map<Integer, Employee> orderedEmployees = new LinkedHashMap<>();

        for (Employee employee : employees) {
            orderedEmployees.put(employee.getId(), employee);
        }

        orderedEmployees.forEach((id, employee) ->
                System.out.println(id + " - " + employee.getName())
        );

        // HashSet: Test equals() and hashCode()
        System.out.println("\n===== EQUALS AND HASHCODE TEST =====");

        Set<Employee> uniqueEmployees = new HashSet<>();

        Employee employee1 =
                new Employee(101, "Arun", "HR", 40000);

        Employee employee2 =
                new Employee(101, "Arun Kumar", "HR", 45000);

        uniqueEmployees.add(employee1);
        uniqueEmployees.add(employee2);

        System.out.println("Are employees equal? "
                + employee1.equals(employee2));

        System.out.println("HashSet size: " + uniqueEmployees.size());

        if (uniqueEmployees.size() == 1) {
            System.out.println(
                    "SUCCESS: Duplicate employee ID was detected."
            );
        }
    }
}
