<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	int userType = Integer.parseInt(request.getParameter("user_type"));
	String email = request.getParameter("email");
	String isActive = request.getParameter("is_active");
	
	String message = null;
	
	if(isActive.equals("Y")){
		if(userType==1){
			session.setAttribute("email", email);
			session.setAttribute("user_type", Integer.valueOf(userType));
			message = "admin";
		}
		else if(userType==0){
			session.setAttribute("email", email);
			session.setAttribute("user_type", Integer.valueOf(userType));
			message = "user";
		}
	}
	else{
		message = "User is Not Active";
	}
	
	String responseData = "{\"message\": \"" + message + "\"}";

    // Convert response data to JSON
    Gson gson = new Gson();
    String jsonResponse = gson.toJson(responseData);
    
  //Set content type and write response
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(jsonResponse);
%>