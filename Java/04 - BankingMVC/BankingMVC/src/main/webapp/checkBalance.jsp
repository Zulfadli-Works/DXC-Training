<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
body{
	background-color: gray;
}
</style>
</head>
<body>
<%
	out.println("Accout Number: " + session.getAttribute("accNo") + "\n");
	out.println("Balance: $" + session.getAttribute("balance"));
%>

<form action="DepositBank">
	<table>
		<tr>
			<th>Deposit</th>
			<th>Amount</th>
		</tr>
		<!-- 
 		<tr>
			<td>Receiver's Account Number: </td>
			<td><input type="text" name="toAccNo"></td>
		</tr>
		-->
		<tr>
			<td>Amount: $</td>
			<td><input type="text" name="amount"></td>
		</tr>

		<tr>
			<td></td>
			<td><input type="submit" name="submit" value="Deposit"></td>
		</tr>
	</table>
</form>

</br>

<form action="WithdrawBank">
	<table>
		<tr>
			<th>Withdraw</th>
			<th>Amount</th>
		</tr>
		<!-- 
		<tr>
			<td>Account Number: </td>
			<td><input type="text" name="toAccNo"></td>
		</tr>
		-->
		<tr>
			<td>Amount: $</td>
			<td><input type="text" name="amount"></td>
		</tr>
		
		<tr>
			<td></td>
			<td><input type="submit" name="submit" value="Withdraw"></td>
		</tr>
	</table>	
</form>

</br>

<form action="TransferBank">
	<table>
		<tr>
			<th>Transfer to</th>
			<th>Amount</th>
		</tr>
		
		<tr>
			<td>Account Number: </td>
			<td><input type="text" name="toAccNo"></td>
		</tr>
		
		<tr>
			<td>Amount: $</td>
			<td><input type="text" name="amount"></td>
		</tr>
		
		<tr>
			<td></td>
			<td><input type="submit" name="submit" value="Transfer"></td>
		</tr>
	</table>	
</form>
</body>
</html>