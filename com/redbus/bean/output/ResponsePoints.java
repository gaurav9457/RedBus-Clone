package com.redbus.bean.output;

import java.util.List;

public class ResponsePoints {
	String message;
	int status;
	List<BoardingPoints> boardingpoints;
	List<DroppingPoints> droppingpoints;
	
	public ResponsePoints(String message, int status,
			List<BoardingPoints> boardingpoints,
			List<DroppingPoints> droppingpoints) {
		this.message = message;
		this.status = status;
		this.boardingpoints = boardingpoints;
		this.droppingpoints = droppingpoints;
	}
}
