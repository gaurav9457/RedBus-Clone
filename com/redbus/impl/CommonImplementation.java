package com.redbus.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.redbus.bean.output.AllBookings;
import com.redbus.bean.output.AllBuses;
import com.redbus.bean.output.BoardingPoints;
import com.redbus.bean.output.BusesData;
import com.redbus.bean.output.DroppingPoints;
import com.redbus.bean.output.ResponseBooking;
import com.redbus.bean.output.ResponseBusImages;
import com.redbus.bean.output.ResponsePoints;
import com.redbus.bean.output.TicketData;
import com.redbus.bean.pojo.BoardingPoint;
import com.redbus.bean.pojo.BookingsData;
import com.redbus.bean.pojo.Bus;
import com.redbus.bean.pojo.BusInfo;
import com.redbus.bean.pojo.BusesImage;
import com.redbus.bean.pojo.DroppingPoint;
import com.redbus.bean.pojo.User;
import com.redbus.dao.CommonDao;
import com.redbus.util.BCrypt;

public class CommonImplementation {
	public String registerUser(User user){
		String message = null;
		
		CommonDao dao = new CommonDao();
		
		long userId = dao.registerUser(user);
		
		if(userId!=0){
			message = "Registered Successfully!";
		}
		else{
			message = "Failed to Registered!";
		}
		
		return message;
	}
	
	public String LoginImplementation(User user){
		String message = null;
		
		String email = user.getEmail();
		String password = user.getCpassword();
		
		CommonDao dao = new CommonDao();
		User userData = dao.verifyUser(email);
		
		int DBusertype = 0;
		String DBis_active = null;
		String DBPassword = null;
		
		if(userData!=null){
			DBusertype = userData.getUserType();
			DBis_active = userData.getIsActive();
			DBPassword = userData.getCpassword();
			
			if(BCrypt.checkpw(password, DBPassword)){
				message = "success";
			}
			else{
				message = "failed";
			}
		}
		else{
			message = "User Not Found!";
		}
		
		String responseData = "{\"message\": \"" + message + "\", \"email\": \"" + email + "\", \"DBUserType\": " + DBusertype + ", \"DBIsActive\": \"" + DBis_active + "\"}";

        // Convert response data to JSON
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(responseData);
        
        return jsonResponse;
	}
	
	public String addBus(Bus bus,BusInfo busInfo,String email){
		String message = null;
		
		CommonDao dao = new CommonDao();
		
		User user = dao.verifyUser(email);
		
		bus.setCreatedBy(user.getUserId());
		
		long busId = dao.addBus(bus);
		
		Bus busData = dao.getBusById(busId);
		
		if(busId!=0){
			busInfo.setCreatedBy(user.getUserId());
			busInfo.setBus(busData);
		
			long busInfoId = dao.addBusInfo(busInfo);
			
			if(busInfoId!=0){
				message = "Added Successfully";
			}
			else{
				message = "Failed To Add Bus Info!";
			}
		}
		else{
			message = "Failed To Add Bus!";
		}
		
		return message;
	}
	
	public List<BusesData> getBusesData(String startPoint,String endPoint,String date){
		CommonDao dao = new CommonDao();
		List<BusesData> busesData = dao.getBusesData(startPoint,endPoint);
		return busesData;
	}
	
	public List<User> getAllUsers(){
		CommonDao dao = new CommonDao();
		List<User> usersData = dao.getAllUsers();
		return usersData;
	}
	
	public List<AllBuses> getAllBuses(){
		CommonDao dao = new CommonDao();
		List<AllBuses> buses =  dao.getAllBuses();
		return buses;
	}
	
	public String uploadBusImage(BusesImage busimage, long busId, String email){
		String message = null;
		
		CommonDao dao = new CommonDao();
		
		User user = dao.verifyUser(email);
		
		Bus bus = dao.getBusById(busId);
		
		busimage.setCreatedBy(user.getUserId());
		busimage.setBus(bus);
		
		long busImageId = dao.uploadBusImage(busimage);
		
		if(busImageId!=0){
			message = "Uploaded Successfully!";
		}
		else{
			message = "Failed to Upload!";
		}
		
		return message;
	}
	
	public String getAllBuses(long busId){
		String message = null;
		int status = 0;
		
		CommonDao dao = new CommonDao();
		List<BusesImage> busImages = dao.getBusImages(busId);
		
		if(busImages!=null){
			message = "success";
			status = 200;
		}
		else{
			message = "No Data Found!";
			status = 500;
		}
		
		ResponseBusImages responseData = new ResponseBusImages("success",200,busImages);

        // Convert response data to JSON
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(responseData);
        
		return jsonResponse;
	}
	
	public String addBoardingPoint(BoardingPoint point,long busId,String email){
		String message = null;
		
		CommonDao dao = new CommonDao();
		User user = dao.verifyUser(email);
		Bus bus = dao.getBusById(busId);
		
		point.setBus(bus);
		point.setCreatedBy(user.getUserId());
		
		long pointId = dao.addPoints(point);
		
		if(pointId!=0){
			message = "Added Successfully!";
		}
		else{
			message = "Failed To Add!";
		}
		
		return message;
	}
	
	public String addDroppingPoint(DroppingPoint point,long busId,String email){
		String message = null;
		
		CommonDao dao = new CommonDao();
		User user = dao.verifyUser(email);
		Bus bus = dao.getBusById(busId);
		
		point.setBus(bus);
		point.setCreatedBy(user.getUserId());
		
		long pointId = dao.addDroppingPoints(point);
		
		if(pointId!=0){
			message = "Added Successfully!";
		}
		else{
			message = "Failed To Add!";
		}
		
		return message;
	}
	
	public String getPoints(long busId){
		String message = null;
		int status = 0;
		
		CommonDao dao = new CommonDao();
		List<BoardingPoint> boardingPoints = dao.getBoardingPoints(busId);
		List<DroppingPoint> destinationPoints = dao.getDestinationPoints(busId);
		
		List<BoardingPoints> boardingpoints = new ArrayList<>();
		List<DroppingPoints> destinationpoints = new ArrayList<>();
		
		for (BoardingPoint point : boardingPoints) {
			BoardingPoints boardpoint = new BoardingPoints(point.getBoardingPoint(),point.getId());
			boardingpoints.add(boardpoint);
		}
		
		for (DroppingPoint point : destinationPoints) {
			DroppingPoints droppoint = new DroppingPoints(point.getDroppingPoint(), point.getId());
			destinationpoints.add(droppoint);
		}
		
		if(boardingPoints!=null && destinationPoints!=null){
			message = "success";
			status = 200;
		}
		else{
			message = "No Data Found!";
			status = 500;
		}
		
		ResponsePoints responseData = new ResponsePoints(message,status,boardingpoints,destinationpoints);
		
        // Convert response data to JSON
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(responseData);
		
		return jsonResponse;
	}
	
	public String bookTicket(BookingsData booking,String email,long busId,long boardingPoint,long destinationPoint){
		String message = null;
		
		CommonDao dao = new CommonDao();
		
		User user = dao.verifyUser(email);
		
		Bus bus = dao.getBusById(busId);
		
		DroppingPoint droppingpoint = dao.getDroppingPointById(destinationPoint);
		
		BoardingPoint boardingpoint = dao.getBoardingPointById(boardingPoint);
		
		booking.setCreatedBy(user.getUserId());
		booking.setUser(user);
		booking.setBus(bus);
		booking.setBoardingPoint(boardingpoint);
		booking.setDroppingPoint(droppingpoint);
		
		long bookingId = dao.bookTicket(booking);
		
		if(bookingId!=0){
			message = "Booked Successfully!";
		}
		else{
			message = "Failed To Book Ticket!";
		}
		
		return message;
	}
	
	public List<TicketData> getUserTickets(String email){
		CommonDao dao = new CommonDao();
		User user = dao.verifyUser(email);
		
		long userId = user.getUserId();
		
		List<TicketData> userTickets = dao.getUserTickets(userId);
		
		return userTickets;
	}
	
	public String cancelTicket(long bookingId){
		String message = null;
		int status = 0;
		
		CommonDao dao = new CommonDao();
		long num = dao.cancelTicket(bookingId);
		
		if(num!=0){
			message = "Cancelled Successfully!";
			status = 200;
		}
		else{
			message = "Failed To Cancel!";
			status = 500;
		}
		
		String responseData = "{\"message\": \"" + message + "\", \"status\": " + status + "}";

        // Convert response data to JSON
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(responseData);
		
		return jsonResponse;
	}
	
	public String changeBusStatus(long busId,String busStatus){
		String message = null;
		int status = 0;
		
		CommonDao dao = new CommonDao();
		long num = dao.changeBusStatus(busId,busStatus);
		
		if(num!=0){
			message = "Changed Successfully!";
			status = 200;
		}
		else{
			message = "Failed To Change Status!";
			status = 500;
		}
		
		String responseData = "{\"message\": \"" + message + "\", \"status\": " + status + "}";

        // Convert response data to JSON
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(responseData);
		
		return jsonResponse;
	}
	
	public String changeUserStatus(long userId,String userStatus){
		String message = null;
		int status = 0;
		
		CommonDao dao = new CommonDao();
		long num = dao.changeUserStatus(userId,userStatus);
		
		if(num!=0){
			message = "Changed Successfully!";
			status = 200;
		}
		else{
			message = "Failed To Change Status!";
			status = 500;
		}
		
		String responseData = "{\"message\": \"" + message + "\", \"status\": " + status + "}";

        // Convert response data to JSON
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(responseData);
		
		return jsonResponse;
	}
	
	public List<AllBookings> getAllBookings(){
		CommonDao dao = new CommonDao();
		
		List<AllBookings> allBookings = dao.getAllBookings();
		
		return allBookings;
	}
	
	public String changeBookingStatus(long bookingId,String bookingStatus){
		String message = null;
		int status = 0;
		
		CommonDao dao = new CommonDao();
		long num = dao.changeBookingStatus(bookingId,bookingStatus);
		
		if(num!=0){
			message = "Changed Successfully!";
			status = 200;
		}
		else{
			message = "Failed To Change Status!";
			status = 500;
		}
		
		String responseData = "{\"message\": \"" + message + "\", \"status\": " + status + "}";

        // Convert response data to JSON
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(responseData);
		
		return jsonResponse;
	}
	
	public String getBookingData(long busId,Date searchedDate){
		String message = null;
		int status = 0;
		
		CommonDao dao = new CommonDao();
		
		List<BookingsData> bookings = dao.getBookingData(busId,searchedDate);
		
		if(bookings!=null){
			message = "Data Found!";
			status = 200;
		}
		else{
			message = "No Data Found!";
			status = 500;
		}
		
		ResponseBooking responseData = new ResponseBooking(message, status, bookings);
		
		// Convert response data to JSON
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(responseData);
		
		return jsonResponse;
	}
	
	public String updateUserProfile(String path,String sessionEmail,String firstName,String lastName,String email,long mobileNumber){
		String message = null;
		
		CommonDao dao = new CommonDao();
		User user = dao.verifyUser(sessionEmail);
		
		long userId = user.getUserId();
		
		if(user!=null){
			long nums = dao.updateUserProfile(userId,path,firstName,lastName,email,mobileNumber);
			
			if(nums!=0){
				message = "Updated Successfully!";
			}
			else{
				message = "Failed To Update!";
			}
		}
		else{
			message = "User Not Found!";
		}
		
		return message;
	}
}
