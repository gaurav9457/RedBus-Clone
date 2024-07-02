<%@page import="com.redbus.bean.pojo.User"%>
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

if(session.getAttribute("email")==null && userType!=0){
	response.sendRedirect("index.jsp");
	return;
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>RedBus | Update Profile</title>
<link rel="stylesheet" href="./css/style.css" />
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/script.js"></script>
</head>
<body>
	<%
		CommonDao dao = new CommonDao();
		User userData = dao.verifyUser((String)session.getAttribute("email"));
		
		String fullName = userData.getName();
		String[] parts = fullName.split("\\s+");
	%>
	<div id="maincontainer">
		<jsp:include page="navbar.jsp"></jsp:include>
		
		<div class="container">
			<h1 class="text-center text-red">User Profile</h1>
			<div class="f-left">
				<div>
					<%
						if(userData.getUserProfile()!=null){
							%>
								<img class="user-image" alt="user-img" src="<%= userData.getUserProfile() %>" height="250" width="250">
							<%
						}
						else{
							%>
							<img class="user-image" alt="user-img" src="./images/userimg.png" height="250" width="250">
							<%
						}
					%>
				</div>
			</div>
			<div class="f-left form-container">
				<form action="postupdateprofiledata.jsp" method="post" enctype="multipart/form-data">
					<div class="form-element">
						<label for="firstname" class="label text-black">First Name <span>*</span></label>
						<input type="text" name="firstname" class="form_input text-white bg-red" id="firstname" maxlength="15" value="<%= parts[0] %>" required>
						<span class="error text-black width-280" id="firstname_error"></span>
					</div>
					
					<div class="form-element">
						<label for="lastname" class="label text-black">Last Name <span class="required">*</span></label>
						<input type="text" name="lastname" class="form_input text-white bg-red" id="lastname" maxlength="15" value="<%= parts[1] %>" required>
						<span class="error text-black width-280" id="lastname_error"></span>
					</div>

					<div class="form-element">
						<label for="mobile_number" class="label text-black">Mobile Number <span class="required">*</span></label>
						<input type="text" class="form_input text-white bg-red" name="mobile_number" id="mobile_number" maxlength="10" value="<%= userData.getMobileNumber() %>" required>
						<span class="error text-black width-280" id="mobile_number_error"></span>
					</div>

					<div class="form-element">
						<label for="email" class="label text-black">Email <span class="required">*</span></label>
						<input type="text" class="form_input text-white bg-red" name="email" id="email" value="<%= userData.getEmail() %>" required>
						<span class="error text-black width-280" id="email_error"></span>
					</div>
					<div class="form-element">
						<label for="userimage" class="label text-black d-block mb-5">User Profile <span class="required">*</span></label>
						<input type="file" name="userimage" class="p-10 btn-red width-280" style="border-radius:10px;" required>
					</div>
					<input type="hidden" name="session_email" value="<%= session.getAttribute("email") %>">
					<div class="form-element">
						<input type="submit" class="btn btn-red">
					</div>
				</form>
			</div>
			<div style="clear:both"></div>
		</div>
		
		<jsp:include page="footer.jsp"></jsp:include>
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