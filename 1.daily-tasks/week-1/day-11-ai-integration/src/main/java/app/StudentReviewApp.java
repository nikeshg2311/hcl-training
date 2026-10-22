
package app;

public class StudentReviewApp {

    public static void main(String[] args) {

        System.out.println("===== AI CODE REVIEW TESTS =====");

        // Test 1: Invalid ID accepted by constructor
        Student s1 = new Student(-1, "Arun", 20, 80);
        System.out.println("Negative ID accepted: " + s1.getId());

        // Test 2: Blank name accepted by constructor
        Student s2 = new Student(2, "   ", 20, 80);
        System.out.println("Blank name accepted: [" + s2.getName() + "]");

        // Test 3: Invalid age accepted by constructor
        Student s3 = new Student(3, "Meena", -5, 80);
        System.out.println("Negative age accepted: " + s3.getAge());

        // Test 4: Invalid grade accepted by constructor
        Student s4 = new Student(4, "Ravi", 20, 150);
        System.out.println("Grade above 100 accepted: " + s4.getGrade());

        // Test 5: Invalid values accepted by setters
        Student s5 = new Student(5, "Priya", 20, 80);
        s5.setName("");
        s5.setAge(-2);
        s5.setGrade(-10);

        System.out.println("Blank name via setter: [" + s5.getName() + "]");
        System.out.println("Invalid age via setter: " + s5.getAge());
        System.out.println("Invalid grade via setter: " + s5.getGrade());

        // Verify that the service rejects invalid students
        StudentService service = new StudentService();

        try {
            service.addStudent(s4);
        } catch (IllegalArgumentException e) {
            System.out.println("Service validation: " + e.getMessage());
        }
    }
}
