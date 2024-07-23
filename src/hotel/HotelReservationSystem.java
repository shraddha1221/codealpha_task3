package hotel;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class HotelReservationSystem {
	 private static Scanner scanner = new Scanner(System.in);
	 private static Hotel hotel = new Hotel("Sample Hotel");
	 private static Payment paymentProcessor = new Payment();

	 public static void main(String[] args) {
	     initializeRooms();

	     boolean exit = false;
	     while (!exit) {
	         System.out.println("\nWelcome to Hotel Reservation System of " + hotel.name);
	         System.out.println("1. Search Available Rooms");
	         System.out.println("2. Make a Reservation");
	         System.out.println("3. View Booking Details");
	         System.out.println("4. Cancel a Reservation");
	         System.out.println("5. Exit");

	         System.out.print("Enter your choice: ");
	         int choice = scanner.nextInt();
	         scanner.nextLine();

	         switch (choice) {
	             case 1:
	                 searchAvailableRooms();
	                 break;
	             case 2:
	                 makeReservation();
	                 break;
	             case 3:
	                 viewBookingDetails();
	                 break;
	             case 4:
	    	         System.out.println("Enter Room Number For Cancel Reservation");
	            	 int roomNum=scanner.nextInt();
	            	 cancelReservation(roomNum);
	            	 break;
	             case 5:
	                 exit = true;
	                 System.out.println("Thank you for using the Hotel Reservation System.");
	                 break;
	             default:
	                 System.out.println("Invalid choice. Please enter a number from 1 to 4.");
	         }
	     }
	 }

	 private static void initializeRooms() {
	     Room room1 = new Room(101,400.0, RoomCategory.STANDARD);
	     Room room2 = new Room(102,800.0, RoomCategory.DELUXE);
	     Room room3 = new Room(103,1200.0, RoomCategory.SUITE);

	     hotel.addRoom(room1);
	     hotel.addRoom(room2);
	     hotel.addRoom(room3);
	 }

	 private static void searchAvailableRooms() {
	     List<Room> availableRooms = hotel.getAvailableRooms();

	     if (availableRooms.isEmpty()) {
	         System.out.println("No rooms available at the moment.");
	     } else {
	         System.out.println("Available Rooms:");
	         for (Room room : availableRooms) {
	             System.out.println("Room Number: " + room.getRoomNumber() + " | Category: " + room.getCategory() + " | Price: "+ room.getPrice());
	         }
	     }
	 }
	 private static void cancelReservation(int roomNum) {
		 List<Reservation> reservations = hotel.getAllReservations();
		  for (Reservation reservation : reservations) {
		  if(roomNum==reservation.getRoom().getRoomNumber()) {
			  hotel.cancelReservation(reservation);
		         System.out.println("Rservation Cancel Successfully for Room Number :" +roomNum);
			  break;
		  }
		}
		  System.out.println("Reservation not found for Room Number" +roomNum);
			 
	 }

	 private static void makeReservation() {
	     System.out.println("Enter Guest Name:");
	     String guestName = scanner.nextLine();

	     System.out.println("Enter Check-in Date (YYYY-MM-DD):");
	     String checkInStr = scanner.nextLine();
	     Date checkInDate = parseDate(checkInStr);

	     System.out.println("Enter Check-out Date (YYYY-MM-DD):");
	     String checkOutStr = scanner.nextLine();
	     Date checkOutDate = parseDate(checkOutStr);

	     System.out.println("Choose Room Number:");
	     List<Room> availableRooms = hotel.getAvailableRooms();
	     for (Room room : availableRooms) {
	         System.out.println(room.getRoomNumber() + " - " + room.getCategory());
	     }
	     System.out.print("Room Number: ");
	     int roomNumber = scanner.nextInt();

	     Room selectedRoom = null;
	     for (Room room : availableRooms) {
	         if (room.getRoomNumber() == roomNumber) {
	             selectedRoom = room;
	             break;
	         }
	     }

	     if (selectedRoom == null) {
	         System.out.println("Invalid room number.");
	         return;
	     }

	     double totalPrice = calculateTotalPrice(checkInDate, checkOutDate, selectedRoom.getCategory());
	     Reservation reservation = hotel.bookRoom(selectedRoom, checkInDate, checkOutDate, guestName, totalPrice);

	     if (reservation != null) {
	         System.out.println("Room booked successfully. Reservation ID: " + reservation.getReservationId());
	         paymentProcessor.processPayment(guestName, totalPrice);
	     } else {
	         System.out.println("Failed to book the room. Please try again later.");
	     }
	 }

	 private static void viewBookingDetails() {
	     List<Reservation> reservations = hotel.getAllReservations();

	     if (reservations.isEmpty()) {
	         System.out.println("No reservations found.");
	     } else {
	         System.out.println("Booking Details:");
	         for (Reservation reservation : reservations) {
	             System.out.println("Reservation ID: " + reservation.getReservationId());
	             System.out.println("Guest Name: " + reservation.getGuestName());
	             System.out.println("Room Number: " + reservation.getRoom().getRoomNumber());
	             System.out.println("Check-in Date: " + reservation.getCheckInDate());
	             System.out.println("Check-out Date: " + reservation.getCheckOutDate());
	             System.out.println("Total Price: " + reservation.getTotalPrice());
	             System.out.println("---------------------");
	         }
	     }
	 }

	 private static Date parseDate(String dateStr) {
	     return new Date();
	 }

	 private static double calculateTotalPrice(Date checkInDate, Date checkOutDate, RoomCategory category) {
	     if(category.equals("STANDARD"))
	    	 return 400.0;
	     else if(category.equals("DELUXE"))
	    	 return 800.0;
	     else 
	    	 return 1200;
	     
	 }
	}
