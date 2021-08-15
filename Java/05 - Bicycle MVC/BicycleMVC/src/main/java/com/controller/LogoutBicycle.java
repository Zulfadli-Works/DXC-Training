package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.ModelBicycle;

/**
 * Servlet implementation class LogoutBank
 */
@WebServlet("/LogoutBicycle")
public class LogoutBicycle extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		ModelBicycle mb = new ModelBicycle();
		mb.clearShoppingCart();
		HttpSession session = req.getSession();
		//kills the session so that next time it won't be logged into the same account
		session.invalidate();
		//sends user back to the starting page
		resp.sendRedirect("/BicycleMVC/index.html");
	}

}
