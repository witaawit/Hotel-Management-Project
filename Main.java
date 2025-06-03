import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Holder hotelData = PersistenceManager.loadData();
        HotelManager hotelManager = new HotelManager(hotelData, sc);

        int choice = 0, subChoice = 0;
        char continueWish = 'y';

        mainLoop:
        do {
            System.out.println("\n======== Hotel Menu ========");
            System.out.println("1. Display Room Features");
            System.out.println("2. Display Room Availability");
            System.out.println("3. Book Room");
            System.out.println("4. Order Foods");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            try {
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next(); // consume the invalid input
                    continue;
                }
                sc.nextLine(); // Consume newline after reading integer

                switch (choice) {
                    case 1: // Display room features
                        System.out.println("\n--- Display Room Features ---");
                        System.out.println("Choose room type:\n1. Luxury Double\n2. Deluxe Double\n3. Luxury Single\n4. Deluxe Single");
                        System.out.print("Enter type (1-4): ");
                        if (sc.hasNextInt()) {
                            subChoice = sc.nextInt(); sc.nextLine();
                            hotelManager.displayRoomFeatures(subChoice);
                        } else { System.out.println("Invalid type."); sc.nextLine(); }
                        break;

                    case 2: // Display room availability
                        System.out.println("\n--- Display Room Availability ---");
                        System.out.println("Choose room type:\n1. Luxury Double\n2. Deluxe Double\n3. Luxury Single\n4. Deluxe Single");
                        System.out.print("Enter type (1-4): ");
                         if (sc.hasNextInt()) {
                            subChoice = sc.nextInt(); sc.nextLine();
                            hotelManager.checkAvailability(subChoice);
                        } else { System.out.println("Invalid type."); sc.nextLine(); }
                        break;

                    case 3: // Book room
                        System.out.println("\n--- Book Room ---");
                        System.out.println("Choose room type:\n1. Luxury Double\n2. Deluxe Double\n3. Luxury Single\n4. Deluxe Single");
                        System.out.print("Enter type (1-4): ");
                        if (sc.hasNextInt()) {
                            subChoice = sc.nextInt(); sc.nextLine();
                            hotelManager.bookRoom(subChoice);
                        } else { System.out.println("Invalid type."); sc.nextLine(); }
                        break;

                    case 4: // Order food
                        System.out.println("\n--- Order Food ---");
                        System.out.print("Enter Room Number (1-60): ");
                        if (sc.hasNextInt()) {
                            subChoice = sc.nextInt(); sc.nextLine();
                            if (subChoice >= 1 && subChoice <= 10) hotelManager.orderFood(subChoice - 1, 1);       // Luxury Double
                            else if (subChoice >= 11 && subChoice <= 30) hotelManager.orderFood(subChoice - 11, 2); // Deluxe Double
                            else if (subChoice >= 31 && subChoice <= 40) hotelManager.orderFood(subChoice - 31, 3); // Luxury Single
                            else if (subChoice >= 41 && subChoice <= 60) hotelManager.orderFood(subChoice - 41, 4); // Deluxe Single
                            else System.out.println("Room number out of range (1-60).");
                        } else { System.out.println("Invalid room number."); sc.nextLine(); }
                        break;

                    case 5: // Checkout
                        System.out.println("\n--- Checkout ---");
                        System.out.print("Enter Room Number (1-60): ");
                        if (sc.hasNextInt()) {
                            subChoice = sc.nextInt(); sc.nextLine();
                            if (subChoice >= 1 && subChoice <= 10) hotelManager.deallocateRoom(subChoice - 1, 1);
                            else if (subChoice >= 11 && subChoice <= 30) hotelManager.deallocateRoom(subChoice - 11, 2);
                            else if (subChoice >= 31 && subChoice <= 40) hotelManager.deallocateRoom(subChoice - 31, 3);
                            else if (subChoice >= 41 && subChoice <= 60) hotelManager.deallocateRoom(subChoice - 41, 4);
                            else System.out.println("Room number out of range (1-60).");
                        } else { System.out.println("Invalid room number."); sc.nextLine(); }
                        break;

                    case 6: // Exit
                        System.out.println("Exiting program...");
                        break mainLoop;

                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input type. Please enter a number.");
                sc.nextLine(); // Consume the invalid input to prevent infinite loop
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                e.printStackTrace(); // Helpful for debugging
            }

            if (choice != 6) { // Don't ask to continue if exiting
                System.out.print("\nDo you want to perform another action? (y/n): ");
                String wishInput = sc.next().toLowerCase();
                continueWish = wishInput.isEmpty() ? 'n' : wishInput.charAt(0);
                sc.nextLine(); // Consume newline
            }

        } while (continueWish == 'y' && choice !=6 );

        // Save data on exit
        System.out.println("Saving data before closing...");
        Thread backupThread = new Thread(new DataBackupWriter(hotelManager.getHotelData()));
        backupThread.start();
        try {
            backupThread.join(2000); // Wait for backup thread to finish (with a timeout)
        } catch (InterruptedException e) {
            System.err.println("Backup thread was interrupted.");
            Thread.currentThread().interrupt();
        }

        sc.close();
        System.out.println("Program terminated. Goodbye!");
    }
}