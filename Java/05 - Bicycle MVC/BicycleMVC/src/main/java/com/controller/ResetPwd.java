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
 * Servlet implementation class ResetPwd
 */
@WebServlet("/ResetPwd")
public class ResetPwd extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String userOtp = req.getParameter("otp");
		String newPwd = req.getParameter("newPwd");
		String cNewPwd = req.getParameter("cNewPwd");
		
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("email");
		int otp = (int) session.getAttribute("otp");
		
		if (userOtp.equals(String.valueOf(otp)) )
		{
			if (newPwd.equals(cNewPwd))
			{
				ModelBicycle mb =new ModelBicycle();
				mb.setEmail(email);
				mb.setNewPwd(newPwd);
				
				int x = mb.resetPwd();
				
				if(x ==1 )
				{
					//Successful reset
					System.out.println("Password has been reset");
				}
				else
				{
					System.out.println("Password was not reset");
				}
			}
			else
			{
				System.out.println("Password does not match");
			}
		}
		else
		{
			System.out.println("OTP does not match");
		}

	}

}
