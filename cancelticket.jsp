<%@page import="com.redbus.handler.CommonAjaxHandler"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	CommonAjaxHandler handler = new CommonAjaxHandler();
	String jsonResponse = handler.cancelTicket(request,response);
	
	//Set content type and write response
	response.setContentType("application/json");
	response.setCharacterEncoding("UTF-8");
	response.getWriter().write(jsonResponse);
%>