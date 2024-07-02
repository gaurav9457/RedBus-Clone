package com.redbus.helper;

import java.sql.Time;
import java.util.List;

import com.redbus.bean.output.AllBookings;
import com.redbus.bean.output.AllBuses;
import com.redbus.bean.output.BusesData;
import com.redbus.bean.output.TicketData;
import com.redbus.bean.pojo.BoardingPoint;
import com.redbus.bean.pojo.BookingsData;
import com.redbus.bean.pojo.Bus;
import com.redbus.bean.pojo.BusInfo;
import com.redbus.bean.pojo.BusesImage;
import com.redbus.bean.pojo.DroppingPoint;
import com.redbus.bean.pojo.User;
import com.redbus.impl.CommonImplementation;
import com.redbus.util.BCrypt;

public class CommonHelper {
	public String registerUser(User user,String password){
		String message = null;
		
		String name = user.getName();
		long mobileNumber = user.getMobileNumber();
		String email = user.getEmail();
		String cpassword = user.getCpassword();
		
		if(name!="" && mobileNumber!=0 && email!="" && password!="" && cpassword!=""){
			String strMobileNumber = Long.toString(mobileNumber);
			
			if(strMobileNumber.length()==10){
				if(password.equals(cpassword)){
		            String hashedPassword = BCrypt.hashpw(cpassword, BCrypt.gensalt(12));
					user.setCpassword(hashedPassword);
					CommonImplementation impl = new CommonImplementation();
					message = impl.registerUser(user);
				}
				else{
					message = "Password & Confirm Password Does Not Match!";
				}
			}
			else{
				message = "Enter A Proper Mobile Number!";
			}
		}
		else{
			message = "Null Fields Are Not Allowed!";
		}
		
		return message;
	}
	
	public String addBus(Bus bus,BusInfo busInfo,String email){
		String message = null;
		
		String busName = bus.getBusName();
		String startPoint = bus.getBusFrom();
		String endPoint = bus.getBusTo();
		String route = bus.getRoute();
		String busRegistrationNumber = busInfo.getBusRegistationNumber();
		String isPrimo = busInfo.getIsPrimo();
		Time startTime = bus.getStartTime();
		Time endTime = bus.getEndTime();
		
		if(busName!="" && startPoint!="" && endPoint!="" && route!="" && busRegistrationNumber!="" && isPrimo!="" && startTime!=null && endTime!=null){
			CommonImplementation impl = new CommonImplementation();
			message = impl.addBus(bus,busInfo,email);
		}
		else{
			message = "Null Fields Are Not Allowed!";
		}
		
		return message;
	}
	
	public List<BusesData> getBusesData(String startPoint,String endPoint,String date){
		CommonImplementation impl = new CommonImplementation();
		List<BusesData> busesData = impl.getBusesData(startPoint, endPoint, date);
		return busesData;
	}
	
	public List<User> getAllUsers(){
		CommonImplementation impl = new CommonImplementation();
		List<User> usersData = impl.getAllUsers();
		return usersData;
	}
	
	public List<AllBuses> getAllBuses(){
		CommonImplementation impl = new CommonImplementation();
		List<AllBuses> buses =  impl.getAllBuses();
		return buses;
	}
	
	public String uploadBusImage(BusesImage busimage, long busId, String email){
		String message = null;
		
		CommonImplementation impl = new CommonImplementation();
		
		if(busimage!=null){
			message = impl.uploadBusImage(busimage,busId,email);
		}
		else{
			message = "Path Not Found";
		}
		
		return message;
	}
	
	public String addBoardingPoint(BoardingPoint point,long busId,String email){
		String message = null;
		
		if(point!=null){
			CommonImplementation impl = new CommonImplementation();
			impl.addBoardingPoint(point,busId,email);
		}
		else{
			message = "Null Values Are Not Allowed!";
		}
		
		return message;
	}
	
	public String addDroppingPoint(DroppingPoint point,long busId,String email){
		String message = null;
		
		if(point!=null){
			CommonImplementation impl = new CommonImplementation();
			impl.addDroppingPoint(point,busId,email);
		}
		else{
			message = "Null Values Are Not Allowed!";
		}
		
		return message;
	}
	
	public String bookTicket(BookingsData booking,String email,long busId,long boardingPoint,long destinationPoint){
		String message = null;
		
		if(booking!=null){
			CommonImplementation impl = new CommonImplementation();
			message = impl.bookTicket(booking,email,busId,boardingPoint,destinationPoint);
		}
		else{
			message = "Null Values Are Not Allowed!";
		}
		
		return message;
	}
	
	public List<TicketData> getUserTickets(String email){
		CommonImplementation impl = new CommonImplementation();
		List<TicketData> userTickets = impl.getUserTickets(email);
		return userTickets;
	}
	
	public List<AllBookings> getAllBookings(){
		CommonImplementation impl = new CommonImplementation();
		List<AllBookings> allBookings = impl.getAllBookings();
		return allBookings;
	}
	
	public String updateUserProfile(String path,String sessionEmail,String firstName,String lastName,String email,long mobileNumber){
		String message = null;
		
		if(path!="" && sessionEmail!="" && firstName!="" && lastName!="" && email!="" && mobileNumber!=0){
			CommonImplementation impl = new CommonImplementation();
			message = impl.updateUserProfile(path,sessionEmail,firstName,lastName,email,mobileNumber);
		}
		else{
			message= "Null Values Are Not Allowed!";
		}
		
		return message;
	}
}
