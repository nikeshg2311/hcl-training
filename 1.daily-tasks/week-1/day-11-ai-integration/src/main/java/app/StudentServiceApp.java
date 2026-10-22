
package app;

import java.util.NoSuchElementException;

public class StudentServiceApp {

    public static void main(String[] args) {

        StudentService service = new StudentService();

        // 1. Add a student
        Student student = new Student(1, "Arun", 20, 85.5);
        service.addStudent(student);
        System.out.println("Added: " + service.findStudentById(1));

        // 2. Display all students
        System.out.println("Total students: "
                + service.getAllStudents().size());

        // 3. Test duplicate ID
        try {
            service.addStudent(student);
        } catch (IllegalArgumentException e) {
            System.out.println("Duplicate check: " + e.getMessage());
        }

        // 4. Test missing student
        try {
            service.findStudentById(99);
        } catch (NoSuchElementException e) {
            System.out.println("Missing student check: " + e.getMessage());
        }

        // 5. Update student
        service.updateStudent(new Student(1, "Arun Kumar", 21, 90.0));
        System.out.println("Updated: " + service.findStudentById(1));

        // 6. Delete student
        service.deleteStudent(1);
        System.out.println("Total after deletion: "
                + service.getAllStudents().size());
    }
}
