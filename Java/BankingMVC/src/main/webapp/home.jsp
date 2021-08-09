<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>

#logo{
	width: 75px;
	height: 75px;
}

body {
	background-color: skyblue;
}

h1, h3, p, .buttonHolder{
	text-align:center;
}

#logout, #changeEmailAdd{
	float:right;
}

.itemImg{
	width: 200px;
	height: 200px;
	
	display: block;
	margin-left: 38 %;
	/*margin-right: auto;*/
}

</style>
</head>

<body>
<a href="home.jsp"><img id="logo" src="C:\Users\zulwi\eclipse-workspace\BankingMVC\src\main\webapp\Images\bank-logo.jpg"></a>

<h1> Online Bank </h1>
	<h3>
	<%
		out.println("Welcome " + session.getAttribute("name"));
	%>
	</h3>
	<a id="logout" href="LogoutBank">Logout</a></br>
	
	<div id="changePwd"><form action="/BankingMVC/changePwd.html"><input type="submit" name="login" value="Change Password"></form></div>
	</br>
	<form action="CheckBalance">
		<input type="submit" name="submit" value="View Balance">	
	</form>
</body>
</html>