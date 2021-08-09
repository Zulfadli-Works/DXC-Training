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
 * Servlet implementation class ChangePwdBank
 */
@WebServlet("/ChangePwdBank")
public class ChangePwdBank extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pwd = req.getParameter("pwd");
		String newPwd = req.getParameter("newPwd");
		String cNewPwd = req.getParameter("cNewPwd");
		
		if(newPwd.equals(cNewPwd))
		{
			HttpSession session = req.getSession();
			String un = (String) session.getAttribute("un");
			
			ModelBank mb = new ModelBank();
			mb.setUn(un);
			mb.setPwd(pwd);
			mb.setNewPwd(newPwd);
			int x = mb.changePwd();
			
			if (x == 0)
			{
				//password not updated
				resp.sendRedirect("/BankingMVC/failedPasswordChange.html");
			}
			else
			{
				//password updated
				resp.sendRedirect("/BankingMVC/successPasswordChange.html");
			}
		
		}
	}

}
