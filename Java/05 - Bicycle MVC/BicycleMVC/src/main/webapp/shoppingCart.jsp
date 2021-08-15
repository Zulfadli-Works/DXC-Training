<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>

body{
	background-color: skyblue;
}

#sCart{
width: 60%;
background-color: black;
border-collapse: collapse;
}

#sCart td{
background-color: lightgreen;
font-weight: 400;
font-size: large;
}

td,h3{
	text-align:center;
}

.center {
  margin-left: auto;
  margin-right: auto;
}
</style>
</head>
<body>
<a href="home.jsp"><img id="logo" src="C:\Users\zulwi\eclipse-workspace\BicycleMVC\src\main\webapp\Images\bank-logo.jpg"></a>

<!-- https://stackoverflow.com/questions/31200393/how-to-display-a-database-table-in-the-jsp-page -->
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>

<form method="post">

<table border="1" id="sCart" class="center">
<tr>
<td>Item Id</td>
<td>Item Name</td>
<td>Price</td>
<td>Quantity</td>
</tr>
<%
try
{
Class.forName("com.mysql.jdbc.Driver");
String url="jdbc:mysql://localhost:3306/bicycle";
String username="root";
String password="root";
String query="select * from cart";
Connection conn=DriverManager.getConnection(url, username, password);
Statement stmt=conn.createStatement();
ResultSet rs=stmt.executeQuery(query);

while(rs.next())
{
	int item_id = rs.getInt("item_id") ;
	session.setAttribute("item_id", item_id);
%>
<tr>
	<td><%=rs.getString("item_id") %></td>
	<td><%=rs.getString("name") %></td>
	<td>$<%=rs.getDouble("price") %></td>
	<td><%=rs.getInt("quantity") %></td>
	
</tr>

<%

}
%>
</table>

<%
rs.close();
stmt.close();
conn.close();
}
catch(Exception e)
{
e.printStackTrace();
}
%>
<h3>
	<%
		out.println("Current Total: " + session.getAttribute("itemTotal"));
	%>
</h3>
</form>

<form action="RemoveItem">
<table class="center">
<tr>
	<th>Item Id to remove</th>
	<th>Click to remove item</th>
</tr>
<tr>
	<td><input type="text" name="itemR"></td>
	<td><button type="submit"  name="submit">Remove Item</button></td>
</tr>

</table>
</form>

</body>
</html>