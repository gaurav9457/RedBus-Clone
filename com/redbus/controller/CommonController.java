package com.redbus.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.redbus.bean.action.CommonActionBean;
import com.redbus.bean.output.BusesData;

public class CommonController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String url = req.getParameter("url");
		
		if(url.equals("/register")){
			CommonActionBean bean = new CommonActionBean();
			String message = bean.registerUser(req,resp);
			
			HttpSession session = req.getSession();
			session.setAttribute("message", message);
			resp.sendRedirect("index.jsp");
		}
		else if(url.equals("/addbus")){
			CommonActionBean bean = new CommonActionBean();
			String message = bean.addBus(req,resp);
			
			HttpSession session = req.getSession();
			session.setAttribute("message", message);
			resp.sendRedirect("addbus.jsp");
		}
		else if(url.equals("/getbuses")){
			CommonActionBean bean = new CommonActionBean();
			List<BusesData> busesData =  bean.getBusesData(req, resp);
			
			String date = req.getParameter("date");
			
			HttpSession session = req.getSession();
			session.setAttribute("busesdata", busesData);
			session.setAttribute("searcheddate", date);
			resp.sendRedirect("showbuses.jsp");
		}
		else if(url.equals("/boardingpoint")){
			CommonActionBean bean = new CommonActionBean();
			String message = bean.addBoardingPoint(req,resp);
			
			HttpSession session = req.getSession();
			session.setAttribute("message", message);
			resp.sendRedirect("allbuses.jsp");
		}
		else if(url.equals("/destinationpoint")){
			CommonActionBean bean = new CommonActionBean();
			String message = bean.addDestinationPoint(req,resp);
			
			HttpSession session = req.getSession();
			session.setAttribute("message", message);
			resp.sendRedirect("allbuses.jsp");
		}
		else if(url.equals("/bookticket")){
			CommonActionBean bean = new CommonActionBean();
			String message = bean.bookTicket(req,resp);
			
			HttpSession session = req.getSession();
			session.setAttribute("message", message);
			resp.sendRedirect("index.jsp");
		}
	}
}
