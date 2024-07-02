<%@page import="com.redbus.bean.pojo.User"%>
<%@page import="com.redbus.dao.CommonDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.redbus.bean.output.BusesData"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RedBus | Show Buses</title>
<link rel="stylesheet" href="./css/style.css" />
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/script.js"></script>
</head>
<body>
	<div id="maincontainer">
		<jsp:include page="navbar.jsp"></jsp:include>
		
		<div id="container">
			<div class="sidebar f-left">
				<img style="margin-left:23px;margin-right:20px;margin-top:15px; margin-bottom:15px;border-radius:10px;" alt="sidebanner" src="./images/sidebanner.png" height="600" width="250">
			</div>
			<div class="mainarea f-left">
				<div class="cards">
					<%
					User user = null;
					if(session.getAttribute("email")!=null){
						CommonDao dao = new CommonDao();
						user = dao.verifyUser((String)session.getAttribute("email"));
					}
					
					List allOrders = (List)session.getAttribute("busesdata");
					
					if(allOrders.size()==0){
						out.println("<h1 class='text-center m-10'>No Buses Available!</h1>");
					}
					
					for(int i=0; i<allOrders.size(); i++){
						BusesData buses = (BusesData) allOrders.get(i);
					%>
					<div class="card">
						<% 
							if(buses.getIsPrimo().equals("Y")){
								%>
								<img alt="primo" class="primo-img" src="./images/primobanner.png" height="100" width="180">
								<%
							}
						%>
						<div class="f-left card-content">
							<p class="text-center m-5">Travels Name</p>
							<div class="small-heading"></div>
							
							<h2 class="text-center m-5"><%= buses.getBusName() %></h2>
							<p class="p-10 text-center">
								<%
									if(buses.getBusType()==0){
										out.println("Seater");
									}
									else{
										out.println("Sleeper");
									}
								%>(2+1)
							</p>
						</div>
						
						<div class="f-left card-content">
							<p class="text-center m-5">Departure</p>
							<div class="small-heading"></div>
							<h2 class="text-center mt-5"><%
							  DateFormat outputFormat = new SimpleDateFormat("HH:mm");
							  String startTime = outputFormat.format(buses.getStartTime());
							  out.println(startTime);
							 %></h2>
							<p class="p-10 text-center"><%= buses.getStartingPoint() %></p>
						</div>
						
						<div class="f-left card-content">
							<p class="text-center m-5">Bus Number</p>
							<div class="small-heading"></div>
							<h2 class="text-center mt-5"><%= buses.getBusRegistrationNumber() %></h2>
							
							<p class="text-center m-5">Route</p>
							<div class="small-heading"></div>
							<h3 class="text-center mt-5"><%= buses.getRoute() %></h3>
						</div>
						
						<div class="f-left card-content">
							<p class="text-center m-5">Arrival</p>
							<div class="small-heading"></div>
							<h2 class="text-center mt-5"><%
							  String endTime = outputFormat.format(buses.getEndTime());
							  out.println(endTime);
							 %></h2>
							<p class="p-10 text-center"><%= buses.getEndingPoint() %></p>
						</div>
						
						<div class="f-left card-content">
							<p class="text-center m-5">Price</p>
							<div class="small-heading"></div>
							<h2 class="text-center mt-5"><%= buses.getTicketPrice() %>/-</h2>
						</div>
						
						<div style="clear:both"></div>
						
						<div id="card-bottom">
							<div class="f-right">
								<button class="view-seat btn2 btn-red mr-10" id="<%= buses.getBusId() %>" data-currentgender="<% if(session.getAttribute("email")!=null){out.print(user.getGender());} %>" data-seats="<%= buses.getTotalSeats() %>" data-date="<%= session.getAttribute("searcheddate") %>" onclick="userFeatures.showSeats(this)">View Seats</button>
							</div>
							<div class="f-right">
								<button class="btn2 btn-red mr-10" id="<%= buses.getBusId() %>" onclick="userFeatures.showImages(this)">Show Images</button>
							</div>
							<div style="clear:both"></div>
						</div>
					</div>
					
					<div id="seats-<%= buses.getBusId() %>" class="seats">
						<div class="mb-10">
							<div class="f-left ml-10">
								<div class="f-left"><p>Booked By Ladies :</p></div>
								<div class="f-left ml-10" style="height:15px;width:15px;background-color:#FD73F7;"></div>
								<div style="clear:both;"></div>
							</div>
							<div class="f-left ml-10">
								<div class="f-left"><p>Booked By Men :</p></div>
								<div class="f-left" style="margin-left:15px;height:15px;width:15px;background-color:#4C7CFC;"></div>
								<div style="clear:both;"></div>
							</div>
							<div class="f-left ml-10">
								<div class="f-left"><p>Available Seats :</p></div>
								<div class="f-left" style="margin-left:15px;height:15px;width:15px;background-color:white;"></div>
								<div style="clear:both;"></div>
							</div>
							<div class="f-left ml-10">
								<div class="f-left"><p><b>* Third Row From Steering Are Single Seats</b></p></div>
							</div>
							<div style="clear:both"></div>
						</div>
						
						<img class="close_img" onclick="userFeatures.hideSeats(this)" id="<%= buses.getBusId() %>" data-seats="<%= buses.getTotalSeats() %>" src="./images/close.png" alt="close_icon">
						<div class="f-left" style="margin-top:50px;"><img alt="steering-img" src="./images/car_steering.png" height="40" width="40"></div>
						<div id="lower-seats-<%= buses.getBusId() %>" class="f-left p-5">
							
						</div>
					
						<div id="upper-seats-<%= buses.getBusId() %>" class="f-left p-5">
							
						</div>
						<div style="clear:both"></div>
						
						<button class="btn btn-red f-right" id="<%= buses.getBusId() %>" data-price="<%= buses.getTicketPrice() %>" onclick="
						<% 
							if(session.getAttribute("email")!=null){
								out.println("userFeatures.loadPoints(this)");
							}
							else{
								out.println("userFeatures.showLoginAlert()");
							}
						%>
						">Book Ticket</button>
						<div style="clear:both"></div>
					</div>
					<%
					}
				%>
				</div>
			</div>
			<div style="clear:both"></div>
		</div>
		
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
	
	<div id="login-transparent-screen">
		<div class="login_form">
			<img id="login-close-img" class="close_img" src="./images/close.png" alt="close_icon">
			<div class="login_side1">
				<img class="login_img" src="./images/login_img.png" alt="login_image" />
			</div>
			<div class="login_side2">
				<div class="wrapper">
					<form id="login_form">
						<h1 class="login_heading">Login Form</h1>
						<h4 id="error-message"></h4>
						<div class="form-element">
							<input type="text" id="login_email" class="login_form_input" placeholder="Email..." maxlength="255">
							<span id="email_error"></span>
						</div>
						<div class="form-element">
							<input type="password" id="login_password" class="login_form_input" placeholder="Password...">
						</div>
						<input type="submit" onclick="Authentication.validateUser()" class="login_btn" value="LOGIN">
					</form>
					<p class="new_user">Not a User? <a href="#" id="open-register">Register</a></p>
				</div>
			</div>
			<div style="clear:both;"></div>
		</div>
	</div>
	
	<div id="register-transparent-screen">
		<div class="subcontainer">
			<img class="close_img" id="register-close-img" src="./images/close.png" alt="close_icon">
			<div class="reg_left_side">
				<form action="controller" method="post" class="reg_form" id="reg_form">
					<h1 class="reg_heading">User Form</h1>
					<div class="form_content">
						<div class="form-element">
							<label for="firstname" class="label">First Name <span class="required">*</span></label>
							<input type="text" name="firstname" class="form_input" id="firstname" placeholder="Enter First Name" maxlength="15">
							<span class="error" id="firstname_error"></span>
						</div>
						
						<div class="form-element">
							<label for="lastname" class="label">Last Name <span class="required">*</span></label>
							<input type="text" name="lastname" class="form_input" id="lastname" placeholder="Enter Last Name" maxlength="15">
							<span class="error" id="lastname_error"></span>
						</div>

						<div class="form-element">
							<label for="mobile_number" class="label">Mobile Number <span class="required">*</span></label>
							<input type="text" class="form_input" name="mobile_number" id="mobile_number" placeholder="Enter Mobile Number" maxlength="10">
							<span class="error" id="mobile_number_error"></span>
						</div>

						<div class="form-element">
							<label for="email" class="label">Email <span class="required">*</span></label>
							<input type="text" class="form_input" name="email" id="email" placeholder="example@gmail.com">
							<span class="error" id="email_error"></span>
						</div>
						
						<div class="form-element">
							<label class="label gender">Gender <span class="required">*</span> :</label>
							<label for="male" class="label">Male</label>
							<input type="radio" class="radio-btn" name="gender" id="male" value="male">
							<label for="female" class="label">Female</label>
							<input type="radio" class="radio-btn" name="gender" id="female" value="female">
							<span class="error" id="gender_error"></span>
						</div>
						
						<div class="form-element">
							<label for="password" class="label">Password <span class="required">*</span></label>
							<input type="password" class="form_input" name="password" id="password" placeholder="Password" maxlength="15">
							<span class="error" id="password_error"></span>
						</div>

						<div class="form-element">
							<label for="cpassword" class="label">Confirm Password <span class="required">*</span></label>
							<input type="password" class="form_input" name="cpassword" id="cpassword" placeholder="Confirm Password" maxlength="15">
							<span class="error" id="cpassword_error"></span>
						</div>
						<input type="submit" value="Submit" class="submit_btn" id="submit_btn">
						<p class="old_user">Already Registered? <a href="#" id="open_login_form">Login</a></p>
					</div>
					<input type="hidden" name="url" value="/register">
				</form>
			</div>
			<div class="reg_right_side">
				<img class="registration_img" src="./images/registration.png" alt="registration_image" />
			</div>
			<div style="clear:both;"></div>
		</div>
	</div>

	<div id="error-transparent-screen">
		<div class="alert_popup">
			<div class="popupwrapper">
				<div class="popup_content">
					<img class="check_img" src="" alt="check_image">
					<h1 class="alert_data"></h1>
				</div>
			</div>
		</div>
	</div>
	
	<div id="busesphotos-transaparent-screen">
		<img class="close_img1" id="bus-close-img" src="./images/close.png" alt="close_icon">
		<img class="bus-image" id="bus-photo" alt="bus-image" src="" height="450" width="900">
	</div>
	
	<div id="bookseat-transparent-screen">
		<img class="close_img1" id="bookseat-close-img" src="./images/close.png" alt="close_icon">
		<div class="bookseat-area bg-red">
			<form action="controller" method="post" id="bookseat-form">
				<h1 class="text-center text-white">Book Seat</h1>
				<div class="form-element">
					<label for="" class="label d-block mt-10 mb-10">Select Boarding Point</label>
					<select id="boardingpoint" class="select" name="boardingpoint">
					</select>
				</div>
				<div class="form-element">
					<label for="" class="label d-block mt-10 mb-10">Select Destination Point</label>
					<select id="destinationpoint" class="select" name="destinationpoint">
					</select>
				</div>
				<div class="form-element">
					<label for="gender" class="label d-block mt-10 mb-10">Gender</label>
					<label class="label">Male</label>
					<input type="radio" name="gender" value="male">
					<label class="label ml-10">Female</label>
					<input type="radio" name="gender" value="female">
				</div>
				<div class="form-element">
					<label for="" class="label d-block mt-10 mb-10">Total Price</label>
					<input type="text" id="totalprice" class="input bg-white" name="totalprice" disabled>
				</div>
				<div class="form-element">
					<label for="" class="label d-block mt-10 mb-10">Seats</label>
					<input type="text" id="bookedseats" class="input bg-white" name="seats" disabled>
				</div>
				<div class="form-element">
					<label for="" class="label d-block mt-10 mb-10">Confirm Date</label>
					<input type="date" id="searchdate" class="input bg-white" name="bookingdate">
				</div>
				<input type="hidden" id="sendtotalprice" name="totalprice">
				<input type="hidden" id="sendseats" name="seats">
				<input type="hidden" name="url" value="/bookticket">
				<input type="hidden" id="busid2" name="bus_id">
				<input type="hidden" name="email" value="<%= session.getAttribute("email") %>">
				<div class="form-element">
					<input type="submit" class="btn btn-white" value="Book Ticket">
				</div>
			</form>
		</div>
	</div>
	
	<%
		if((String)session.getAttribute("message")!=null){
	%>
	<div id="transparent-screen-error">
		<div class="alert_popup">
			<div class="popupwrapper">
				<div class="popup_content">
					<img class="check_img" src="./images/message.png" alt="check_image">
					<h1 class="alert_data"><%= session.getAttribute("message")%></h1>
				</div>
			</div>
		</div>
	</div>
	<%
		session.removeAttribute("message");
		}
	%>
</body>
</html>