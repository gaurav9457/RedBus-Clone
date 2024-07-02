package com.redbus.bean.output;

import java.sql.Date;
import java.sql.Timestamp;

public class AllBookings {
	private long bookingId;
	private Date bookingDate;
	private String gender;
	private int totalPrice;
	private String bookedSeats;
	private String isActive;
	private Timestamp createdAt;
	private String boardingPoint;
	private String droppingPoint;
	private int busType;
	private String busRegistrationNo;
	private String isPrimo;
	private String busName;
	private String route;
	private String name;
	private long mobileNumber;
	private String email;
	
	public long getBookingId() {
		return bookingId;
	}
	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getBookedSeats() {
		return bookedSeats;
	}
	public void setBookedSeats(String bookedSeats) {
		this.bookedSeats = bookedSeats;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public String getBoardingPoint() {
		return boardingPoint;
	}
	public void setBoardingPoint(String boardingPoint) {
		this.boardingPoint = boardingPoint;
	}
	public String getDroppingPoint() {
		return droppingPoint;
	}
	public void setDroppingPoint(String droppingPoint) {
		this.droppingPoint = droppingPoint;
	}
	public int getBusType() {
		return busType;
	}
	public void setBusType(int busType) {
		this.busType = busType;
	}
	public String getBusRegistrationNo() {
		return busRegistrationNo;
	}
	public void setBusRegistrationNo(String busRegistrationNo) {
		this.busRegistrationNo = busRegistrationNo;
	}
	public String getIsPrimo() {
		return isPrimo;
	}
	public void setIsPrimo(String isPrimo) {
		this.isPrimo = isPrimo;
	}
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public AllBookings() {}
	
	public AllBookings(long bookingId, Date bookingDate, String gender,
			int totalPrice, String bookedSeats, String isActive,
			Timestamp createdAt, String boardingPoint, String droppingPoint,
			int busType, String busRegistrationNo, String isPrimo,
			String busName, String route, String name, long mobileNumber,
			String email) {
		this.bookingId = bookingId;
		this.bookingDate = bookingDate;
		this.gender = gender;
		this.totalPrice = totalPrice;
		this.bookedSeats = bookedSeats;
		this.isActive = isActive;
		this.createdAt = createdAt;
		this.boardingPoint = boardingPoint;
		this.droppingPoint = droppingPoint;
		this.busType = busType;
		this.busRegistrationNo = busRegistrationNo;
		this.isPrimo = isPrimo;
		this.busName = busName;
		this.route = route;
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "AllBookings [bookingId=" + bookingId + ", bookingDate="
				+ bookingDate + ", gender=" + gender + ", totalPrice="
				+ totalPrice + ", bookedSeats=" + bookedSeats + ", isActive="
				+ isActive + ", createdAt=" + createdAt + ", boardingPoint="
				+ boardingPoint + ", droppingPoint=" + droppingPoint
				+ ", busType=" + busType + ", busRegistrationNo="
				+ busRegistrationNo + ", isPrimo=" + isPrimo + ", busName="
				+ busName + ", route=" + route + ", name=" + name
				+ ", mobileNumber=" + mobileNumber + ", email=" + email + "]";
	}
}
