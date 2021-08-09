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
 * Servlet implementation class DepositBank
 */
@WebServlet("/WithdrawBank")
public class WithdrawBank extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer.parseInt(req.getParameter("amount"));  
		int amount = Integer.parseInt(req.getParameter("amount"));
		
		ModelBank mb = new ModelBank();
		HttpSession session = req.getSession();
		String un = (String) session.getAttribute("un");
		mb.setUn(un);
//		System.out.println(un);
		int accNo = (int) session.getAttribute("accNo");
		mb.setAccNo(accNo);
		
		mb.setAmount(amount);
		
		
		int x = mb.withdraw();
//		System.out.println("TEST");
		if (x == 0)
		{
			//unsuccessful retrieval of balance
		}
		else
		{
			
			session.setAttribute("amount", amount);
			
			//login successful
			double balance = mb.getBalance();
//			System.out.println(balance);
			session.setAttribute("accNo", accNo);
			session.setAttribute("balance", balance);
			resp.sendRedirect("/BankingMVC/checkBalance.jsp");
		}
	}
}
