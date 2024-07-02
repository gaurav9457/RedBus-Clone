package com.redbus.bean.output;

public class TicketData {
	private long bookingId;
	private String busName;
	private String isActive;
	private int ticketPrice;
	private String seats;
	private String busRegistrationNo;
	private int busType;
	private String boardingPoint;
	private String destinationPoint;
	private String isPrimo;
	
	public long getBookingId() {
		return bookingId;
	}
	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public int getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	public String getBusRegistrationNo() {
		return busRegistrationNo;
	}
	public void setBusRegistrationNo(String busRegistrationNo) {
		this.busRegistrationNo = busRegistrationNo;
	}
	public int getBusType() {
		return busType;
	}
	public void setBusType(int busType) {
		this.busType = busType;
	}
	public String getBoardingPoint() {
		return boardingPoint;
	}
	public void setBoardingPoint(String boardingPoint) {
		this.boardingPoint = boardingPoint;
	}
	public String getDestinationPoint() {
		return destinationPoint;
	}
	public void setDestinationPoint(String destinationPoint) {
		this.destinationPoint = destinationPoint;
	}
	public String getIsPrimo() {
		return isPrimo;
	}
	public void setIsPrimo(String isPrimo) {
		this.isPrimo = isPrimo;
	}
	public TicketData() {}
	
	public TicketData(long bookingId, String busName, String isActive,
			int ticketPrice, String seats, String busRegistrationNo,
			int busType, String boardingPoint, String destinationPoint,
			String isPrimo) {
		this.bookingId = bookingId;
		this.busName = busName;
		this.isActive = isActive;
		this.ticketPrice = ticketPrice;
		this.seats = seats;
		this.busRegistrationNo = busRegistrationNo;
		this.busType = busType;
		this.boardingPoint = boardingPoint;
		this.destinationPoint = destinationPoint;
		this.isPrimo = isPrimo;
	}
	
	@Override
	public String toString() {
		return "TicketData [bookingId=" + bookingId + ", busName=" + busName
				+ ", isActive=" + isActive + ", ticketPrice=" + ticketPrice
				+ ", seats=" + seats + ", busRegistrationNo="
				+ busRegistrationNo + ", busType=" + busType
				+ ", boardingPoint=" + boardingPoint + ", destinationPoint="
				+ destinationPoint + ", isPrimo=" + isPrimo + "]";
	}
}
