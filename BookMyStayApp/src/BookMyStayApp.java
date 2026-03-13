/**
 * Use Case 2: Basic Room Types & Static Availability
 * This program demonstrates room initialization using abstraction and inheritance.
 * Different room types are created and their availability is displayed.
 *
 * @author Monish
 * @version 2.1
 */

// Abstract class representing a generic Room
abstract class Room {

    String roomType;
    int beds;
    int size;
    double price;

    // Constructor
    Room(String roomType, int beds, int size, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    // Method to display room details
    void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sq.ft");
        System.out.println("Price per night: $" + price);
    }
}

// Single Room class
class SingleRoom extends Room {

    SingleRoom() {
        super("Single Room", 1, 200, 100.0);
    }
}

// Double Room class
class DoubleRoom extends Room {

    DoubleRoom() {
        super("Double Room", 2, 350, 180.0);
    }
}

// Suite Room class
class SuiteRoom extends Room {

    SuiteRoom() {
        super("Suite Room", 3, 500, 300.0);
    }
}

// Application entry point
class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("===== Hotel Booking System v2.1 =====\n");

        // Creating room objects (Polymorphism)
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability variables
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        // Display room details and availability
        System.out.println("Single Room Details:");
        single.displayRoomDetails();
        System.out.println("Available: " + singleAvailable + "\n");

        System.out.println("Double Room Details:");
        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + doubleAvailable + "\n");

        System.out.println("Suite Room Details:");
        suite.displayRoomDetails();
        System.out.println("Available: " + suiteAvailable + "\n");

        System.out.println("Application terminated.");
    }
}
