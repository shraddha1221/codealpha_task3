package hotel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Hotel {
	 public String name;
	 private List<Room> rooms;
	 private List<Reservation> reservations;
	 private int nextReservationId;

	 public Hotel(String name) {
	     this.name = name;
	     this.rooms = new ArrayList<>();
	     this.reservations = new ArrayList<>();
	     this.nextReservationId = 1;
	 }

	 public void addRoom(Room room) {
	     rooms.add(room);
	 }

	 public List<Room> getAvailableRooms() {
	     List<Room> availableRooms = new ArrayList<>();
	     for (Room room : rooms) {
	         if (room.isAvailable()) {
	             availableRooms.add(room);
	         }
	     }
	     return availableRooms;
	 }

	 public Reservation bookRoom(Room room, Date checkInDate, Date checkOutDate, String guestName, double totalPrice) {
	     if (!room.isAvailable()) {
	         return null;
	     }

	     Reservation reservation = new Reservation(nextReservationId++, room, checkInDate, checkOutDate, guestName, totalPrice);
	     reservations.add(reservation);
	     room.bookRoom();

	     return reservation;
	 }

	 public void cancelReservation(Reservation reservation) {
	     reservations.remove(reservation);
	     reservation.getRoom().releaseRoom();
	 }

	 public List<Reservation> getAllReservations() {
	     return reservations;
	 }
	}

