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
 * Servlet implementation class LoginBank
 */
@WebServlet("/LoginBicycle")
public class LoginBicycle extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String un = req.getParameter("un");
		String pwd = req.getParameter("pwd");
		
		ModelBicycle mb = new ModelBicycle();

		mb.setUn(un);
		mb.setPwd(pwd);
		
		int x = mb.login();
		
		if (x == 0)
		{
			//login unsuccessful
			resp.sendRedirect("/BicycleMVC/login.html");
		}
		else
		{
			//login successful
			String name = mb.getName();
			int accNo = mb.getAccNo();
			String email = mb.getEmail();
			HttpSession session = req.getSession(true);
			session.setAttribute("name", name);
			session.setAttribute("un", un);
			session.setAttribute("accNo", accNo);
			session.setAttribute("email", email);
			session.setAttribute("itemTotal", 0.0);
			session.setAttribute("quantity", 0);
			session.setAttribute("price", 0.0);
			
			resp.sendRedirect("/BicycleMVC/home.jsp");
		}
	}

}
