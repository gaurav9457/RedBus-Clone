<%@page import="com.redbus.bean.action.CommonActionBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
CommonActionBean bean = new CommonActionBean();
String message = bean.updateUserProfile(request,response);

session.setAttribute("message", message);

response.sendRedirect("updateprofile.jsp");
%>