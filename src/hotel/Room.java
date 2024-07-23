package hotel;

enum RoomCategory {
STANDARD,
DELUXE,
SUITE
}
class Room {
	 private int roomNumber;
	 private RoomCategory category;
	 private boolean isAvailable;
	 private double price;

	 public Room(int roomNumber, double price, RoomCategory category) {
	     this.roomNumber = roomNumber;
	     this.category = category;
	     this.isAvailable = true; 
	     this.price=price;
	 }

	 public double getPrice() {
	     return price;
	 }
	 public int getRoomNumber() {
	     return roomNumber;
	 }

	 public RoomCategory getCategory() {
	     return category;
	 }

	 public boolean isAvailable() {
	     return isAvailable;
	 }

	 public void bookRoom() {
	     this.isAvailable = false;
	 }

	 public void releaseRoom() {
	     this.isAvailable = true;
	 }
	}


