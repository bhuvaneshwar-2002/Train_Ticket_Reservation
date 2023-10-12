import java.util.Scanner;
import System.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Train Reservation System");
            System.out.println("1. Book a Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Print All Tickets");
            System.out.println("4. Check Ticket Status");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bookTicket(scanner);
                    break;
                case 2:
                    cancelTicket(scanner);
                    break;
                case 3:
                    Booking.printAllTickets();
                    break;
                case 4:
                    checkTicketStatus(scanner);
                    break;
                case 5:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void bookTicket(Scanner scanner) {
        System.out.print("Enter passenger name: ");
        String name = scanner.next();
        System.out.print("Enter passenger age: ");
        int age = scanner.nextInt();
        System.out.print("Enter berth preference (lower/middle/upper/waiting): ");
        String berthPreference = scanner.next();

        Passenger passenger = new Passenger(name, age, berthPreference);
        Booking.bookTicket(passenger);
    }
    private static void cancelTicket(Scanner scanner) {
        System.out.print("Enter Ticket ID to cancel: ");
        String ticketId = scanner.next();
        Booking.cancelTicket(ticketId);
    }

    private static void checkTicketStatus(Scanner scanner) {
        System.out.print("Enter Ticket ID: ");
        String ticketId = scanner.next();
        Booking.checkTicketStatus(ticketId);
    }
}

