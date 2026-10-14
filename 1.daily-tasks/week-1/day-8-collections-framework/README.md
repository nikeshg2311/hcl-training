# Day 8 – Collections Framework Part 2

## Objective
Practice Java Maps, employee lookup, department frequency counting, sorting with Comparators, and the `equals()` and `hashCode()` contract.

## Concepts Covered

### 1. HashMap
Used to store employees with employee ID as the key. It allows efficient lookup using `employeeMap.get(id)`.

### 2. LinkedHashMap
Maintains entries in insertion order. Used to display employees in the same order in which they were added.

### 3. TreeMap
Stores department names in sorted key order. Used to display department counts alphabetically.

### 4. Map.merge()
Used to count the number of employees in each department:
`departmentCount.merge(department, 1, Integer::sum)`

### 5. Comparator
Used to sort employees by ID in ascending order.

### 6. Comparator.comparingDouble(), reversed(), thenComparing()
Used to sort employees by salary in descending order. Employee name is used as a secondary sorting condition when salaries are equal.

### 7. equals() and hashCode()
The Employee class compares employees using their IDs. Both methods are overridden consistently so equal employees have the same hash code.

A HashSet test creates two Employee objects with the same ID. The set size is 1, demonstrating that the duplicate is treated as the same employee.

## Application Features
- Display all employees.
- Search for an employee by ID.
- Handle a missing employee.
- Count employees by department.
- Sort employees by ID.
- Display the five highest salaries.
- Preserve insertion order with LinkedHashMap.
- Test duplicate employee IDs with HashSet.

## How to Compile and Run

From this directory, execute:

```powershell
javac -d out src\main\java\app\EmployeeDirectory.java
java -cp out app.EmployeeDirectory
```

## Verification
The program compiled and executed successfully. The output confirmed correct employee lookup, department counts, ascending ID order, descending salary order, insertion-order display, and duplicate detection using equals() and hashCode().

## Learning Outcome
Learned how to select appropriate Map implementations, count values with merge(), define custom sorting with Comparator, and maintain the equals()/hashCode() contract for hash-based collections.