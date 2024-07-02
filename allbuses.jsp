<%@page import="com.redbus.bean.output.AllBuses"%>
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
<title>RedBus | Buses</title>
<link rel="stylesheet" href="./css/style.css" />
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/script.js"></script>
</head>
<body>
	<div id="maincontainer">
		<jsp:include page="navbar.jsp"></jsp:include>
		
		<h4 id="bus-status-message" class="text-green text-center mt-10"></h4>
		
		<div id="allusers">
			<table class="table-collapse" id="allusers-table">
				<tr>
					<th class="table-heading">ID</th>
					<th class="table-heading">Bus Name</th>
					<th class="table-heading">Bus Route</th>
					<th class="table-heading">Start Point</th>
					<th class="table-heading">End Point</th>
					<th class="table-heading">Start Time</th>
					<th class="table-heading">End Time</th>
					<th class="table-heading">Bus Type</th>
					<th class="table-heading">Bus Registration No</th>
					<th class="table-heading">isPrimo</th>
					<th class="table-heading">Ticket Price</th>
					<th class="table-heading">Total Seats</th>
					<th class="table-heading">Active/De-Active</th>
					<th class="table-heading">Created At</th>
					<th class="table-heading">Add Boarding Point</th>
					<th class="table-heading">Add Destination Point</th>
					<th class="table-heading">Add Images</th>
				</tr>
				
				<%
					int num = 1;
				
					CommonActionBean bean = new CommonActionBean();
					List allbuses = (List)bean.getAllBuses();
					
					for(int i=0; i<allbuses.size(); i++){
						AllBuses allBuses = (AllBuses)allbuses.get(i);
				%>
					<tr>
						<td class="table-data"><%= num %></td>
						<td class="table-data"><%= allBuses.getBusName() %></td>
						<td class="table-data"><%= allBuses.getRoute() %></td>
						<td class="table-data"><%= allBuses.getStartingPoint() %></td>
						<td class="table-data"><%= allBuses.getEndingPoint() %></td>
						<td class="table-data"><%= allBuses.getStartTime() %></td>
						<td class="table-data"><%= allBuses.getEndTime() %></td>
						<td class="table-data"><%
							if(allBuses.getBusType()==0){
								out.println("Seater");
							}
							else{
								out.println("Sleeper");
							}
						%></td>
						<td class="table-data"><%= allBuses.getBusRegistrationNumber() %></td>
						<td class="table-data">
							<%
								if(allBuses.getIsPrimo().equals("Y")){
									out.println("Primo");
								}
								else{
									out.println("Not Primo");
								}
							%>
						</td>
						<td class="table-data"><%= allBuses.getTicketPrice() %></td>
						<td class="table-data"><%= allBuses.getTotalSeats() %></td>
						<td class="table-data">
							<select id="<%= allBuses.getBusId() %>" class="bus-status" name="bus_status" onchange="userFeatures.changeBusStatus(this)">
								<option value="Y"
									<% 
									if(allBuses.getIsActive().equals("Y")){
										out.println("selected");
									}
									%>
								>Active</option>
								
								
								<option value="N" 
								<% 
								if(allBuses.getIsActive().equals("N")){
									out.println("selected");
								}
								%>
								>De-Active</option>
							</select>
						</td>
						<td class="table-data"><%= allBuses.getCreatedAt() %></td>
						<td class="table-data"><button class="btn btn-red" id="<%= allBuses.getBusId() %>" onclick="userFeatures.showBoardingPointForm(this)">Add Boarding Points</button></td>
						<td class="table-data"><button class="btn btn-red" id="<%= allBuses.getBusId() %>" onclick="userFeatures.showDestinationPointForm(this)">Add Destination Points</button></td>
						<td class="table-data">
							<form action="uploadbusimage.jsp" method="post" enctype="multipart/form-data">
								<input type="hidden" name="email" value="<%= session.getAttribute("email") %>">
								<input type="hidden" name="bus_id" value="<%= allBuses.getBusId() %>">
								<input type="file" name="busimage" class="btn btn-red">
								<input type="submit" class="btn btn-red">
							</form>
						</td>
					</tr>
				<%
					num++;
					}
				%>
			</table>
		</div>
		
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
	
	<div id="boarding-point-transparent-screen">
		<img class="close_img1" id="boarding-close-img" src="./images/close.png" alt="close_icon">
		<div class="form-area bg-red">
			<form action="controller" method="post" id="boardingpoints">
				<div class="form-element">
					<label for="boardingpoint" class="label">Boarding Point</label>
					<input type="text" name="boardingpoint" class="form_input">
				</div>
				<div class="form-element">
					<input type="submit" class="btn btn-white">
				</div>
				<input type="hidden" name="url" value="/boardingpoint">
				<input type="hidden" id="busid" name="bus_id">
				<input type="hidden" name="email" value="<%= session.getAttribute("email") %>">
			</form>
		</div>
	</div>
	
	<div id="destination-point-transparent-screen">
		<img class="close_img1" id="destination-close-img" src="./images/close.png" alt="close_icon">
		<div class="form-area bg-red">
			<form action="controller" method="post" id="destinationpoints">
				<div class="form-element">
					<label for="destinationpoint" class="label">Destination Point</label>
					<input type="text" name="destinationpoint" class="form_input">
				</div>
				<div class="form-element">
					<input type="submit" class="btn btn-white">
				</div>
				<input type="hidden" name="url" value="/destinationpoint">
				<input type="hidden" id="busid1" name="bus_id">
				<input type="hidden" name="email" value="<%= session.getAttribute("email") %>">
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