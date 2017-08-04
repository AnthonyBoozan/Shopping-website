

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.server.JSONP;

import com.uci.a4.Customer;

/**
 * Servlet implementation class OrderHandler
 */
@WebServlet("/OrderHandler")
public class OrderHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderHandler() {
        super();
        // TODO Auto-generated constructor stub
    }


	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<String> c = (ArrayList<String>) session.getAttribute("cart");
		
		
		try {
    		Class.forName("com.mysql.jdbc.Driver").newInstance();
    		PrintWriter out = response.getWriter();
    		Connection conn = DriverManager.getConnection("jdbc:mysql://sylvester-mccoy-v3.ics.uci.edu/inf124-db-000", "inf124-db-000", "b3g5exxF19bp");
        	Statement stmt = conn.createStatement();
			String fn = request.getParameter("firstname");
        	String ln = request.getParameter("lastname");
        	String em = request.getParameter("email");
        	String sa = request.getParameter("streetaddress");
        	String zc = request.getParameter("zipcode");
        	String cty = request.getParameter("city");
        	String sta = request.getParameter("state");
        	String phone = request.getParameter("phonenumber");
        	String ship = request.getParameter("shipping");
        	String ccn = request.getParameter("ccnum");
        	String ncc = request.getParameter("ccname");
        	String ss = request.getParameter("ccsnum");
        	
        	String input = "{\"firstname\": \"" + fn + "\", " + "\"lastname\": \"" + ln + "\", "  + "\"streetaddress\": \"" + sa + "\", "  + "\"city\": \"" + cty + "\", "  + "\"state\": \"" + sta + "\", "  + 
        			"\"zipcode\": \"" + zc + "\", "  + "\"phonenumber\": \"" + phone + "\", "  + "\"ccnum\": \"" + ccn + "\", "  + "\"ccname\": \"" + ncc + "\", "  + "\"ccsnum\": \"" + ss + "\", "  + "\"email\": \"" + em + "\"}";
        	
        	
			
        	ClientConfig config = new ClientConfig();

    		Client client = ClientBuilder.newClient(config);

    		WebTarget target = client.target(getBaseURI());
    		
    		
    		target.path("rest").path("customer").
            request().post(Entity.entity(input, "APPLICATION/JSON"));
    		
        	
        	/*ResultSet ras = s.executeQuery("SELECT MAX(order_id) FROM orders");
        	int newOID = 0;
			if(ras.next())
			{
				newOID = ras.getInt("order_id");
			}*/
        	//stmt.executeUpdate("INSERT INTO customer( firstname, lastname, streetaddress, city, state, zipcode, phonenumber, ccnum, ccname, ccsnum, email ) VALUE('JEFO','f','f','f','f',3,3,33333333333333,'f',333,'fdsafdsafDDDDDDa'");
        	
        	/*s.executeUpdate("INSERT INTO customer(firstname,lastname,streetaddress,city,state,zipcode,phonenumber,ccnum,ccname,ccnum,email) VALUE "
        			+ "('"+fn+"','"+ln+"','"+sa+"',"+cty+",'"+sta+"','"+zc+"','"+phone+"','"+ccn+"','"+ncc+"','"+ss+"','"+em+"')");*/
        	
        	/*for(int i = 0; i < c.size(); i++)
        	{
        		newOID = newOID + i;
        		stmt.executeUpdate ("INSERT INTO orders" + "VALUES (" + newOID + "," + ship + "," + c.get(i) + "," + em + ")");
        	}*/
        	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/OrderComplete");
        	dispatcher.forward(request, response);
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
	private static URI getBaseURI() {

	    //Change the URL here to make the client point to your service.
	    return UriBuilder.fromUri("http://andromeda-36.ics.uci.edu:5036/com.vogella.jersey.first").build();
	}
	

}
