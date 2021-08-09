package com.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Model
 */
@WebServlet("/ModelBank")
public class ModelBank extends HttpServlet {
	
	public Connection con;
	public PreparedStatement pstmt;
	public ResultSet res;
	
	
	private String name;
	private String email;
	private String un;
	private String pwd;
	private double balance;
	private double amount;
	private double newBalance;
	
	
	private int accNo;
	private int toAccNo;
	private double toBalance;
	private String newPwd;
	
	
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
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setNewBalance(double newBalance) {
		this.newBalance = newBalance;
	}
	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}
	public void setToAccNo(int toAccNo) {
		this.toAccNo = toAccNo;
	}
	public void setToBalance(double toBalance) {
		this.toBalance = toBalance;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
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
	public double getBalance() {
		return balance;
	}
	public double getAmount() {
		return amount;
	}
	public double getNewBalance() {
		return newBalance;
	}
	public int getAccNo() {
		return accNo;
	}
	public int getToAccNo() {
		return toAccNo;
	}
	public double getToBalance() {
		return toBalance;
	}
	public String getNewPwd() {
		return newPwd;
	}
	
	public ModelBank()
	{
		try 
		{
		//loads driver
		Class.forName("com.mysql.jdbc.Driver");
		//connects to database
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankDB", "root", "root");
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
			String sql = "INSERT INTO bank values(?,?,?,?,?,?)";		
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, un);
			pstmt.setString(4, pwd);
			pstmt.setInt(5, accNo);
			pstmt.setDouble(6, 0.00); //Balance, 0 because it's a new account
			
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
			String sql = "SELECT * from bank where username=? AND password=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, un);
			pstmt.setString(2, pwd);
			
			res = pstmt.executeQuery();
			
			if(res.next() == true)
			{
				name = res.getString("name");
				accNo = res.getInt("accNo");
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
			String sql1 = "SELECT * from bank where username=? AND password=?";
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, un);
			pstmt.setString(2, pwd);
			
			res = pstmt.executeQuery();
//			System.out.println(un + pwd);
			if(res.next()==true)
			{
				String sql2 = "UPDATE bank SET password=? where username=?";
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
	
	//later when using checkBalance()
	//if return is >=0, true else failed to get balance
	public int checkBalance()
	{
		try
		{
			String sql = "SELECT * FROM bank WHERE username=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, un);
			res = pstmt.executeQuery();
			
			if(res.next() == true)
			{
				balance = res.getDouble("balance");
				setBalance(balance);
				return 1;
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
	
	//later in the html page, if x = 0, failed to add balance else run checkBalance()
	public int deposit()
	{
		try
		{
//			System.out.println("TESTR 2");
			String sql1 = "SELECT * FROM bank WHERE accNo=?";
			pstmt = con.prepareStatement(sql1);
			
			pstmt.setInt(1, accNo);
			res = pstmt.executeQuery();
			
			if(res.next() == true)
			{
				balance = res.getDouble("balance");
				newBalance = balance + amount;
				balance = newBalance;
//				System.out.println(balance);
//				System.out.println(newBalance);
				String sql2 = "UPDATE bank SET balance=? where accNo=?";
				pstmt = con.prepareStatement(sql2);
				
				pstmt.setDouble(1, newBalance);
				pstmt.setInt(2, accNo);
				
				int x = pstmt.executeUpdate();
//				System.out.println(x);
				return x;
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	public int addBalance2()
	{
		try
		{
			checkBalance();
			
			if(balance >= 0)
			{
				balance = res.getDouble("balance");
				newBalance = balance + amount;
				
				String sql = "UPDATE bank SET balance=? where username=?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setDouble(1, newBalance);
				pstmt.setString(2, un);
				
				int x = pstmt.executeUpdate();
				return x;
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	//check current balance
	//if the amount to minus is higher than current balance, stop transaction
	//else continue with transaction
	public int withdraw()
	{
		try
		{
			String sql = "SELECT * FROM bank WHERE accNo=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, accNo);
			res = pstmt.executeQuery();
			
			if(res.next() == true)
			{
				balance = res.getDouble("balance");
				
				if(balance > amount)
				{
						newBalance = balance - amount;
						balance = newBalance;
						
						String sql2 = "UPDATE bank SET balance=? where accNo=?";
						pstmt = con.prepareStatement(sql2);
						
						pstmt.setDouble(1, newBalance);
						pstmt.setInt(2, accNo);
						
						int x = pstmt.executeUpdate();
						return x;
				}
				else
				{
					return 0;
				}
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
	
	
	//check current balance
		//if the amount to minus is higher than current balance, stop transaction
		//else continue with transaction
		public int substractBalance2()
		{
			try
			{
				balance = checkBalance();
				
				if(balance >= 0)
				{
					
					if(balance > amount)
					{
							newBalance = balance - amount;
						
							String sql2 = "UPDATE bank SET balance=? where username=?";
							pstmt = con.prepareStatement(sql2);
							
							pstmt.setDouble(1, newBalance);
							pstmt.setString(2, un);
							
							int x = pstmt.executeUpdate();
							return x;
					}
					else
					{
						return 0;
					}
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
	
	public int transfer()
	{
		try
		{
			int x = withdraw();
			if (x != 0)
			{
				String sql1 = "SELECT * FROM bank WHERE accNo=?";
				pstmt = con.prepareStatement(sql1);
				
				pstmt.setInt(1, toAccNo);
				res = pstmt.executeQuery();
				
				if(res.next() == true)
				{
					toBalance = res.getDouble("balance");
					//return balance;
					
					newBalance = toBalance + amount;
					
					String sql2 = "UPDATE bank SET balance=? where accNo=?";
					pstmt = con.prepareStatement(sql2);
					
					pstmt.setDouble(1, newBalance);
					pstmt.setInt(2, toAccNo);
					
					int y = pstmt.executeUpdate();
					return y;
				}
				else
				{
					return 0;
				}	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	

	
}
