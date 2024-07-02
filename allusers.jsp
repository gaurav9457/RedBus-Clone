<%@page import="com.redbus.bean.pojo.User"%>
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
<title>RedBus | Users</title>
<link rel="stylesheet" href="./css/style.css" />
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/script.js"></script>
</head>
<body>
	<div id="maincontainer">
		<jsp:include page="navbar.jsp"></jsp:include>
		
		<h4 id="user-status-message" class="text-center text-green mt-10"></h4>
		
		<div id="allusers">
			<table class="table-collapse" id="allusers-table">
				<tr>
					<th class="table-heading">ID</th>
					<th class="table-heading">Name</th>
					<th class="table-heading">Mobile Number</th>
					<th class="table-heading">Email</th>
					<th class="table-heading">Active/De-Active</th>
					<th class="table-heading">Created At</th>
				</tr>
				
				<%
					int num = 1;
				
					CommonActionBean bean = new CommonActionBean();
					List allUsers = (List)bean.getAllUsers();
					
					for(int i=0; i<allUsers.size(); i++){
						User allusers = (User)allUsers.get(i);
				%>
					<tr>
						<td class="table-data"><%= num %></td>
						<td class="table-data"><%= allusers.getName() %></td>
						<td class="table-data"><%= allusers.getMobileNumber() %></td>
						<td class="table-data"><%= allusers.getEmail() %></td>
						<td class="table-data">
							<select id="<%= allusers.getUserId() %>" class="user-status" onchange="userFeatures.changeUserStatus(this)">
								<option value="Y"
									<% 
									if(allusers.getIsActive().equals("Y")){
										out.println("selected");
									}
									%>
								>Active</option>
								
								
								<option value="N" 
								<% 
								if(allusers.getIsActive().equals("N")){
									out.println("selected");
								}
								%>
								>De-Active</option>
							</select>
						</td>
						<td class="table-data"><%= allusers.getCreatedAt() %></td>
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