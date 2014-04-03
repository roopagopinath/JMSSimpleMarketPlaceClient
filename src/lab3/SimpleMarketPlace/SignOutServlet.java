package lab3.SimpleMarketPlace;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import lab2.Question2.MarketPlace.MarketPlace;

/**
 * Servlet implementation class SignOutServlet
 */
public class SignOutServlet extends HttpServlet {
MarketPlaceClient mpObj;
	
	public void init() throws ServletException{
		mpObj = new MarketPlaceClient();
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Cookie loginCookie = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("user")){
					loginCookie = cookie;
					break;
				}
			}
		}
		if(loginCookie != null){
			try{
				mpObj.signOut(loginCookie.getValue());
				
				loginCookie.setValue(null);
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}
		response.sendRedirect("SignUpIn.jsp");
	}


}
