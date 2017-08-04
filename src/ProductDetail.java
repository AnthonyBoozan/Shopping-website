

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
import org.codehaus.jackson.type.TypeReference;
import org.glassfish.jersey.client.ClientConfig;

/**
 * Servlet implementation class ProductDetail
 */
@WebServlet("/kitten")
public class ProductDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection conn = DriverManager.getConnection("jdbc:mysql://sylvester-mccoy-v3.ics.uci.edu/inf124-db-000", "inf124-db-000", "b3g5exxF19bp");
		Statement stmt = conn.createStatement();*/
		String s = request.getParameter("id");
		
		//Restapi target
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
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("ItemHistory");
		rd.include(request, response);
		//ResultSet rs = stmt.executeQuery("SELECT name, price, gender, image, description FROM cats WHERE cat_id = " + s);
		PrintWriter out = response.getWriter();
		/* Remenants of Assignment 3
		String name = new String();
		String price = new String();
		String gender = new String();
		String description = new String();
		String image = new String();
		while(rs.next())
		{
			name = rs.getString("name");
			price = rs.getString("price");
			gender = rs.getString("gender");
			image = rs.getString("image");
			description = rs.getString("description");
		}*/
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Project 3 </title>");
		out.println("<link href=kittenstyles.css rel=stylesheet type=text/css>");
		out.println("<script type = \"text/Javascript\" src = \"kitten.js\">");
		out.println("</script>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div id = \"cart\">");
		HttpSession session = request.getSession();
		ArrayList<String> c = (ArrayList<String>)session.getAttribute("cart");
		if (c != null)
		{
			out.println("<p>Cart: " + c.size() + " </p>");
		}
		else
		{
			out.println("<p>Cart: 0</p>");
		}
		if(c.size() != 0)
		{
			out.println("<form action=\"FillOrder\" method=\"get\">");
		}
		else{
			out.println("<form action=index.jsp method=\"get\">");
		}
		out.println("<input type=\"submit\" value=\"Checkout\">");
		out.println("</form>");
		out.println("</div>");
		out.println("<script>");
		out.println("function bigImg(x) {");
		out.println("x.style.height = \"353px\";");
		out.println("x.style.width = \"578px\";");
		out.println("x.style.marginTop = \"-40px\";");
		out.println("}");
		out.println("function normalImg(x){");
		out.println(" x.style.height = \"282px\";");
		out.println("x.style.width = \"462px\";");
		out.println("x.style.marginTop = \"auto\";");
		out.println("}");
		out.println("</script>");
		out.println("<div id=\"main\">");
		out.println("<h1 id=\"header\">Kittens For All </h1>");
		out.println("<nav>");
		out.println("<a href=\"index.jsp\"> Home </a>");
		out.println("<a href=\"kittens_for_all.html\">About Us </a>");
		out.println("</nav>");
		out.println("<div id=\"imagearea\">");
		out.println("<img onmouseover=\"bigImg(this)\" onmouseout=\"normalImg(this)\" src=\"" + cat.getImage() + "\"></img>");
		out.println("</div>");
		out.println("<p class=\"info\" id=\"name\">Name: " + cat.getName() +"<p>");
		out.println("<p class=\"info\" id=\"price\">Gender: " + cat.getGender() +"<p>");
		out.println("<p class=\"info\" id=\"price\">Price: " + cat.getPrice() +"<p>");
		out.println("<p class=\"info\" id=\"description\">" + cat.getDescription() +"<p>");
		out.println("<form action=\"carthandler\" method=\"get\">");
		out.println("<input type=\"hidden\" name=\"id\" value = " + s + ">");
		out.println("<input type=\"submit\" value=\"Add to cart\" style=\"margin-left: 47em;\">");
		out.println("</form>");
		out.println("</div>");
		out.println("<footer>");
		out.println("</footer>");
		out.println("</body>");
		out.println("</html>");
    }
	private static URI getBaseURI() {

	    //Change the URL here to make the client point to your service.
	    return UriBuilder.fromUri("http://andromeda-36.ics.uci.edu:5036/com.vogella.jersey.first").build();
	}
}
