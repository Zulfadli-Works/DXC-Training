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



.floatRight{
	float:right;
}

.center {
  display: block;
  margin-left: auto;
  margin-right: auto;
  
}

#shopName{
	display: inline;
	text-align:center;
	margin-left: 30%;
	}
	
.border{
	float: center;
	padding: 5px;
    border-style: solid;
    border-width: 4px 4px;
    border-color: lightblue lawngreen;
    border-radius: 30px;
   }

img{
width: 30%;
}

ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
}

li {
  float: right;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

/* Change the link color to #111 (black) on hover */
li a:hover {
  background-color: #111;
}


</style>
</head>

<body>
<a href="home.jsp"><img id="logo" src="C:\Users\zulwi\eclipse-workspace\BicycleMVC\src\main\webapp\Images\bank-logo.jpg"></a>

<h1 id="shopName" class="border"> Bicycle Shop</h1>
	<h3>
	<%
		out.println("Welcome " + session.getAttribute("name"));
	%>
	</h3>
	<!--  
	<a class="border floatRight" href="LogoutBicycle">Logout</a></br></br>
	<a class="border floatRight" href="shoppingCart.jsp">Cart</a></br></br>
	<a class="border floatRight" href="/BicycleMVC/changePwd.html">Change Password</a></br></br>
	-->
	
<ul>
  <li><a href="LogoutBicycle">Logout</a></li>
  <li><a href="/BicycleMVC/changePwd.html">Change Password</a></li>
  <li><a href="shoppingCart.jsp">Cart</a></li>
</ul>
	
	<img class="center" src="C:\Users\zulwi\Documents\Training\HTML\Training\Images\java-zelo.jpg">
	<h3>Java Zelo</h3>
	<p>$343</p>
	</br>
	<div class="buttonHolder">
		<form action="AddToCart">
			<button type="submit" name="item" value="1">Add to Cart</button>
		</form>
	</div>
	</br></br>
	
	<img class="center" src="C:\Users\zulwi\Documents\Training\HTML\Training\Images\java-neo.png">
	<h3>Java Neo</h3>
	<p>$810</p>
	</br>
	<div class="buttonHolder">
		<form action="AddToCart">
			<button type="submit" name="item" value="2">Add to Cart</button>
		</form>
	</div>

</body>
</html>