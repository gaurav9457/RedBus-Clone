<%@page import="com.redbus.dao.CommonDao"%>
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
<title>RedBus | Admin</title>
<link rel="stylesheet" href="./css/style.css" />
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/script.js"></script>
</head>
<body>
	<div id="maincontainer">
		<jsp:include page="navbar.jsp"></jsp:include>
		
		<%
			CommonDao dao = new CommonDao();
			
			long totalActiveUsers = dao.getTotalActiveUsersCount();
			long totalActiveBuses = dao.getTotalActiveBusesCount();
			long totalActiveBookings = dao.getTotalActiveBookingsCount();
			long totalCancelledBookings = dao.getTotalCancelledBookingsCount();
		%>
		
		<div id="dashboard">
			<h1 class="text-red" style="text-align:center;margin-bottom:20px;">DASHBOARD</h1>
			<div class="dashboard-card">
				<div class="dashboard-data">
					<h2 class="dashboard-card-heading">Total Active Users</h2>
					<h1 class="count"><%= totalActiveUsers %></h1>
				</div>
			</div>
			<div class="dashboard-card">
				<div class="dashboard-data">
					<h2 class="dashboard-card-heading">Total Active Buses</h2>
					<h1 class="count"><%= totalActiveBuses %></h1>
				</div>
			</div>
			<div class="dashboard-card">
				<div class="dashboard-data">
					<h2 class="dashboard-card-heading">Total Bookings Count</h2>
					<h1 class="count"><%= totalActiveBookings %></h1>
				</div>
			</div>
			<div class="dashboard-card">
				<div class="dashboard-data">
					<h2 class="dashboard-card-heading">Cancelled Bookings</h2>
					<h1 class="count"><%= totalCancelledBookings %></h1>
				</div>
			</div>
			<div style="clear:both"></div>
		</div>
		
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>