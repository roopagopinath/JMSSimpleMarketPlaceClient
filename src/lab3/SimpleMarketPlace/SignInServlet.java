package lab3.SimpleMarketPlace;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import lab2.Question2.MarketPlace.*;

/**
 * Servlet implementation class MarketPlaceServlet
 */
public class SignInServlet extends HttpServlet {
MarketPlaceClient mpObj;
	
	public void init() throws ServletException{
		mpObj = new MarketPlaceClient();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// change the parameter name after JSP
		String userID = request.getParameter("userID_signin");
		String password1 = request.getParameter("password1_signin");
		try{
			String msg = mpObj.signIn(userID, password1);
			String[] m = msg.split(":");
					
			if(m[0].equals("true"))
			{
				Cookie loginCookie = new Cookie("user",userID);
				//setting cookie to expiry in 30 mins
				loginCookie.setMaxAge(30*60);
				response.addCookie(loginCookie);
				
				//request.getSession().setAttribute("userID_signin", userID);
				if(m[1].equals("-1")){
					request.getSession().setAttribute("loginTime", "Not available - Its your first login");
				}
				else{
				request.getSession().setAttribute("loginTime", m[1]+":"+m[2]+":"+m[3].substring(0,2));
				}
				
				response.sendRedirect("Options.jsp");
				    
			}
			else{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/SignUpIn.jsp");
				out.println("<font color=red>Either user name or password is wrong.</font>");
				rd.include(request, response);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void destroy(){
		super.destroy();
	}

}
