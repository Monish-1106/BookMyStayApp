import java.util.HashMap;
import java.util.Map;

/**
 * Use Case 4: Room Search & Availability Check
 * This program allows guests to search for available rooms
 * without modifying the system state.
 *
 * @author Monish
 * @version 4.0
 */

// Abstract Room class
abstract class Room {
    String roomType;
    double price;
    String amenities;

    public Room(String roomType, double price, String amenities) {
        this.roomType = roomType;
        this.price = price;
        this.amenities = amenities;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Price per night: $" + price);
        System.out.println("Amenities: " + amenities);
    }
}

// Single Room
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 100.0, "1 Bed, Free WiFi");
    }
}

// Double Room
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 180.0, "2 Beds, Free WiFi, TV");
    }
}

// Suite Room
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 300.0, "King Bed, WiFi, TV, Mini Bar");
    }
}

// Inventory class (state holder)
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 0); // Example: no suites available
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

// Search Service (read-only access)
class RoomSearchService {

    public void searchAvailableRooms(RoomInventory inventory, Room[] rooms) {

        System.out.println("Available Rooms:\n");

        for (Room room : rooms) {
            int available = inventory.getAvailability(room.roomType);

            // Show only available rooms
            if (available > 0) {
                room.displayRoomDetails();
                System.out.println("Available: " + available);
                System.out.println("---------------------------");
            }
        }
    }
}

// Main application
class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("===== Hotel Booking System v4.0 =====\n");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Create room objects
        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        // Search service
        RoomSearchService searchService = new RoomSearchService();

        // Guest searches for rooms
        searchService.searchAvailableRooms(inventory, rooms);

        System.out.println("\nSearch completed. System state unchanged.");
    }
}
