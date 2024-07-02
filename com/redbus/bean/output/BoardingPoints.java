package com.redbus.bean.output;

public class BoardingPoints {
	private String boardingPoint;
	private long id;
	
	public String getBoardingPoint() {
		return boardingPoint;
	}
	public void setBoardingPoint(String boardingPoint) {
		this.boardingPoint = boardingPoint;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public BoardingPoints() {}
	
	public BoardingPoints(String boardingPoint, long id) {
		this.boardingPoint = boardingPoint;
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "BoardingPoints [boardingPoint=" + boardingPoint + ", id=" + id
				+ "]";
	}
}
