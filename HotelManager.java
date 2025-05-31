import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class HotelManager {
    private Holder hotelData;
    private Scanner scanner;

    public HotelManager(Holder hotelData, Scanner scanner) {
        this.hotelData = hotelData;
        this.scanner = scanner;
    }

    public Holder getHotelData() {
        return this.hotelData;
    }

    private void custDetails(int roomCategory, int roomArrayIndex) {
        String name, contact, gender;
        String name2 = null, contact2 = null;
        String gender2 = "";

        // Consume any leftover newline from previous scanner.nextInt()
        // scanner.nextLine(); // This might be needed if previous input was nextInt() and not fully consumed

        System.out.print("\nEnter customer name: ");
        name = scanner.nextLine(); 
        System.out.print("Enter contact number: ");
        contact = scanner.next();
        System.out.print("Enter gender: ");
        gender = scanner.next();
        scanner.nextLine(); // Consume newline

        if (roomCategory == 1 || roomCategory == 2) { // Double rooms
            System.out.print("Enter second customer name: ");
            name2 = scanner.nextLine(); 
            System.out.print("Enter contact number for second customer: ");
            contact2 = scanner.next();
            System.out.print("Enter gender for second customer: ");
            gender2 = scanner.next();
            scanner.nextLine(); 
        }

        switch (roomCategory) {
            case 1:
                hotelData.luxury_doublerrom[roomArrayIndex] = new Doubleroom(name, contact, gender, name2, contact2, gender2);
                break;
            case 2:
                hotelData.deluxe_doublerrom[roomArrayIndex] = new Doubleroom(name, contact, gender, name2, contact2, gender2);
                break;
            case 3:
                hotelData.luxury_singleerrom[roomArrayIndex] = new Singleroom(name, contact, gender);
                break;
            case 4:
                hotelData.deluxe_singleerrom[roomArrayIndex] = new Singleroom(name, contact, gender);
                break;
            default:
                System.out.println("Error: Invalid room category in customer details.");
                break;
        }
    }

    public void bookRoom(int roomCategory) {
        int roomNumberInput;
        int roomArrayIndex = -1; 

        System.out.println("\nAvailable rooms: ");
        boolean availableFound = false;
        try {
            switch (roomCategory) {
                case 1: 
                    for (int j = 0; j < hotelData.luxury_doublerrom.length; j++) {
                        if (hotelData.luxury_doublerrom[j] == null) {
                            System.out.print((j + 1) + " "); availableFound = true;
                        }
                    }
                    if (!availableFound) { System.out.println("None"); throw new NotAvailable();}
                    System.out.print("\nEnter room number (1-10): ");
                    roomNumberInput = scanner.nextInt();
                    roomArrayIndex = roomNumberInput - 1;
                    if (roomArrayIndex < 0 || roomArrayIndex >= hotelData.luxury_doublerrom.length || hotelData.luxury_doublerrom[roomArrayIndex] != null) {
                        throw new NotAvailable();
                    }
                    break;
                case 2: 
                    for (int j = 0; j < hotelData.deluxe_doublerrom.length; j++) {
                        if (hotelData.deluxe_doublerrom[j] == null) {
                            System.out.print((j + 11) + " "); availableFound = true;
                        }
                    }
                    if (!availableFound) { System.out.println("None"); throw new NotAvailable();}
                    System.out.print("\nEnter room number (11-30): ");
                    roomNumberInput = scanner.nextInt();
                    roomArrayIndex = roomNumberInput - 11;
                    if (roomArrayIndex < 0 || roomArrayIndex >= hotelData.deluxe_doublerrom.length || hotelData.deluxe_doublerrom[roomArrayIndex] != null) {
                        throw new NotAvailable();
                    }
                    break;
                case 3: 
                    for (int j = 0; j < hotelData.luxury_singleerrom.length; j++) {
                        if (hotelData.luxury_singleerrom[j] == null) {
                            System.out.print((j + 31) + " "); availableFound = true;
                        }
                    }
                     if (!availableFound) { System.out.println("None"); throw new NotAvailable();}
                    System.out.print("\nEnter room number (31-40): ");
                    roomNumberInput = scanner.nextInt();
                    roomArrayIndex = roomNumberInput - 31;
                    if (roomArrayIndex < 0 || roomArrayIndex >= hotelData.luxury_singleerrom.length || hotelData.luxury_singleerrom[roomArrayIndex] != null) {
                        throw new NotAvailable();
                    }
                    break;
                case 4: 
                    for (int j = 0; j < hotelData.deluxe_singleerrom.length; j++) {
                        if (hotelData.deluxe_singleerrom[j] == null) {
                            System.out.print((j + 41) + " "); availableFound = true;
                        }
                    }
                    if (!availableFound) { System.out.println("None"); throw new NotAvailable();}
                    System.out.print("\nEnter room number (41-60): ");
                    roomNumberInput = scanner.nextInt();
                    roomArrayIndex = roomNumberInput - 41;
                    if (roomArrayIndex < 0 || roomArrayIndex >= hotelData.deluxe_singleerrom.length || hotelData.deluxe_singleerrom[roomArrayIndex] != null) {
                        throw new NotAvailable();
                    }
                    break;
                default:
                    System.out.println("Invalid room category.");
                    return;
            }
            scanner.nextLine(); // Consume newline after nextInt() for roomNumberInput
            custDetails(roomCategory, roomArrayIndex);
            System.out.println("Room Booked Successfully!");
        } catch (NotAvailable e) {
            System.out.println(e.toString() + " Or invalid room number selected.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number for room selection.");
            scanner.nextLine(); 
        } catch (Exception e) {
            System.out.println("An unexpected error occurred during booking: " + e.getMessage());
             e.printStackTrace();
        }
    }

    public void displayRoomFeatures(int roomCategory) {
        switch (roomCategory) {
            case 1: System.out.println("\nLuxury Double Room:\nNumber of double beds: 1\nAC: Yes\nFree breakfast: Yes\nCharge per day: 4000"); break;
            case 2: System.out.println("\nDeluxe Double Room:\nNumber of double beds: 1\nAC: No\nFree breakfast: Yes\nCharge per day: 3000"); break;
            case 3: System.out.println("\nLuxury Single Room:\nNumber of single beds: 1\nAC: Yes\nFree breakfast: Yes\nCharge per day: 2200"); break;
            case 4: System.out.println("\nDeluxe Single Room:\nNumber of single beds: 1\nAC: No\nFree breakfast: Yes\nCharge per day: 1200"); break;
            default: System.out.println("Invalid room category."); break;
        }
    }

    public void checkAvailability(int roomCategory) {
        int count = 0;
        switch (roomCategory) {
            case 1: for (Singleroom room : hotelData.luxury_doublerrom) if (room == null) count++; break;
            case 2: for (Singleroom room : hotelData.deluxe_doublerrom) if (room == null) count++; break;
            case 3: for (Singleroom room : hotelData.luxury_singleerrom) if (room == null) count++; break;
            case 4: for (Singleroom room : hotelData.deluxe_singleerrom) if (room == null) count++; break;
            default: System.out.println("Invalid room category."); return;
        }
        System.out.println("Number of rooms available: " + count);
    }

    public void generateBill(int roomArrayIndex, int roomCategory) {
        double amount = 0;
        String[] list = {"Sandwich", "Pasta", "Noodles", "Coke"};
        ArrayList<Food> foodOrders = null;
        Singleroom currentRoom = null;

        System.out.println("\n*******");
        System.out.println(" Bill:-");
        System.out.println("*******");

        switch (roomCategory) {
            case 1: amount = 4000; currentRoom = hotelData.luxury_doublerrom[roomArrayIndex]; break;
            case 2: amount = 3000; currentRoom = hotelData.deluxe_doublerrom[roomArrayIndex]; break;
            case 3: amount = 2200; currentRoom = hotelData.luxury_singleerrom[roomArrayIndex]; break;
            case 4: amount = 1200; currentRoom = hotelData.deluxe_singleerrom[roomArrayIndex]; break;
            default: System.out.println("Not a valid room type for billing."); return;
        }
        
        System.out.println("\nRoom Charge: " + String.format("%.2f", amount)); 

        if (currentRoom != null) {
            foodOrders = currentRoom.getFoodOrders();
            if (foodOrders != null && !foodOrders.isEmpty()) {
                System.out.println("\n===============");
                System.out.println("Food Charges:- ");
                System.out.println("===============");
                System.out.printf("%-10s %-10s %-10s%n", "Item", "Quantity", "Price");
                System.out.println("--------------------------------");
                for (Food obb : foodOrders) {
                    amount += obb.getPrice(); 
                    System.out.printf("%-10s %-10d %-10.2f%n", list[obb.getItemno() - 1], obb.getQuantity(), obb.getPrice());
                }
            } else {
                System.out.println("\nNo food ordered for this room.");
            }
        } else {
             System.out.println("Error: Room data not found for billing.");
             return; 
        }
        System.out.println("\nTotal Amount: " + String.format("%.2f", amount));
    }


    public void deallocateRoom(int roomArrayIndex, int roomCategory) {
        char w;
        Singleroom currentRoom = null;

        switch (roomCategory) {
            case 1: currentRoom = hotelData.luxury_doublerrom[roomArrayIndex]; break;
            case 2: currentRoom = hotelData.deluxe_doublerrom[roomArrayIndex]; break;
            case 3: currentRoom = hotelData.luxury_singleerrom[roomArrayIndex]; break;
            case 4: currentRoom = hotelData.deluxe_singleerrom[roomArrayIndex]; break;
            default: System.out.println("\nInvalid room category."); return;
        }

        if (currentRoom != null) {
            System.out.println("Room used by " + currentRoom.getName());
            System.out.println("Do you want to checkout? (y/n)");
            w = scanner.next().charAt(0);
            scanner.nextLine(); 

            if (w == 'y' || w == 'Y') {
                generateBill(roomArrayIndex, roomCategory);
                switch (roomCategory) { 
                    case 1: hotelData.luxury_doublerrom[roomArrayIndex] = null; break;
                    case 2: hotelData.deluxe_doublerrom[roomArrayIndex] = null; break;
                    case 3: hotelData.luxury_singleerrom[roomArrayIndex] = null; break;
                    case 4: hotelData.deluxe_singleerrom[roomArrayIndex] = null; break;
                }
                System.out.println("Deallocated successfully.");
            } else {
                System.out.println("Checkout cancelled.");
            }
        } else {
            System.out.println("Room is already empty or does not exist.");
        }
    }

    public void orderFood(int roomArrayIndex, int roomCategory) {
        int itemChoice, quantity;
        char wish = ' '; // Initialize wish here
        Singleroom currentRoom = null;

        try {
            switch (roomCategory) {
                case 1: currentRoom = hotelData.luxury_doublerrom[roomArrayIndex]; break;
                case 2: currentRoom = hotelData.deluxe_doublerrom[roomArrayIndex]; break;
                case 3: currentRoom = hotelData.luxury_singleerrom[roomArrayIndex]; break;
                case 4: currentRoom = hotelData.deluxe_singleerrom[roomArrayIndex]; break;
                default: System.out.println("Invalid room category for ordering food."); return;
            }

            if (currentRoom == null) {
                System.out.println("\nRoom not booked or does not exist. Cannot order food.");
                return;
            }

            System.out.println("\n==========\n  Menu:   \n==========\n\n1.Sandwich\tRp.50\n2.Pasta\t\tRp.60\n3.Noodles\tRp.70\n4.Coke\t\tRp.30\n");
            
            do { // Loop starts here
                System.out.print("Enter item number (1-4): ");
                if (scanner.hasNextInt()) {
                    itemChoice = scanner.nextInt();
                } else {
                    System.out.println("Invalid item number. Please enter a number.");
                    scanner.nextLine(); // Consume invalid input
                    continue; // Skip to next iteration
                }
                
                if (itemChoice < 1 || itemChoice > 4) {
                    System.out.println("Invalid item number. Please choose from the menu.");
                    scanner.nextLine(); 
                    continue;
                }

                System.out.print("Quantity: ");
                if (scanner.hasNextInt()) {
                    quantity = scanner.nextInt();
                } else {
                    System.out.println("Invalid quantity. Please enter a number.");
                    scanner.nextLine(); // Consume invalid input
                    continue; // Skip to next iteration
                }

                if (quantity <= 0) {
                    System.out.println("Quantity must be a positive number.");
                    scanner.nextLine(); 
                    continue;
                }
                scanner.nextLine(); // Consume newline after quantity

                currentRoom.addFoodOrder(new Food(itemChoice, quantity));
                System.out.println("Item added to order.");

                System.out.println("Do you want to order anything else? (y/n)");
                String wishInput = scanner.next();
                if (!wishInput.isEmpty()) {
                    wish = wishInput.toLowerCase().charAt(0);
                } else {
                    wish = 'n'; // Default to no if input is empty
                }
                scanner.nextLine(); // Consume newline
            } while (wish == 'y'); // Loop condition

            System.out.println("Food order process complete.");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter numbers for item choice and quantity.");
            if(scanner.hasNextLine()) scanner.nextLine(); // Consume the rest of the bad input line
        } catch (NullPointerException e) {
            System.out.println("\nError: Problem accessing room data for food order.");
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\nError: Invalid room index provided for food order.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred during food order: " + e.getMessage());
            e.printStackTrace();
        }
    }
}