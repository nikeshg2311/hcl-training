import java.util.Scanner;

public class JFS27Menu {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== JFS-27 IT HELPDESK =====");
            System.out.println("1. Create IT Ticket");
            System.out.println("2. Automatic Ticket Assignment");
            System.out.println("3. Manage Ticket");
            System.out.println("4. Track SLA");
            System.out.println("5. Reassign Ticket");
            System.out.println("6. Link Knowledge Base Article");
            System.out.println("7. Reopen Ticket");
            System.out.println("8. View Agent Metrics");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Create IT Ticket selected.");
                    break;

                case 2:
                    System.out.println("Automatic Ticket Assignment selected.");
                    break;

                case 3:
                    System.out.println("Manage Ticket selected.");
                    break;

                case 4:
                    System.out.println("Track SLA selected.");
                    break;

                case 5:
                    System.out.println("Reassign Ticket selected.");
                    break;

                case 6:
                    System.out.println("Link Knowledge Base Article selected.");
                    break;

                case 7:
                    System.out.println("Reopen Ticket selected.");
                    break;

                case 8:
                    System.out.println("View Agent Metrics selected.");
                    break;

                case 9:
                    System.out.println("Exiting JFS-27 Helpdesk...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 9);

        scanner.close();
    }
}