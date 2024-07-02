package com.redbus.bean.output;

public class DroppingPoints {
	private String droppingPoint;
	private long id;
	
	public String getDroppingPoint() {
		return droppingPoint;
	}
	public void setDroppingPoint(String droppingPoint) {
		this.droppingPoint = droppingPoint;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public DroppingPoints() {}
	
	public DroppingPoints(String droppingPoint, long id) {
		this.droppingPoint = droppingPoint;
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "DroppingPoints [droppingPoint=" + droppingPoint + ", id=" + id
				+ "]";
	}
}
