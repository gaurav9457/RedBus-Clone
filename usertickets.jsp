<%@page import="com.redbus.bean.output.TicketData"%>
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

if(session.getAttribute("email")==null && userType!=0){
	response.sendRedirect("index.jsp");
	return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RedBus | User Tickets</title>
<link rel="stylesheet" href="./css/style.css" />
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/script.js"></script>
</head>
<body>
	<div id="maincontainer">
		<jsp:include page="navbar.jsp"></jsp:include>
		
		<div id="usertickets">
			<table class="table-collapse" id="usertickets-table">
				<tr>
					<th class="table-heading">Id</th>
					<th class="table-heading">Bus Name</th>
					<th class="table-heading">Bus Registration Number</th>
					<th class="table-heading">Boarding Point</th>
					<th class="table-heading">Dropping Point</th>
					<th class="table-heading">Price</th>
					<th class="table-heading">Booked Seats</th>
					<th class="table-heading">Bus Type</th>
					<th class="table-heading">Primo/Non Primo</th>
					<th class="table-heading">Ticket Status</th>
				</tr>
				
				<%
				CommonActionBean bean = new CommonActionBean();
				List userTickets = (List)bean.getUserTickets((String)session.getAttribute("email"));
				
				int num = 1;
				
				for(int i=0; i<userTickets.size(); i++){
					TicketData alltickets = (TicketData)userTickets.get(i);
					%>
					<tr>
						<td class="table-data"><%= num %></td>
						<td class="table-data"><%= alltickets.getBusName() %></td>
						<td class="table-data"><%= alltickets.getBusRegistrationNo() %></td>
						<td class="table-data"><%= alltickets.getBoardingPoint() %></td>
						<td class="table-data"><%= alltickets.getDestinationPoint() %></td>
						<td class="table-data"><%= alltickets.getTicketPrice() %></td>
						<td class="table-data"><%= alltickets.getSeats() %></td>
						<td class="table-data"><%
							if(alltickets.getBusType()==0){
								out.println("Seater");
							}
							else{
								out.println("Sleeper");
							}
						%></td>
						<td class="table-data"><%
							if(alltickets.getIsPrimo().equals("Y")){
								out.println("<img class='m-5' src='./images/primobanner.png' height='70' width='100'>");
							}
							else{
								out.println("Non Primo");
							}
						%></td>
						<td class="table-data"><%
							if(alltickets.getIsActive().equals("Y")){
								out.println("<button class='btn btn-red' id='" + alltickets.getBookingId() + "' onclick='userFeatures.cancelTicket(this)'>Cancel Ticket</button>");
							}
							else{
								out.println("<i>CANCELLED</i>");
							}
						%></td>
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