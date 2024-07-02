package com.redbus.bean.output;

import java.util.List;

import com.redbus.bean.pojo.BookingsData;

public class ResponseBooking {
	String message;
	int status;
	List<BookingsData> bookings;
	
	public ResponseBooking(String message, int status,
			List<BookingsData> bookings) {
		this.message = message;
		this.status = status;
		this.bookings = bookings;
	}
}
