package lab3.SimpleMarketPlace;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MarketPlaceServlet
 */
public class StoreAdServlet extends HttpServlet {
MarketPlaceClient mpObj;
	
	public void init() throws ServletException{
		mpObj = new MarketPlaceClient();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try{

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
			// change the parameter name after JSP
			String item_name = request.getParameter("item_name");
			String item_desc = request.getParameter("item_desc");
			float price = Float.parseFloat(request.getParameter("price"));
			
			if(price < 0)
			{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/StoreAd.jsp");
				rd.include(request, response);
				throw new Exception();
				

			}
			
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			if(quantity < 1)
			{
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/StoreAd.jsp");
				rd.include(request, response);
				throw new Exception();

			}


			boolean status = mpObj.storeAdvertisement(item_name, item_desc, price, quantity, loginCookie.getValue());
			String msg;
			if(status == true){
				msg = "Product Ad stored successfully!";
			}
			else{
				msg = "There was an error storing your Ad. Please try again.";
			}

			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Options.jsp");
			out.println("<h3>" +msg +"</h3>");
			rd.include(request, response);

		}
		else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/SignUpIn.jsp");
			out.println("<font color=red>Sorry, Not a valid session. Try logging in again!</font>");
			rd.include(request, response);

		}
		}
		catch(Exception e){
			out.println("<font color=red> Either Price or Quantity is out of range (Should be a positive number).</font> <br/>");
			out.println("<font color=red> Price can be 0 or more. <br/> Quantity can be 1 or more. </font>");
		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void destroy(){
		super.destroy();
	}

}

