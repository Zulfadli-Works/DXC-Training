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
 * Servlet implementation class RemoveItem
 */
@WebServlet("/RemoveItem")
public class RemoveItem extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ModelBicycle mb = new ModelBicycle();
		HttpSession session = req.getSession();
		int itemR = Integer.parseInt(req.getParameter("itemR"));
//		System.out.println(itemR);
		mb.setItem_id(itemR);
		
		double itemTotal = (double) session.getAttribute("itemTotal");
		mb.setItemTotal(itemTotal);
		
		mb.removeItem();
		itemTotal = mb.getItemTotal();
		session.setAttribute("itemTotal", itemTotal);
//		System.out.println(itemTotal);
		
//		int quantity = (int) session.getAttribute("quantity");
//		int price = (int) session.getAttribute("price");
//		double itemTotal = (double) session.getAttribute("itemTotal");
//		itemTotal = itemTotal - (quantity * price);
//		session.setAttribute("itemTotal", itemTotal);
//		System.out.println(item_id);
//		System.out.println(itemTotal);
		
		resp.sendRedirect("/BicycleMVC/shoppingCart.jsp");
	}

}
