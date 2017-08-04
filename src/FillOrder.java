import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;
import org.codehaus.jackson.type.TypeReference;

import com.mysql.jdbc.Blob;
import com.mysql.jdbc.Driver;

/**
 * Servlet implementation class FillOrder
 */
@WebServlet("/FillOrder")
public class FillOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FillOrder() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Class.forName("com.mysql.jdbc.Driver").newInstance();
		PrintWriter out = response.getWriter();
		//Connection conn = DriverManager.getConnection("jdbc:mysql://sylvester-mccoy-v3.ics.uci.edu/inf124-db-000", "inf124-db-000", "b3g5exxF19bp");
		//Statement stmt = conn.createStatement();
		HttpSession session = request.getSession();
		
		ArrayList<String> c = (ArrayList<String>) session.getAttribute("cart");
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Project 3 </title>");
		out.println("<link href=kittenstyles.css rel=stylesheet type=text/css>");
		out.println("<script type = \"text/Javascript\" src = \"kitten.js\">");
		out.println("</script>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div id = \"cart\">");
		out.println("<p>Cart: " + c.size() + "</p>");
		out.println("<form action=\"CartHandler\" method=\"get\">");
		out.println("<input type=\"submit\" name=\"cart\" value=\"Checkout\">");
		out.println("</form>");
		out.println("</div>");
		out.println("<div id=\"main\">");
		out.println("<h1 id=\"header\">Kittens For All </h1>");
		out.println("<nav>");
		out.println("<a href=\"Home\"> Home </a>");
		out.println("<a href=\"about_us.html\">About Us </a>");
		out.println("<p> Fill out the order form below to complete your order! </p>");
		out.println("</nav>");
		out.println("<div id=\"formdiv\">");
		int total = 0;
		for(int i = 0; i < c.size(); i++)
		{
		
		String s = c.get(i);
		
		ClientConfig config = new ClientConfig();

		Client client = ClientBuilder.newClient(config);

		WebTarget target = client.target(getBaseURI());
		
		
		
		String jsonResponse =
		        target.path("rest").path("cat").path(s).
		                request(). //send a request
		                accept(MediaType.APPLICATION_JSON). //specify the media type of the response
		                get(String.class);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Cat cat = objectMapper.readValue(jsonResponse, Cat.class);
		out.println("<img src=\"" + cat.getImage() + "\"></img>");
		out.println("<p>Name: " + cat.getName() + "<p>");
		int temp = Integer.parseInt(cat.getPrice());
		total = temp + total;
		}
		
		out.println("TOTAL: " + total);
		out.println("<form action=\"OrderHandler\" onsubmit=\"return validateForm()\" method=\"post\">");
		out.println("First name:<br>");
		out.println("<input type=\"text\" name=\"firstname\"><br>");
		out.println("Last name:<br>");
		out.println("<input type=\"text\" name=\"lastname\"><br>");
		out.println("Email:<br>");
		out.println("<input type=\"text\" name=\"email\"><br>");
		out.println("Street address:<br>");
		out.println("<input type=\"text\" name=\"streetaddress\"><br>");
		out.println("Zip code:<br>");
		out.println("<input type=\"text\" name=\"zipcode\"><br>");
		out.println("City:<br>");
		out.println("<input type=\"text\" name=\"city\"><br>");
		out.println("State:<br>");
		out.println("<input type=\"text\" name=\"state\"><br>");
		out.println("Phone number (digits only):<br>");
		out.println("<input type=\"text\" name=\"phonenumber\"><br>");
		out.println("Shipping type:<br>");
		out.println("<select name=\"shipping\">");
		out.println("<option value=\"overnight\">Overnight Shipping</option>");
		out.println("<option value=\"2day\">2 day Shipping</option>");
		out.println("<option value=\"6day\">6 day Shipping</option>");
		out.println("</select> <br><br>");
		out.println("Credit Card info<br>");
		out.println("Credit Card number (No spaces):<br>");
		out.println("<input type=\"text\" name=\"ccnum\"><br>");
		out.println("Name on card:<br>");
		out.println("<input type=\"text\" name=\"ccname\"><br>");
		out.println("Security number on card:<br>");
		out.println("<input type=\"password\" name=\"ccsnum\"><br><br>");
		out.println("<input type=\"submit\" value=\"Submit\">");
		out.println("<input type=\"reset\" value=\"Reset\">");
		out.println("</form>");
		out.println("</div>");
		out.println("</body>");
		out.println("<footer>");
		out.println("</footer>");
		out.println("</html>");
	}
	private static URI getBaseURI() {

	    //Change the URL here to make the client point to your service.
	    return UriBuilder.fromUri("http://andromeda-36.ics.uci.edu:5036/com.vogella.jersey.first").build();
	}


}


