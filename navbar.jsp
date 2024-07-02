<div id="navbar" class="p-10">
	<div class="mainlogo f-left ml-20">
		<a href="index.jsp"><img src="./images/redbuslogo.png" alt="redbus-logo" height="65" width="100"/></a>
	</div>
	<div class="f-right mr-20">
		<p class="welcome-message f-left">Hi!<span class="welcome-name"><%
			if(session.getAttribute("email")==null){
				out.println("Guest");
			}
			else{
				out.println(session.getAttribute("email"));
			}
		 %></span></p>
		 <%
		 Integer user; 
		 int userType = 0;
		 
		 if(session.getAttribute("user_type")!=null){
			 user = (Integer)session.getAttribute("user_type");
			 userType = user.intValue();
		 }

		 if(session.getAttribute("email")!=null && userType==0){
		 	%>
		 		<button class="btn btn-red" onclick="location.href='index.jsp'">Home</button>
		 		<button class="btn btn-red" onclick="location.href='updateprofile.jsp'">Update Profile</button>
		 		<button class="btn btn-red" onclick="location.href='usertickets.jsp'">Tickets</button>
				<button onclick="location.href='logout.jsp'" class="btn btn-red" id="logout-btn">Logout</button>
		 	<%
		 }
		 else if(session.getAttribute("email")!=null && userType==1){
			 %>
			 	<button class="btn btn-red" onclick="location.href='admin.jsp'">Home</button>
			 	<button class="btn btn-red" onclick="location.href='addbus.jsp'">Add Bus</button>
			 	<button class="btn btn-red" onclick="location.href='allbuses.jsp'">Buses</button>
			 	<button class="btn btn-red" onclick="location.href='allusers.jsp'">Users</button>
				<button class="btn btn-red" onclick="location.href='bookings.jsp'">Bookings</button>
				<button onclick="location.href='logout.jsp'" class="btn btn-red" id="logout-btn">Logout</button>
			 <%
		 }
		 else{
			 %>
			 	<div class="login f-left">
					<button class="btn btn-red" id="login-form-btn">Login</button>
				</div>
				<div class="register f-left">
					<button class="btn btn-red" id="register-form-btn">Register</button>
				</div>
			 <%
		 }
		 %>
		 <div style="clear:both;"></div>
	</div>
	<div style="clear:both;"></div>
</div>