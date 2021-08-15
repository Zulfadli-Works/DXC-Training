package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.ModelBank;

/**
 * Servlet implementation class Register
 */
@WebServlet("/RegisterBank")
public class RegisterBank extends HttpServlet {
	
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
			ModelBank mb = new ModelBank();
			mb.setName(name);
			mb.setEmail(email);
			mb.setUn(un);
			mb.setPwd(pwd);
			
			int x = mb.register();
			
			if (x == 0)
			{
				resp.sendRedirect("/BankingMVC/didNotReg.html");
			}
			else
			{
				resp.sendRedirect("/BankingMVC/successReg.html");
			}
		}
		else
		{
			resp.sendRedirect("/BankingMVC/wrongReg.html");
		}
	}

}
