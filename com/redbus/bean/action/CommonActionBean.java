package com.redbus.bean.action;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
import com.redbus.helper.CommonHelper;

public class CommonActionBean {
	public String registerUser(HttpServletRequest req, HttpServletResponse resp){
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		String mobileNumber = req.getParameter("mobile_number");
		String email = req.getParameter("email");
		String gender = req.getParameter("gender");
		String password = req.getParameter("password");
		String cpassword = req.getParameter("cpassword");
		
		User user = new User();
		user.setName(firstName+" "+lastName);
		user.setMobileNumber(Long.parseLong(mobileNumber));
		user.setEmail(email);
		user.setGender(gender);
		user.setCpassword(cpassword);
		user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		user.setUserType(0);
		user.setIsActive("Y");
		
		CommonHelper helper = new CommonHelper();
		String message = helper.registerUser(user,password);
		
		return message;
	}
	
	public String addBus(HttpServletRequest req, HttpServletResponse resp){
		String startPoint = req.getParameter("startingpoint");
		String endPoint = req.getParameter("endpoint");
		String route = req.getParameter("route");
		String busRegistrationNumber = req.getParameter("busregistrationnumber");
		int busType = Integer.parseInt(req.getParameter("bustype"));
		String isPrimo = req.getParameter("isprimo");
		int ticketPrice = Integer.parseInt(req.getParameter("ticketprice"));
		int totalSeats = Integer.parseInt(req.getParameter("totalseats"));
		String busName = req.getParameter("busname");
		String email = req.getParameter("email");
		
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
		
		Date parsedStartDate = null;
		try {
			parsedStartDate = dateFormat.parse(req.getParameter("starttime"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Time startTime = new Time(parsedStartDate.getTime());
		
		Date parsedEndDate = null;
		try {
			parsedEndDate = dateFormat.parse(req.getParameter("endtime"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Time endTime = new Time(parsedEndDate.getTime());
		
		Bus bus = new Bus();
		bus.setBusName(busName);
		bus.setBusFrom(startPoint);
		bus.setBusTo(endPoint);
		bus.setRoute(route);
		bus.setStartTime(startTime);
		bus.setEndTime(endTime);
		bus.setIsActive("Y");
		bus.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		
		BusInfo busInfo = new BusInfo();
		busInfo.setBusType(busType);
		busInfo.setBusRegistationNumber(busRegistrationNumber);
		busInfo.setIsPrimo(isPrimo);
		busInfo.setTicketPrice(ticketPrice);
		busInfo.setTotalSeats(totalSeats);
		busInfo.setIsActive("Y");
		busInfo.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		
		CommonHelper helper = new CommonHelper();
		String message = helper.addBus(bus,busInfo,email); 
		return message;
	}
	
	public List<BusesData> getBusesData(HttpServletRequest req, HttpServletResponse resp){
		String startPoint = req.getParameter("departure");
		String endPoint = req.getParameter("destination");
		String date = req.getParameter("date");
		
		CommonHelper helper = new CommonHelper();
		List<BusesData> busesData = helper.getBusesData(startPoint,endPoint,date); 
		return busesData;
	}
	
	public List<User> getAllUsers(){
		CommonHelper helper = new CommonHelper();
		List<User> usersData = helper.getAllUsers(); 
		return usersData;
	}
	
	public List<AllBuses> getAllBuses(){
		CommonHelper helper = new CommonHelper();
		List<AllBuses> buses =  helper.getAllBuses();
		return buses;
	}
	
	public String uploadBusImage(HttpServletRequest req, HttpServletResponse resp){
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
	    
		String path="";
		long busId = 0;
		String email = null;
	    
	    if (isMultipart) {
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // a new file upload handle here
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                // Parse the request
                List<FileItem> items = upload.parseRequest(req);

                for (FileItem item : items) {
                	 if (item.isFormField()) {
                		 String fieldName = item.getFieldName();
                		 String fieldValue = item.getString("UTF-8"); //encoding
                		 
                		 if ("bus_id".equals(fieldName)) {
                			 busId = Long.parseLong(fieldValue);
                		 }
                		 else if("email".equals(fieldName)){
                			 email = fieldValue;
                		 }
                     }
                	 else{
                		 String fileName = new File(item.getName()).getName();
                         String uploadPath = "D:\\dev\\projects\\RedBusApplication\\WebContent\\images\\bus_images\\" + fileName;
                         File uploadedFile = new File(uploadPath);
                         item.write(uploadedFile);
                         path = "./images/bus_images/"+fileName;
                	 }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	    
	    BusesImage busimage = new BusesImage();
	    
	    busimage.setImagePath(path);
	    busimage.setCreatedAt(new Timestamp(System.currentTimeMillis()));
	    busimage.setIsActive("Y");
	    
	    CommonHelper helper = new CommonHelper();
	    String message = helper.uploadBusImage(busimage,busId,email);
	    
		return message;
	}
	
	public String addBoardingPoint(HttpServletRequest req, HttpServletResponse resp){
		String boardingPoint = req.getParameter("boardingpoint");
		long busId = Long.parseLong(req.getParameter("bus_id"));
		String email = req.getParameter("email");
		
		BoardingPoint point = new BoardingPoint();
		
		point.setBoardingPoint(boardingPoint);
		point.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		point.setIsActive("Y");
		
		CommonHelper helper = new CommonHelper();
	    String message = helper.addBoardingPoint(point,busId,email);
	    
		return message;
	}
	
	public String addDestinationPoint(HttpServletRequest req, HttpServletResponse resp){
		String droppingPoint = req.getParameter("destinationpoint");
		long busId = Long.parseLong(req.getParameter("bus_id"));
		String email = req.getParameter("email");
		
		DroppingPoint point = new DroppingPoint();
		
		point.setDroppingPoint(droppingPoint);
		point.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		point.setIsActive("Y");
		
		System.out.println(point);
		
		CommonHelper helper = new CommonHelper();
	    String message = helper.addDroppingPoint(point,busId,email);
	    
		return message;
	}
	
	public String bookTicket(HttpServletRequest req, HttpServletResponse resp){
		String email = req.getParameter("email");
		long busId = Long.parseLong(req.getParameter("bus_id"));
		long boardingPoint = Long.parseLong(req.getParameter("boardingpoint"));
		long destinationPoint = Long.parseLong(req.getParameter("destinationpoint"));
		String gender = req.getParameter("gender");
		int totalPrice = Integer.parseInt(req.getParameter("totalprice"));
		String bookedSeats = req.getParameter("seats");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Date parsedDate=null;
		try {
			parsedDate = dateFormat.parse(req.getParameter("bookingdate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		java.sql.Date bookingDate = new java.sql.Date(parsedDate.getTime());
		
		BookingsData booking = new BookingsData();
		
		booking.setGender(gender);
		booking.setBookingDate(bookingDate);
		booking.setTotalPrice(totalPrice);
		booking.setSeats(bookedSeats);
		booking.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		booking.setIsActive("Y");
		
		CommonHelper helper = new CommonHelper();
		String message = helper.bookTicket(booking,email,busId,boardingPoint,destinationPoint);
		
		return message;
	}
	
	public List<TicketData> getUserTickets(String email){
		CommonHelper helper = new CommonHelper();
		List<TicketData> userTickets = helper.getUserTickets(email);
		return userTickets;
	}
	
	public List<AllBookings> getAllBookings(){
		CommonHelper helper = new CommonHelper();
		List<AllBookings> allBookings = helper.getAllBookings();
		return allBookings;
	}
	
	public String updateUserProfile(HttpServletRequest req, HttpServletResponse resp){
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
	    
		String path=null;
		String sessionEmail = null;
		String firstName = null;
		String lastName = null;
		long mobileNumber = 0;
		String email = null;
	    
	    if (isMultipart) {
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // a new file upload handle here
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                // Parse the request
                List<FileItem> items = upload.parseRequest(req);

                for (FileItem item : items) {
                	 if (item.isFormField()) {
                		 String fieldName = item.getFieldName();
                		 String fieldValue = item.getString("UTF-8"); //encoding
                		 
                		 if ("session_email".equals(fieldName)) {
                			 sessionEmail = fieldValue;
                		 }
                		 else if("email".equals(fieldName)){
                			 email = fieldValue;
                		 }
                		 else if("firstname".equals(fieldName)){
                			 firstName = fieldValue;
                		 }
                		 else if("lastname".equals(fieldName)){
                			 lastName = fieldValue;
                		 }
                		 else if("mobile_number".equals(fieldName)){
                			 mobileNumber = Long.parseLong(fieldValue);
                		 }
                     }
                	 else{
                		 String fileName = new File(item.getName()).getName();
                         String uploadPath = "D:\\dev\\projects\\RedBusApplication\\WebContent\\images\\user_profiles\\" + fileName;
                         File uploadedFile = new File(uploadPath);
                         item.write(uploadedFile);
                         path = "./images/user_profiles/"+fileName;
                	 }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
 
	    CommonHelper helper = new CommonHelper();
	    String message = helper.updateUserProfile(path,sessionEmail,firstName,lastName,email,mobileNumber);
	    
		return message;
	}
}
