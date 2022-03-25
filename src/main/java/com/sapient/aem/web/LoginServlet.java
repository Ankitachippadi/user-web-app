package com.sapient.aem.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.sapient.aem.Exception.UserException;

import com.sapient.aem.service.UserService;
import com.sapient.aem.service.UserServiceImpl;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private Logger logger= Logger.getLogger(LoginServlet.class);
	private UserService userService= new UserServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out= response.getWriter();
		try {
			String username= request.getParameter("username");
			String password=  request.getParameter("password");
		
			if(userService.isValidUser(username, password)==true) {
				HttpSession session= request.getSession();
				session.setAttribute("username", username);
//				logger.info(username+" logged at"+ new Date());
				request.getRequestDispatcher("success.jsp").include(request, response);

			
			}else {
				out.println("<html><body><h2>"+"Invalid Credentials. Login Again"+"</h2></body></html>");
            	request.getRequestDispatcher("login.html").include(request, response);
				
			}
			
		}catch(UserException e) {
               e.getMessage();
			
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
