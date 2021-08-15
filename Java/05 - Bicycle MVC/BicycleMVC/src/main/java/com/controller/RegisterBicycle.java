package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.ModelBicycle;

/**
 * Servlet implementation class Register
 */
@WebServlet("/RegisterBicycle")
public class RegisterBicycle extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Get user details
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String un = req.getParameter("un");
		String pwd = req.getParameter("pwd");
		String cpwd = req.getParameter("cpwd");

		//if password and the confirmed password matches
		if(pwd.equals(cpwd))
		{
			ModelBicycle mb = new ModelBicycle();
			mb.setName(name);
			mb.setEmail(email);
			mb.setUn(un);
			mb.setPwd(pwd);
			
			int x = mb.register();
			
			if (x == 0)
			{
				resp.sendRedirect("/BicycleMVC/didNotReg.html");
			}
			else
			{
				resp.sendRedirect("/BicycleMVC/successReg.html");
			}
		}
		else
		{
			resp.sendRedirect("/BicycleMVC/wrongReg.html");
		}
	}

}
