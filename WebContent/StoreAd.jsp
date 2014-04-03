<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Simple Market Place</title></head>
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
<form action="StoreAdServlet" method="post">
<h2>Storing Ads!</h2> <br/>
Enter the following information to Store them <br><br>
<table border="0">
<tr>
<td><font color="red">*</font>Item Name:</td>
<td><input type="text" name="item_name" required></td>
</tr>

<tr>
<td>Item description:</td>
<td><input type="text" name="item_desc"></td>
</tr>

<tr>
<td><font color="red">*</font>Price:</td>
<td><input type="number" name="price" min="0" max="99999999" required></td>
</tr>

<tr>
<td><font color="red">*</font>Quantity:</td>
<td><input type="number" name="quantity" min="1" max="1000000" required></td>
</tr>

<tr>
<td><input type="submit" value="Store"></td>
</tr>
</table>
</form>

<form action="Options.jsp"><br/>
<input type="submit" name="Options" value="Back to Options">
</form><br/><br/> 
 
<form action="SignOutServlet"><br/>
<input type="submit" name="Sign Out" value="Sign Out">
</form><br/><br/>
  
</body>
</html>