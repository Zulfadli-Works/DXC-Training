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
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String item = req.getParameter("item");
		
		ModelBicycle mb = new ModelBicycle();
		HttpSession session = req.getSession();
		
		mb.setItem_id(Integer.parseInt(item));
		String un = (String) session.getAttribute("un");
		mb.setUn(un);
		mb.addItem();
		
		double price = mb.getPrice();
		System.out.println("Item: " + item + ", Price: $" + price);
		
		
		double itemTotal = (double) session.getAttribute("itemTotal");
		itemTotal = itemTotal + price;
		session.setAttribute("itemTotal", itemTotal);
		mb.setItemTotal(itemTotal);
		System.out.println("Total item price: " + itemTotal + "\n");
		
		resp.sendRedirect("/BicycleMVC/home.jsp");
	}

}
