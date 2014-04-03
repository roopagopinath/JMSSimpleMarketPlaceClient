<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Simple Market Place</title>
</head>
<body bgcolor="#E6E6FA">

<form action="SignInServlet" method="post">
<h2><font color="red">Simple Market Place</font></h2>
 <h2>Sign In</h2>
 <table border="0">
<tr>
<td><font color="red">*</font>User ID :</td>
<td><input type="text" name="userID_signin" required></td>
</tr>
<tr>
<td><font color="red">*</font>Password :</td>
<td><input type="password" name="password1_signin" required></td>
</tr>
<tr>
<td>
<input type="submit" value="Sign In">
</td>
</tr>
</table>  
</form>
<br/>
 
<form action="SignUpServlet" method="post">
 
<h2>Sign Up! </h2>

<table border="0">
<tr>
<td><font color="red">*</font>First Name:</td>
<td> <input type="text" name="first_name" required> </td>
</tr>
<tr>
<td><font color="red">*</font>Last Name:</td>
<td><input type="text" name="last_name" required></td>
</tr>
<tr>
<td><font color="red">*</font>Email ID:</td>
<td> <input type="email" name="emailID" pattern="[a-zA-Z0-9]{1,20}@[a-zA-Z0-9]{1,20}.[a-zA-Z0-9]{1,10}" placeholder="me@example.com" required></td>
</tr>
<tr>
<td><font color="red">*</font>User ID:</td>
<td><input type="text" name="userID" required></td>
</tr>
<tr>
<td><font color="red">*</font>Password:</td>
<td> <input type="password" name="password1" required></td>
</tr>
<tr>
<td> <input type="submit" value="Sign Up"></td>
</tr>
</table>
</form>
 
 </body>
</html>