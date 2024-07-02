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
<title>RedBus | Add Bus</title>
<link rel="stylesheet" href="./css/style.css" />
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/script.js"></script>
</head>
<body>
	<div id="maincontainer">
		<jsp:include page="navbar.jsp"></jsp:include>
		<div id="addbus" class="m-10">
			<div class="form-leftside f-left">
				<img alt="addbus-img" src="./images/addbus.png" height="621" width="900">
			</div>
			<div class="form-rightside f-left bg-red">
				<h2 class="text-center text-white">ADD BUS</h2>
				<form action="controller" method="post" id="add-bus-form">
					<div class="form-element">
						<label for="busname" class="label d-block">Bus Name <span class="required">*</span></label>
						<input class="form_input" type="text" id="busname" name="busname" placeholder="Bus Name...">
					</div>
					
					<div class="form-element">
						<label for="startingpoint" class="label d-block">Starting Point <span class="required">*</span></label>
						<input class="form_input" type="text" id="busstartpoint" name="startingpoint" placeholder="Starting Point...">
					</div>
					
					<div class="form-element">
						<label for="endpoint" class="label d-block">End Point <span class="required">*</span></label>
						<input class="form_input" type="text" id="busendpoint" name="endpoint" placeholder="End Point...">
					</div>
					
					<div class="form-element">
						<label for="route" class="label d-block">Route <span class="required">*</span></label>
						<input class="form_input" type="text" id="busroute" name="route" placeholder="Route...">
					</div>
					
					<div class="form-element">
						<label for="busregistrationnumber" class="label d-block">Bus Registration Number <span class="required">*</span></label>
						<input class="form_input" type="text" id="busregistrationnumber" name="busregistrationnumber" placeholder="Bus Registration Number...">
					</div>
					
					<div class="form-element">
						<label class="label gender d-block mb-10">Bus Type <span class="required">*</span></label>
						<label for="bustype" class="label">Seater</label>
						<input type="radio" class="radio-btn" name="bustype" id="seater" value="0">
						<label for="bustype" class="label ml-10">Sleeper</label>
						<input type="radio" class="radio-btn" name="bustype" id="sleeper" value="1">
						<span class="error" id="gender_error"></span>
					</div>
					
					<div class="form-element">
						<label class="label gender d-block mb-10">Primo <span class="required">*</span></label>
						<label for="isprimo" class="label">Yes</label>
						<input type="radio" class="radio-btn" name="isprimo" id="primoY" value="Y">
						<label for="isprimo" class="label ml-10">No</label>
						<input type="radio" class="radio-btn" name="isprimo" id="primoN" value="N">
						<span class="error" id="gender_error"></span>
					</div>
					
					<div class="form-element">
						<label for="ticketprice" class="label d-block">Ticket Price <span class="required">*</span></label>
						<input class="form_input" type="text" id="busticketprice" name="ticketprice" placeholder="Ticket Price...">
					</div>
					
					<div class="form-element">
						<label for="totalseats" class="label d-block">Total Seats <span class="required">*</span></label>
						<input class="form_input" type="number" id="bustotalseats" name="totalseats" placeholder="Total Seats Multiple of 6..." max="30">
						<span class="error" id="seats-error"></span>
					</div>
					
					<div class="form-element">
						<label for="starttime" class="label d-block">Start Journey Time <span class="required">*</span></label>
						<input type="time" step="1" id="busstarttime" min="00:00:00" max="23:59:59" class="time-input" id="starttime" name="starttime">
					</div>
					
					<div class="form-element">
						<label for="endtime" class="label d-block">End Journey Time <span class="required">*</span></label>
						<input type="time" step="1" id="busendtime" min="00:00:00" max="23:59:59" class="time-input" id="endtime" name="endtime">
					</div>
					
					<input type="hidden" name="url" value="/addbus">
					<input type="hidden" name="email" value="<%= session.getAttribute("email") %>">
					<input class="btn btn-white" type="submit" value="Add Bus">
				</form>
			</div>
			<div style="clear:both;"></div>
		</div>
		
		<jsp:include page="footer.jsp"></jsp:include>
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