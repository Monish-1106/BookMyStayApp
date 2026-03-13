import java.util.LinkedList;
import java.util.Queue;

/**
 * Use Case 5: Booking Request Queue (First-Come-First-Served)
 * This program demonstrates how booking requests are collected
 * and stored in a queue to maintain arrival order.
 *
 * No room allocation or inventory updates occur here.
 *
 * @author Monish
 * @version 5.0
 */

// Reservation class representing a booking request
class Reservation {

    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void displayReservation() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}

// Booking Request Queue manager
class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    // Add booking request to queue
    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("Booking request added for " + reservation.guestName);
    }

    // Display queued requests
    public void displayRequests() {
        System.out.println("\nCurrent Booking Requests (FIFO Order):");

        for (Reservation r : requestQueue) {
            r.displayReservation();
        }
    }
}

// Main application
class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("===== Hotel Booking System v5.0 =====\n");

        // Initialize booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Guests submitting booking requests
        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Double Room");
        Reservation r3 = new Reservation("Charlie", "Suite Room");

        // Add requests to queue
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Display queued booking requests
        bookingQueue.displayRequests();

        System.out.println("\nRequests stored in arrival order. Waiting for allocation.");
    }
}
