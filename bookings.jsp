<%@page import="com.redbus.bean.output.AllBookings"%>
<%@page import="java.util.List"%>
<%@page import="com.redbus.bean.action.CommonActionBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //http 1.1
response.setHeader("Pragma", "no-cache"); //http 1.0
response.setDateHeader("Expires", 0); //proxies

Integer user;
int userType = 2;

if(session.getAttribute("email")!=null){
	user = (Integer)session.getAttribute("user_type");
	userType = user.intValue();
}

if(session.getAttribute("email")==null && userType!=1){
	response.sendRedirect("index.jsp");
	return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RedBus | Bookings</title>
<link rel="stylesheet" href="./css/style.css" />
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/script.js"></script>
</head>
<body>
	<div id="maincontainer">
		<jsp:include page="navbar.jsp"></jsp:include>
		
		<h4 id="booking-status-message" class="text-center text-green mt-10"></h4>
		
		<div id="bookings-data" class="m-10">
			<table class="table-collapse">
				<tr>
					<th class="table-heading">Sr No</th>
					<th class="table-heading">User Name</th>
					<th class="table-heading">Mobile Number</th>
					<th class="table-heading">Email</th>
					<th class="table-heading">Gender</th>
					<th class="table-heading">Bus Name</th>
					<th class="table-heading">Route</th>
					<th class="table-heading">Bus Registration No</th>
					<th class="table-heading">Bus Type</th>
					<th class="table-heading">Primo/Non Primo</th>
					<th class="table-heading">Pickup Point</th>
					<th class="table-heading">Drop Point</th>
					<th class="table-heading">Booking date</th>
					<th class="table-heading">Total Price</th>
					<th class="table-heading">Booked Seats</th>
					<th class="table-heading">Active/De-Active</th>
					<th class="table-heading">Created At</th>
				</tr>
				
				<%
					int num = 1;
				
					CommonActionBean bean = new CommonActionBean();
					List allBookings = (List) bean.getAllBookings();
					
					for(int i=0; i<allBookings.size(); i++){
						AllBookings bookings = (AllBookings)allBookings.get(i);
				%>
				<tr>
					<td class="table-data"><%= num %></td>
					<td class="table-data"><%= bookings.getName() %></td>
					<td class="table-data"><%= bookings.getMobileNumber() %></td>
					<td class="table-data"><%= bookings.getEmail() %></td>
					<td class="table-data"><%= bookings.getGender() %></td>
					<td class="table-data"><%= bookings.getBusName() %></td>
					<td class="table-data"><%= bookings.getRoute() %></td>
					<td class="table-data"><%= bookings.getBusRegistrationNo() %></td>
					<td class="table-data">
						<%
							if(bookings.getBusType()==0){
								out.println("Seater");
							}
							else{
								out.println("Sleeper");
							}
						%>
					</td>
					<td class="table-data">
						<%
						if(bookings.getIsPrimo().equals("Y")){
							out.println("<img class='m-5' src='./images/primobanner.png' height='60' width='90'>");
						}
						else{
							out.println("Non Primo");
						}
						%>
					</td>
					<td class="table-data"><%= bookings.getBoardingPoint() %></td>
					<td class="table-data"><%= bookings.getDroppingPoint() %></td>
					<td class="table-data"><%= bookings.getBookingDate() %></td>
					<td class="table-data"><%= bookings.getTotalPrice() %></td>
					<td class="table-data"><%= bookings.getBookedSeats() %></td>
					<td class="table-data">
						<select id="<%= bookings.getBookingId() %>" class="booking-status" name="booking_status" onchange="userFeatures.changeBookingStatus(this)">
							<option value="Y"
								<% 
								if(bookings.getIsActive().equals("Y")){
									out.println("selected");
								}
								%>
								>Active</option>
								
								
								<option value="N" 
								<% 
								if(bookings.getIsActive().equals("N")){
									out.println("selected");
								}
								%>
								>Cancelled</option>
						</select>
					</td>
					<td class="table-data"><%= bookings.getCreatedAt() %></td>
				</tr>
				<%
					num++;
					}
				%>
			</table>
		</div>
		
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>