package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.ModelBank;

/**
 * Servlet implementation class LoginBank
 */
@WebServlet("/LoginBank")
public class LoginBank extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String un = req.getParameter("un");
		String pwd = req.getParameter("pwd");
		
		ModelBank mb = new ModelBank();
		
		mb.setUn(un);
		mb.setPwd(pwd);
		
		int x = mb.login();
		
		if (x == 0)
		{
			//login unsuccessful
			resp.sendRedirect("/BankingMVC/login.html");
		}
		else
		{
			//login successful
			String name = mb.getName();
			int accNo = mb.getAccNo();
			HttpSession session = req.getSession(true);
			session.setAttribute("name", name);
			session.setAttribute("un", un);
			session.setAttribute("accNo", accNo);
			resp.sendRedirect("/BankingMVC/home.jsp");
		}
	}

}
