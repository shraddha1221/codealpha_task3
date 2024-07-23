package hotel;

import java.util.Date;

class Reservation {
	 private int reservationId;
	 private Room room;
	 private Date checkInDate;
	 private Date checkOutDate;
	 private String guestName;
	 private double totalPrice;

	 public Reservation(int reservationId, Room room, Date checkInDate, Date checkOutDate, String guestName, double totalPrice) {
	     this.reservationId = reservationId;
	     this.room = room;
	     this.checkInDate = checkInDate;
	     this.checkOutDate = checkOutDate;
	     this.guestName = guestName;
	     this.totalPrice = totalPrice;
	 }

	 public int getReservationId() {
	     return reservationId;
	 }

	 public Room getRoom() {
	     return room;
	 }

	 public Date getCheckInDate() {
	     return checkInDate;
	 }

	 public Date getCheckOutDate() {
	     return checkOutDate;
	 }

	 public String getGuestName() {
	     return guestName;
	 }

	 public double getTotalPrice() {
	     return totalPrice;
	 }
	}

