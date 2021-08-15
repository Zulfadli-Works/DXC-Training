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
 * Servlet implementation class ChangePwdBank
 */
@WebServlet("/ChangePwdBicycle")
public class ChangePwdBicycle extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pwd = req.getParameter("pwd");
		String newPwd = req.getParameter("newPwd");
		String cNewPwd = req.getParameter("cNewPwd");
		
		if(newPwd.equals(cNewPwd))
		{
			HttpSession session = req.getSession();
			String un = (String) session.getAttribute("un");
			
			ModelBicycle mb = new ModelBicycle();
			mb.setUn(un);
			mb.setPwd(pwd);
			mb.setNewPwd(newPwd);
			int x = mb.changePwd();
			
			if (x == 0)
			{
				//password not updated
				resp.sendRedirect("/BicycleMVC/failedPasswordChange.html");
			}
			else
			{
				//password updated
				resp.sendRedirect("/BicycleMVC/successPasswordChange.html");
			}
		
		}
	}

}
