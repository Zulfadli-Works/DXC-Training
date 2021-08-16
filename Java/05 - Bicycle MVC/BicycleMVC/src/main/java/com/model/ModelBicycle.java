package com.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class Model
 */
@WebServlet("/ModelBicycle")
public class ModelBicycle extends HttpServlet {
	
	public Connection con;
	public PreparedStatement pstmt;
	public ResultSet res;
	
	
	private String name;
	private String email;
	private String un;
	private String pwd;
	private int accNo;
	
	private String newPwd;
	
	private int item_id;
	private double price;
	private int quantity;
	private double itemTotal;
	
	
	public double getItemTotal() {
		return itemTotal;
	}
	public void setItemTotal(double itemTotal) {
		this.itemTotal = itemTotal;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setUn(String un) {
		this.un = un;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getUn() {
		return un;
	}
	public String getPwd() {
		return pwd;
	}
	public int getAccNo() {
		return accNo;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public double getPrice() {
		return price;
	}
	public int getItem_id() {
		return item_id;
	}
	
	
	
	public ModelBicycle()
	{
		try 
		{
		//loads driver
		Class.forName("com.mysql.jdbc.Driver");
		//connects to database
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bicycle", "root", "root");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public int register()
	{
		Random rn = new Random();
		int accNo = rn.nextInt(19999999) + 80000000;
		try 
		{
			String sql = "INSERT INTO users values(?,?,?,?,?)";		
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, un);
			pstmt.setString(4, pwd);
			pstmt.setInt(5, accNo);
			
			//sends the sql transaction
			int x = pstmt.executeUpdate();
			return x;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int login()
	{
		try
		{
			String sql = "SELECT * from users where username=? AND password=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, un);
			pstmt.setString(2, pwd);
			
			res = pstmt.executeQuery();
			
			if(res.next() == true)
			{
				name = res.getString("name");
				accNo = res.getInt("accNo");
				email = res.getString("email");
				return 1;
			}
			return 0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int changePwd()
	{
		try
		{
			String sql1 = "SELECT * from users where username=? AND password=?";
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, un);
			pstmt.setString(2, pwd);
			
			res = pstmt.executeQuery();
//			System.out.println(un + pwd);
			if(res.next()==true)
			{
				String sql2 = "UPDATE users SET password=? where username=?";
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, newPwd);
				pstmt.setString(2, un);
				int x = pstmt.executeUpdate();
//				System.out.println(un + newPwd);
				return x;
			}
			else
			{
				return 0;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	
	public int resetPwd()
	{
		
		try {
			String sql = "UPDATE users SET password=? where email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newPwd);
			pstmt.setString(2, email);
			
			int x = pstmt.executeUpdate();
			return x;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		 
	}
	
	
	public void addItem()
	{
		try
		{
			//this is to search for the price via the item_id
			String sql1 = "SELECT * FROM items where item_id=?";
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, item_id);
			
			res = pstmt.executeQuery();
			if(res.next() == true)
			{
				//servlet will get the price to add into the session for totalPrice
				price = res.getDouble("price");
				name = res.getString("name");
				
				//Check if the same product has already been added, if it has, the quantity + 1
				String sql2 = "SELECT * FROM cart where item_id=?";
				pstmt = con.prepareStatement(sql2);
				pstmt.setInt(1, item_id);	
				res = pstmt.executeQuery();				
				if(res.next() == true)	//
				{
					quantity = res.getInt("quantity");
//					System.out.println("TEST 1");
					quantity += 1;
					String sql3 = "UPDATE cart SET quantity=? where item_id=?";
					pstmt = con.prepareStatement(sql3);
					pstmt.setInt(1, quantity);
					pstmt.setInt(2, item_id);
					
					int x = pstmt.executeUpdate();
					if(x == 0)
					{
	//					System.out.println("Did not insert into shopping cart");
					}
					else
					{
	//					System.out.println("IT WORKED");
					}
	//				return 1;
				}
				else	//if the item is not a duplicate, add as new item
				{
//					System.out.println("Test 2");
					quantity = 1;
					String sql3 = "INSERT INTO cart values(?,?,?,?,?);";
					
					pstmt = con.prepareStatement(sql3);
					pstmt.setInt(1, item_id);
					pstmt.setString(2, name);
					pstmt.setDouble(3, price);
					pstmt.setInt(4, quantity);
					pstmt.setString(5, un);
					System.out.println(quantity);
					
					int x = pstmt.executeUpdate();
					if(x == 0)
					{
	//					System.out.println("Did not insert into shopping cart");
					}
					else
					{
	//					System.out.println("IT WORKED");
					}
	//				return 1;
				}
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	public int removeItem()
	{
		try 
		{
			String sql1 = "SELECT * FROM cart where item_id=?";
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, item_id);
			
//			System.out.println(item_id);
			res = pstmt.executeQuery();
			if(res.next() == true)
			{
//				System.out.println("TESTETSTEST");
				quantity = res.getInt("quantity");
				price = res.getDouble("price");
//				System.out.println("total: " + itemTotal);
				itemTotal = itemTotal - (quantity * price);
//				System.out.println(item_id  + " " + quantity + " " + price);
//				System.out.println("total: " + itemTotal);
			
			}
		}
		catch(Exception e)
		{
			
		}
		
		try
		{
		String sql = "DELETE from cart where item_id=?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, item_id);
		int x = pstmt.executeUpdate();
		return x;
		}
		catch(Exception e)
		{
			
		}
		return 0;
	}
	
	public void showShoppingCart()
	{
		try 
		{
			String sql1 = "SELECT * FROM cart where username=?";
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, un);
			
			res = pstmt.executeQuery();
			if(res.next() == true)
			{
				
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void clearShoppingCart()
	{
		try
		{
			String sql = "TRUNCATE TABLE cart;";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			
		}
	}
	
}
