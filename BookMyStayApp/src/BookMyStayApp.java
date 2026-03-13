import java.util.*;

/**
 * Use Case 6: Reservation Confirmation & Room Allocation
 * This program processes booking requests from a queue,
 * allocates rooms safely, prevents double booking, and
 * updates inventory immediately.
 *
 * @author Monish
 * @version 6.0
 */

// Reservation class
class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Inventory Service
class InventoryService {

    private HashMap<String, Integer> inventory;

    public InventoryService() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void decrementRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// Booking Service
class BookingService {

    private InventoryService inventoryService;

    // Map room type → assigned room IDs
    private HashMap<String, Set<String>> allocatedRooms;

    public BookingService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        allocatedRooms = new HashMap<>();
    }

    public void processBookings(Queue<Reservation> requestQueue) {

        while (!requestQueue.isEmpty()) {

            Reservation reservation = requestQueue.poll();

            System.out.println("\nProcessing booking for: " + reservation.guestName);

            int available = inventoryService.getAvailability(reservation.roomType);

            if (available > 0) {

                // Generate unique room ID
                String roomId = reservation.roomType.replace(" ", "").substring(0, 2).toUpperCase()
                        + (allocatedRooms.size() + 1);

                allocatedRooms.putIfAbsent(reservation.roomType, new HashSet<>());

                Set<String> roomSet = allocatedRooms.get(reservation.roomType);

                // Ensure uniqueness
                if (!roomSet.contains(roomId)) {

                    roomSet.add(roomId);

                    // Update inventory immediately
                    inventoryService.decrementRoom(reservation.roomType);

                    System.out.println("Reservation Confirmed!");
                    System.out.println("Guest: " + reservation.guestName);
                    System.out.println("Room Type: " + reservation.roomType);
                    System.out.println("Assigned Room ID: " + roomId);

                }

            } else {
                System.out.println("No rooms available for " + reservation.roomType);
            }
        }
    }
}

// Main Application
class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("===== Hotel Booking System v6.0 =====");

        // Initialize inventory
        InventoryService inventoryService = new InventoryService();

        // Booking request queue (FIFO)
        Queue<Reservation> requestQueue = new LinkedList<>();

        requestQueue.add(new Reservation("Radhika", "Single Room"));
        requestQueue.add(new Reservation("Kavy", "Double Room"));
        requestQueue.add(new Reservation("Shraa", "Single Room"));
        requestQueue.add(new Reservation("Monish", "Suite Room"));

        // Booking service
        BookingService bookingService = new BookingService(inventoryService);

        // Process booking requests
        bookingService.processBookings(requestQueue);

        // Display remaining inventory
        inventoryService.displayInventory();

        System.out.println("\nRoom allocation completed.");
    }
}
