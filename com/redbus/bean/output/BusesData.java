package com.redbus.bean.output;

import java.sql.Time;

public class BusesData {
	private long busId;
	private String busName;
	private String route;
	private String startingPoint;
	private String endingPoint;
	private Time startTime;
	private Time endTime;
	private int busType;
	private String isPrimo;
	private String busRegistrationNumber;
	private int ticketPrice;
	private int totalSeats;
	
	public long getBusId() {
		return busId;
	}
	public void setBusId(long busId) {
		this.busId = busId;
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
	public String getStartingPoint() {
		return startingPoint;
	}
	public void setStartingPoint(String startingPoint) {
		this.startingPoint = startingPoint;
	}
	public String getEndingPoint() {
		return endingPoint;
	}
	public void setEndingPoint(String endingPoint) {
		this.endingPoint = endingPoint;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public int getBusType() {
		return busType;
	}
	public void setBusType(int busType) {
		this.busType = busType;
	}
	public String getIsPrimo() {
		return isPrimo;
	}
	public void setIsPrimo(String isPrimo) {
		this.isPrimo = isPrimo;
	}
	public String getBusRegistrationNumber() {
		return busRegistrationNumber;
	}
	public void setBusRegistrationNumber(String busRegistrationNumber) {
		this.busRegistrationNumber = busRegistrationNumber;
	}
	public int getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	
	public BusesData() {}
	
	public BusesData(long busId, String busName, String route,
			String startingPoint, String endingPoint, Time startTime,
			Time endTime, int busType, String isPrimo,
			String busRegistrationNumber, int ticketPrice, int totalSeats) {
		this.busId = busId;
		this.busName = busName;
		this.route = route;
		this.startingPoint = startingPoint;
		this.endingPoint = endingPoint;
		this.startTime = startTime;
		this.endTime = endTime;
		this.busType = busType;
		this.isPrimo = isPrimo;
		this.busRegistrationNumber = busRegistrationNumber;
		this.ticketPrice = ticketPrice;
		this.totalSeats = totalSeats;
	}
	
	@Override
	public String toString() {
		return "BusesData [busId=" + busId + ", busName=" + busName
				+ ", route=" + route + ", startingPoint=" + startingPoint
				+ ", endingPoint=" + endingPoint + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", busType=" + busType
				+ ", isPrimo=" + isPrimo + ", busRegistrationNumber="
				+ busRegistrationNumber + ", ticketPrice=" + ticketPrice
				+ ", totalSeats=" + totalSeats + "]";
	}
	
}
