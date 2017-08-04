

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

/**
 * Servlet implementation class OrderComplete
 */
@WebServlet("/OrderComplete")
public class OrderComplete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ArrayList<String> c = (ArrayList<String>) session.getAttribute("cart");
		
		
		PrintWriter out = response.getWriter();
		String fn = request.getParameter("firstname");
		String ln = request.getParameter("lastname");
		String em = request.getParameter("email");
		String sa = request.getParameter("streetaddress");
		String zc = request.getParameter("zipcode");
		String cty = request.getParameter("city");
		String sta = request.getParameter("state");
		String phone = request.getParameter("phonenumber");
		String ship = request.getParameter("shipping");
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Project 3 </title>");
		out.println("<link href=kittenstyles.css rel=stylesheet type=text/css>");
		out.println("<script type = \"text/Javascript\" src = \"kitten.js\">");
		out.println("</script>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div id=\"main\">");
		out.println("<h1 id=\"header\">Kittens For All </h1>");
		out.println("<nav>");
		out.println("<a href=index.jsp> Home </a>");
		out.println("<a href=kittens_for_all>About Us </a>");
		out.println("<p>ORDER COMPELETE </p>");
		out.println("<p>Firstname: " + fn + "</p>");
		out.println("<p>Lastname: " + ln + "</p>");
		out.println("<p>Email: " + em + "</p>");
		out.println("<p>Street address:" + sa + "</p>");
		out.println("<p>ZIP: " +zc + "</p>");
		out.println("<p>City: " + cty + "</p>");
		out.println("<p>State: " + sta + "</p>");
		out.println("<p>Phone number: " + phone + "</p>");
		out.println("<p>Shipping: " + ship + "</p>");
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
		int temp = Integer.parseInt(cat.getPrice());
		total = temp + total;
		}
			
		
		out.println("<p>TOTAL: $" + total + "</p>");
		
		out.println("</nav>");
		out.println("</div>");
		out.println("<footer>");
		out.println("</footer>");
		out.println("</body>");
		out.println("</html>");
		c.clear();
	}
	private static URI getBaseURI() {

	    //Change the URL here to make the client point to your service.
	    return UriBuilder.fromUri("http://andromeda-36.ics.uci.edu:5036/com.vogella.jersey.first").build();
	}

}

