<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Simple Market Place</title>
</head>
<body bgcolor="#E6E6FA">
<%
String userName = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("user")) userName = cookie.getValue();
}
}
if(userName == null) response.sendRedirect("SignUpIn.jsp");
%>
<p align="right">
<table>
<tr>
<td>Signed in User:</td>
<td><%=userName %><td>
</tr>
</table>
</p>
<form action="TransactionBuyServlet" method="post">
 <h2>Items Bought</h2>
 <table border="0">
<tr>
<td>Click here to see the list of items bought by you!</td>
<td><input type="submit" value="Items Bought"></td>
</tr>
</table>
</form>

<form action="TransactionSellServlet" method="post">
 <h2>Items Sold</h2>
 <table border="0">
<tr>
<td>Click here to see the list of items sold by you!</td>
<td><input type="submit" value="Items Sold"></td>
</tr>
</table>
</form>


<form action="Options.jsp" method="post">
 <h2>Options</h2>
 <table border="0">
<tr>
<td>Click here to go back to Options..</td>
<td><input type="submit" value="Back to Options"></td>
</tr>
</table>
</form>
<br/>	
	
<form action="SignOutServlet" method="post">
 <h2>Sign Out</h2>
 <table border="0">
<tr>
<td><input type="submit" value="Sign Out"></td>
</tr>
</table>
</form>
<br/>

</body>
</html>