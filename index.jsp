<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RedBus | Home</title>
<link rel="stylesheet" href="./css/style.css" />
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/script.js"></script>
</head>
<body>
	<div id="maincontainer">
		<jsp:include page="navbar.jsp"></jsp:include>
		<div id="search-buses" class="p-5">
			<h1 class="text-white text-center search-heading">India's No. 1 Online Bus Ticket Booking Site</h1>
			<div class="search-area bg-white mt-20">
				<form action="controller" method="post">
					<input type="hidden" name="url" value="/getbuses">
					<div class="from f-left br-2">
						<label for="departure" class="d-block mt-20 ml-5">FROM</label>
						<input type="text" name="departure" class="input-home ml-5" required/>
						<img src="./images/departure.png" alt="departure-img" height="60" width="60" class="departure-img"/>
					</div>
					<div class="to f-left br-2">
						<label for="destination" class="d-block mt-20 ml-5">TO</label>
						<input type="text" name="destination" class="input-home ml-5" required/>
						<img src="./images/destination.png" alt="departure-img" height="55" width="50" class="destination-img"/>
					</div>
					<div class="date f-left">
						<label for="date" class="d-block mt-20 ml-5">DATE</label>
						<input type="date" id="searchdate" name="date" class="input-home ml-5"/>
					</div>
					<div class="f-right">
						<input type="submit" class="search-btn bg-red text-white" value="Search">
					</div>
					<div style="clear:both"></div>
				</form>
			</div>
		</div>

		<div id="primo-banner">
			<h1 class="banner-heading text-red text-center">PRIMO BUSES</h1>
			<div class="heading-border"></div>
			<div class="primobanner">
				<div class="banner-content">
					<div class="content-container">
						<div class="f-left">
							<img class="banner-img" src="./images/clock.png" alt="clock-img" height="70" width="70"/>
						</div>
						
						<div class="f-left content">
							<h1 class="text-white">On Time</h1>
							<p class="text-white">Punctuals arriavals on 95% times</p>
						</div>

						<div style="clear:both"></div>
					</div>

					<div class="content-container">
						<div class="f-left">
							<img class="banner-img" src="./images/friendlystaff.png" alt="clock-img" height="70" width="70"/>
						</div>
						
						<div class="f-left content">
							<h1 class="text-white">Friendly Staff</h1>
							<p class="text-white">Always Ready To Help</p>
						</div>

						<div style="clear:both"></div>
					</div>

					<div class="content-container">
						<div class="f-left">
							<img class="banner-img" src="./images/star.png" alt="clock-img" height="70" width="70"/>
						</div>
						
						<div class="f-left content">
							<h1 class="text-white">Top Rated</h1>
							<p class="text-white">Buses With 4+ Stars Ratings</p>
						</div>

						<div style="clear:both"></div>
					</div>
				</div>
			</div>
		</div>
		
		<div id="middle-content">
			<h1 class="font-40 mt-10 text-red">BOOK BUS TICKETS ONLINE</h1>
			<p class="text-justify mt-20">redBus is India's largest brand for online bus ticket booking and offers an easy-to-use online bus and train ticket booking; with over 36 million satisfied customers, 3500+ bus operators to choose from, and plenty of offers on bus ticket booking, redBus makes road journeys super convenient for travellers. A leading platform for booking bus tickets, redBus has been the leader in online bus booking over the past 17 years across thousands of cities and lakhs of routes in India.</p>
			<p class="text-justify mt-20">Booking a bus ticket online on the redBus app or website is very simple. You can download the redBus app or visit redbus.in and enter your source, destination & travel date to check the top-rated bus services available. You can then compare bus prices, user ratings & amenities, select your preferred seat, boarding & dropping points and pay using multiple payment options like UPI, debit or credit card, net banking and more. With redBus, get assured safe & secure payment methods and guaranteed travel with the best seat and bus operator of your choice. Once the bus booking payment is confirmed, all you have to do is pack your bags and get ready to travel with the m-ticket, which you can show to the bus operator on your mobile before boarding the bus. Online bus ticket booking with redBus is that simple!</p>
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
						<h4 id="error-message" class="text-white"></h4>
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