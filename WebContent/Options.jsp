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
<table>
<tr>
<td>User:</td>
<td><%=userName %><td>
<tr>
<td>Last login time:</td>
<td>${loginTime} </td>
</tr>
</table>
<br/><br/>
You can choose to do any of the following!

<ul>
<li> <p> 
<form action="StoreAd.jsp" method="post">
  <input type="submit" value="Store Advertisements"><br><br><br> 
  </form>
 </p> </li>
<li> <p> 
<form action="DisplayAdServlet" method="post">
  <input type="submit" value="Display Advertisements"><br><br><br> 
  </form>
 </p> </li>
</ul>
<form action="SignOutServlet" method="post"><br/>
<input type="submit" name="Sign Out" value="Sign Out">
</form><br/><br/>

</body>
</html>