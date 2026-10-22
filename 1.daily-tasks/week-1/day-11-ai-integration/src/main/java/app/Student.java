
package app;

public class Student {

    private final int id;
    private String name;
    private int age;
    private double grade;

    public Student(int id, String name, int age, double grade) {
        validateId(id);
        validateName(name);
        validateAge(age);
        validateGrade(grade);

        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        validateAge(age);
        this.age = age;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        validateGrade(grade);
        this.grade = grade;
    }

    public boolean hasPassed() {
        return grade >= 50.0;
    }

    private static void validateId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Student ID must be greater than zero.");
        }
    }

    private static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Student name must not be blank.");
        }
    }

    private static void validateAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException(
                    "Student age must be greater than zero.");
        }
    }

    private static void validateGrade(double grade) {
        if (!Double.isFinite(grade) || grade < 0.0 || grade > 100.0) {
            throw new IllegalArgumentException(
                    "Student grade must be between 0 and 100.");
        }
    }

    @Override
    public String toString() {
        return "Student{id=" + id
                + ", name='" + name + '\''
                + ", age=" + age
                + ", grade=" + grade + '}';
    }
}
