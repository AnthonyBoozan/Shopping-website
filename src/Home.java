import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Blob;
import com.mysql.jdbc.Driver;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    		PrintWriter out = response.getWriter();
    		Connection conn = DriverManager.getConnection("jdbc:mysql://sylvester-mccoy-v3.ics.uci.edu/inf124-db-000", "inf124-db-000", "b3g5exxF19bp");
        	Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT name, price, image FROM cats WHERE cat_id = 1");
			String name = new String();
			String price = new String();
			String image = new String();
			while(rs.next())
			{
				name = rs.getString("name");
				price = rs.getString("price");
				image = rs.getString("image");
			}
			HttpSession session = request.getSession();
			ArrayList<String> c = (ArrayList<String>)session.getAttribute("cart");
			ArrayList<String> viewhistory = (ArrayList<String>)session.getAttribute("history");
			if (viewhistory == null)
			{
				viewhistory = new ArrayList<String>();
				session.setAttribute("history", viewhistory);
			}
			if (c == null)
			{
				c = new ArrayList<String>();
				session.setAttribute("cart", c);
			}
			session.setAttribute("history", viewhistory);
			session.setAttribute("cart", c);
			viewhistory = (ArrayList<String>)session.getAttribute("history");
			out.println("<html>");
        	out.println("<head>");
        	out.println("<title> Project 3 </title>");
        	out.println("<link href=homecss.css rel=stylesheet type=text/css>");
        	out.println("<script type = \"text/Javascript\" src = \"kitten.js\">");
        	out.println("</script>");
        	out.println("</head>");
        	out.println("<body>");
        	if(viewhistory != null)
        	{
        		out.println("<div id = \"history\">");
            	out.println("<p> History </p>");
            	out.println("<p>" + viewhistory.size() + "</p>");
            	for(int i = 0; i < viewhistory.size(); i++)
            	{
            		stmt = conn.createStatement();
            		String s = viewhistory.get(i);
        			rs = stmt.executeQuery("SELECT name, price, image FROM cats WHERE cat_id = " + s);
        			while(rs.next())
        			{
        				name = rs.getString("name");
        				image = rs.getString("image");
        			}
        			out.println("<a href = \"kitten?id=" + s + "\">");
        			out.println("<img src=\"" + image + "\"></img>");
        			out.println("<p>Name: " + name + "<p>");
        			out.println("</a>");
            	}
            	out.println("</div>");
        	}
        	out.println("<div id = \"cart\">");
        	out.println("<p>Cart: " + c.size() + "</p>");
        	if(c.size() != 0)
        	{
        		out.println("<form action=\"FillOrder\" method=\"get\">");
        	}
        	else{
        		out.println("<form action=\"Home\" method=\"get\">");
        	}
        	out.println("<input type=\"submit\" value=\"Checkout\">");
        	out.println("</form>");
        	out.println("</div>");
        	out.println("<div id=\"main\">");
        	out.println("<h1 id=\"header\">Kittens For All </h1>");
        	out.println("<nav>");
        	out.println("<a href=\"Home\"> Home </a>");
        	out.println("<a href=\"about_us.html\">About Us </a>");
        	out.println("</nav>");
        	out.println("<table>");
        	out.println("<tbody>");
        	out.println("<tr>");
        	out.println("<td>");
        	out.println("<a href = \"kitten?id=1\">");
        	stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT name, price, image FROM cats WHERE cat_id = 1");
			while(rs.next())
			{
				name = rs.getString("name");
				price = rs.getString("price");
				image = rs.getString("image");
			}
        	out.println("<img src=\"" + image + "\"></img>");
			out.println("<p>Name: " + name + "</p>");
			out.println("<p>Price:" + price + "</p>");
			out.println("</a>");
			out.println("</td>");
			out.println("<td>");
			out.println("<a href = \"kitten?id=2\">");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT name, price, image FROM cats WHERE cat_id = 2");
			while(rs.next())
			{
				name = rs.getString("name");
				price = rs.getString("price");
				image = rs.getString("image");
			}
			out.println("<img src=\"" + image + "\"></img>");
			out.println("<p>Name: " + name + "</p>");
			out.println("<p>Price:" + price + "</p>");
			out.println("</a>");
			out.println("</td>");
			out.println("<td>");
			out.println("<a href = \"kitten?id=3\">");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT name, price, image FROM cats WHERE cat_id = 3");
			while(rs.next())
			{
				name = rs.getString("name");
				price = rs.getString("price");
				image = rs.getString("image");
			}
			out.println("<img src=\"" + image + "\"></img>");
			out.println("<p>Name: " + name + "</p>");
			out.println("<p>Price:" + price + "</p>");
			out.println("</a>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>");
			out.println("<a href = \"kitten?id=4\">");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT name, price, image FROM cats WHERE cat_id = 4");
			while(rs.next())
			{
				name = rs.getString("name");
				price = rs.getString("price");
				image = rs.getString("image");
			}
			out.println("<img src=\"" + image + "\"></img>");
			out.println("<p>Name: " + name + "</p>");
			out.println("<p>Price:" + price + "</p>");
			out.println("</a>");
			out.println("</td>");
			out.println("<td>");
			out.println("<a href = \"kitten?id=5\">");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT name, price, image FROM cats WHERE cat_id = 5");
			while(rs.next())
			{
				name = rs.getString("name");
				price = rs.getString("price");
				image = rs.getString("image");
			}
			out.println("<img src=\"" + image + "\"></img>");
			out.println("<p>Name: " + name + "</p>");
			out.println("<p>Price:" + price + "</p>");
			out.println("</a>");
			out.println("</td>");
			out.println("<td>");
			out.println("<a href = \"kitten?id=6\">");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT name, price, image FROM cats WHERE cat_id = 6");
			while(rs.next())
			{
				name = rs.getString("name");
				price = rs.getString("price");
				image = rs.getString("image");
			}
			out.println("<img src=\"" + image + "\"></img>");
			out.println("<p>Name: " + name + "</p>");
			out.println("<p>Price:" + price + "</p>");
			out.println("</a>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>");
			out.println("<a href = \"kitten?id=7\">");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT name, price, image FROM cats WHERE cat_id = 7");
			while(rs.next())
			{
				name = rs.getString("name");
				price = rs.getString("price");
				image = rs.getString("image");
			}
			out.println("<img src=\"" + image + "\"></img>");
			out.println("<p>Name: " + name + "</p>");
			out.println("<p>Price:" + price + "</p>");
			out.println("</a>");
			out.println("</td>");
			out.println("<td>");
			out.println("<a href = \"kitten?id=8\">");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT name, price, image FROM cats WHERE cat_id = 8");
			while(rs.next())
			{
				name = rs.getString("name");
				price = rs.getString("price");
				image = rs.getString("image");
			}
			out.println("<img src=\"" + image + "\"></img>");
			out.println("<p>Name: " + name + "</p>");
			out.println("<p>Price:" + price + "</p>");
			out.println("</a>");
			out.println("</td>");
			out.println("<td>");
			out.println("<a href = \"kitten?id=9\">");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT name, price, image FROM cats WHERE cat_id = 9");
			while(rs.next())
			{
				name = rs.getString("name");
				price = rs.getString("price");
				image = rs.getString("image");
			}
			out.println("<img src=\"" + image + "\"></img>");
			out.println("<p>Name: " + name + "</p>");
			out.println("<p>Price:" + price + "</p>");
			out.println("</a>");
			out.println("</td>");
			out.println("<tr>");
			out.println("</tr>");
			out.println("<td>");
			out.println("<a href = \"kitten?id=10\">");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT name, price, image FROM cats WHERE cat_id = 10");
			while(rs.next())
			{
				name = rs.getString("name");
				price = rs.getString("price");
				image = rs.getString("image");
			}
			out.println("<img src=\"" + image + "\"></img>");
			out.println("<p>Name: " + name + "</p>");
			out.println("<p>Price:" + price + "</p>");
			out.println("</a>");
			out.println("</td>");
			out.println("<td>");
			out.println("<a href = \"kitten?id=11\">");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT name, price, image FROM cats WHERE cat_id = 11");
			while(rs.next())
			{
				name = rs.getString("name");
				price = rs.getString("price");
				image = rs.getString("image");
			}
			out.println("<img src=\"" + image + "\"></img>");
			out.println("<p>Name: " + name + "</p>");
			out.println("<p>Price:" + price + "</p>");
			out.println("</a>");
			out.println("</td>");
			out.println("<td>");
			out.println("<a href = \"kitten?id=12\">");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT name, price, image FROM cats WHERE cat_id = 12");
			while(rs.next())
			{
				name = rs.getString("name");
				price = rs.getString("price");
				image = rs.getString("image");
			}
			out.println("<img src=\"" + image + "\"></img>");
			out.println("<p>Name: " + name + "</p>");
			out.println("<p>Price:" + price + "</p>");
			out.println("</a>");
			out.println("</td>");
			out.println("</tr>");
			out.println("</tbody>");
			out.println("</table>");
			out.println("</div>");
			out.println("</body>");
			out.println("<footer>");
			out.println("</footer>");
			out.println("</html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PrintWriter out = response.getWriter();
	    	out.println("Error");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PrintWriter out = response.getWriter();
	    	out.println("2");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			PrintWriter out = response.getWriter();
	    	out.println("3");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			PrintWriter out = response.getWriter();
	    	out.println("4");
			e.printStackTrace();
		}
    }
}
