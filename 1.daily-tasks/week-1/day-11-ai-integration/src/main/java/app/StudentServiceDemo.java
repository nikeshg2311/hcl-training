package app;

import java.util.NoSuchElementException;

public class StudentServiceDemo {

    public static void main(String[] args) {
        StudentService service = new StudentService();

        service.addStudent(new Student(1001L, "Asha", 20, 86.5));
        service.addStudent(new Student(1002L, "Ravi", 21, 72.0));

        System.out.println("All students: " + service.getAllStudents());
        System.out.println("Found student: " + service.findStudentById(1001L));

        service.updateStudent(new Student(1002L, "Ravi Kumar", 22, 78.5));
        System.out.println("Updated student: " + service.findStudentById(1002L));

        expectFailure("Null student", IllegalArgumentException.class,
                () -> service.addStudent(null));
        expectFailure("Duplicate ID", IllegalArgumentException.class,
                () -> service.addStudent(new Student(1001L, "Another Student", 19, 80.0)));
        expectFailure("Invalid student details", IllegalArgumentException.class,
                () -> service.addStudent(new Student(1003L, " ", 19, 80.0)));
        expectFailure("Find missing student", NoSuchElementException.class,
                () -> service.findStudentById(9999L));
        expectFailure("Update missing student", NoSuchElementException.class,
                () -> service.updateStudent(new Student(9999L, "Unknown", 20, 50.0)));

        service.deleteStudent(1001L);
        System.out.println("After deletion: " + service.getAllStudents());
        expectFailure("Delete missing student", NoSuchElementException.class,
                () -> service.deleteStudent(1001L));
    }

    private static void expectFailure(String scenario,
                                     Class<? extends RuntimeException> expectedException,
                                     Runnable operation) {
        try {
            operation.run();
        } catch (RuntimeException exception) {
            if (expectedException.isInstance(exception)) {
                System.out.println(scenario + " handled: " + exception.getMessage());
                return;
            }
            throw exception;
        }

        throw new AssertionError(scenario + " should have failed with "
                + expectedException.getSimpleName());
    }
}
