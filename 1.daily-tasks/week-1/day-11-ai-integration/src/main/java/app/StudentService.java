
package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class StudentService {

    private final Map<Integer, Student> students = new HashMap<>();

    public void addStudent(Student student) {
        validateStudent(student);

        int id = student.getId();

        if (students.containsKey(id)) {
            throw new IllegalArgumentException(
                    "Student already exists with ID: " + id);
        }

        students.put(id, copyOf(student));
    }

    public Student findStudentById(int id) {
        validateId(id);

        Student student = students.get(id);

        if (student == null) {
            throw new NoSuchElementException(
                    "Student not found with ID: " + id);
        }

        return copyOf(student);
    }

    public List<Student> getAllStudents() {
        List<Student> result = new ArrayList<>();

        for (Student student : students.values()) {
            result.add(copyOf(student));
        }

        return result;
    }

    public void updateStudent(Student student) {
        validateStudent(student);

        int id = student.getId();

        if (!students.containsKey(id)) {
            throw new NoSuchElementException(
                    "Cannot update; student not found with ID: " + id);
        }

        students.put(id, copyOf(student));
    }

    public void deleteStudent(int id) {
        validateId(id);

        if (students.remove(id) == null) {
            throw new NoSuchElementException(
                    "Cannot delete; student not found with ID: " + id);
        }
    }

    private static void validateStudent(Student student) {
        if (student == null) {
            throw new IllegalArgumentException(
                    "Student must not be null.");
        }

        validateId(student.getId());

        if (student.getName() == null
                || student.getName().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Student name must not be blank.");
        }

        if (student.getAge() <= 0) {
            throw new IllegalArgumentException(
                    "Student age must be greater than zero.");
        }

        if (!Double.isFinite(student.getGrade())
                || student.getGrade() < 0.0
                || student.getGrade() > 100.0) {
            throw new IllegalArgumentException(
                    "Student grade must be between 0 and 100.");
        }
    }

    private static void validateId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Student ID must be greater than zero.");
        }
    }

    private static Student copyOf(Student student) {
        return new Student(
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getGrade());
    }
}
