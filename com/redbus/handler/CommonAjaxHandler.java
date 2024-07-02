package com.redbus.handler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.redbus.bean.pojo.User;
import com.redbus.impl.CommonImplementation;

public class CommonAjaxHandler {
	public String login(HttpServletRequest req, HttpServletResponse resp){
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		User user = new User();
		
		user.setEmail(email);
		user.setCpassword(password);;
		
		CommonImplementation impl = new CommonImplementation();
		String jsonResponse = impl.LoginImplementation(user);
		
		return jsonResponse;
	}
	
	public String getBusImages(HttpServletRequest req, HttpServletResponse resp){
		long busId = Long.parseLong(req.getParameter("bus_id"));
		
		CommonImplementation impl = new CommonImplementation();
		String jsonResponse = impl.getAllBuses(busId);
		
		return jsonResponse;
	}
	
	public String getPoints(HttpServletRequest req, HttpServletResponse resp){
		long busId = Long.parseLong(req.getParameter("bus_id"));
		
		CommonImplementation impl = new CommonImplementation();
		String jsonResponse = impl.getPoints(busId);
		
		return jsonResponse;
	}
	
	public String cancelTicket(HttpServletRequest req, HttpServletResponse resp){
		long bookingId = Long.parseLong(req.getParameter("booking_id"));
		
		CommonImplementation impl = new CommonImplementation();
		String jsonResponse = impl.cancelTicket(bookingId);
		
		return jsonResponse;
	}
	
	public String changeBusStatus(HttpServletRequest req, HttpServletResponse resp){
		long busId = Long.parseLong(req.getParameter("bus_id"));
		String busStatus = req.getParameter("bus_status");
		
		CommonImplementation impl = new CommonImplementation();
		String jsonResponse = impl.changeBusStatus(busId,busStatus);
		
		return jsonResponse;
	}
	
	public String changeUserStatus(HttpServletRequest req, HttpServletResponse resp){
		long userId = Long.parseLong(req.getParameter("user_id"));
		String userStatus = req.getParameter("user_status");
		
		CommonImplementation impl = new CommonImplementation();
		String jsonResponse = impl.changeUserStatus(userId,userStatus);
		
		return jsonResponse;
	}
	
	public String changeBookingStatus(HttpServletRequest req, HttpServletResponse resp){
		long bookingId = Long.parseLong(req.getParameter("booking_id"));
		String bookingStatus = req.getParameter("booking_status");
		
		CommonImplementation impl = new CommonImplementation();
		String jsonResponse = impl.changeBookingStatus(bookingId,bookingStatus);
		
		return jsonResponse;
	}
	
	public String getBookingData(HttpServletRequest req, HttpServletResponse resp){
		long busId = Long.parseLong(req.getParameter("bus_id"));
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date parsedDate=null;
		try {
			parsedDate = dateFormat.parse(req.getParameter("searched_date"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		java.sql.Date searchedDate = new java.sql.Date(parsedDate.getTime());
		
		CommonImplementation impl = new CommonImplementation();
		String jsonResponse = impl.getBookingData(busId,searchedDate);
		
		return jsonResponse;
	}
}
