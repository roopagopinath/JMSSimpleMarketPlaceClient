package lab3.SimpleMarketPlace;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import lab2.Question2.MarketPlace.*;

/**
 * Servlet implementation class MarketPlaceServlet
 */
public class SignUpServlet extends HttpServlet {
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
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String emailID = request.getParameter("emailID");
		String password = request.getParameter("password1");
		String userID = request.getParameter("userID");

		try{
			boolean status = mpObj.signUp(first_name, last_name, emailID, password, userID);
			
			String msg; 
			if(status == true){
				msg = "You have successfully signed up!";
			}
			else{
				msg = "Could not sign up! Try again.";
				
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/SignUpIn.jsp");
			out.println("<h3>" +msg +"</h3>");
			rd.include(request, response);
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
