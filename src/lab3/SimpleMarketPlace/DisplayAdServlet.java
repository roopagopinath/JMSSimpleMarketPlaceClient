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
 * Servlet implementation class DisplayAdServlet
 */
public class DisplayAdServlet extends HttpServlet {
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
				String msg = mpObj.displayAdvertisement(loginCookie.getValue());
				out.println("<html> <body bgcolor=\"#E6E6FA\">");
				out.println("<p align=\"right\" ><table><tr><td>Signed in User:</td><td>"+loginCookie.getValue() + "</td></tr></table></p>");
				out.println("<h2>Advertisements!!</h2>");
				out.println("<br/><br/>");
				if(msg.equals("NO-AD")){
					out.println("No items to display!");
				}
				else{
					String arr[] = msg.split("@");

					request.getSession().setAttribute("count", arr.length);

					for(int i=0; i< arr.length; i++){

						String[] m = arr[i].split(":");
						if(Integer.parseInt(m[4])!=0)
						{
							String textToDisplay = "The user " + m[0] + " has advertised product " + m[1] + ". Product description: " + m[2] + " and it costs $ " + m[3] + ". ";
							out.println((i+1) +". " + textToDisplay );
							out.println(Integer.parseInt(m[4])+" such items are available <br/> <br/> ");
						}

						request.getSession().setAttribute("userID"+i, m[0]);
						//Passing item name to JSP buyProduct
						request.getSession().setAttribute("item_name"+i, m[1]);

						//Passing quantity to JSP buyProduct
						request.getSession().setAttribute("quantity"+i, m[4]);
						request.getSession().setAttribute("itemID"+i, m[5]);

					}
				}
				out.println("<form action=\"Options.jsp\">");
				out.println("<input type=\"submit\" name=\"Back\" value=\"Back to Options\">");
				out.println("</form><br/>");

				out.println("<form action=\"SignOutServlet\" method=\"post\"><br/>");
				out.println("<input type=\"submit\" name=\"Sign Out\" value=\"Sign Out\">");
				out.println("</form><br/><br/>");

				out.println("</body> </html>");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/SignUpIn.jsp");
			out.println("<font color=red>Sorry, Not a valid session. Try logging in again!</font>");
			rd.include(request, response);

		}
	}


}
