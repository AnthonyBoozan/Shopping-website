<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import = "org.codehaus.jackson.map.ObjectMapper,
org.codehaus.jackson.type.TypeReference,org.glassfish.jersey.client.ClientConfig" %>
<%@ page import = " javax.ws.rs.client.Client,
 javax.ws.rs.client.ClientBuilder,
 javax.ws.rs.client.WebTarget,
 javax.ws.rs.core.MediaType,
 javax.ws.rs.core.UriBuilder,
 java.net.URI" %> 
 <%@ page import ="com.uci.a4.*" %>
 <%! private static URI getBaseURI() {

	    //Change the URL here to make the client point to your service.
	    return UriBuilder.fromUri("http://andromeda-36.ics.uci.edu:5036/com.vogella.jersey.first").build();
	} %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head></head>
    <title>Project 4</title>
    <link href=homecss.css rel=stylesheet type=text/css>
    <script type = \"text/Javascript\" src = \"kitten.js\">
	</script>
    </head>
    <body>
    <%
    int tracker = 1;
    ClientConfig configg = new ClientConfig();

    Client client = ClientBuilder.newClient(configg);

    WebTarget target = client.target(getBaseURI());


    String jsonResponse =
            target.path("rest").path("cat").path("all").
                    request(). //send a request
                    accept(MediaType.APPLICATION_JSON). //specify the media type of the response
                    get(String.class); // use the get method and return the response as a string

    System.out.println(jsonResponse);

    ObjectMapper objectMapper = new ObjectMapper(); // This object is from the jackson library

    List<Cat> catList = objectMapper.readValue(jsonResponse, new TypeReference<List<Cat>>(){});
    
    //Cat cat = catList.get(0);
    //String name = cat.getName();
    
    session = request.getSession();
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
	int historysize = viewhistory.size();
	if(viewhistory != null)
	{ %>
		<div id = history>
    	<p> History </p>
    	<p> <%= historysize %> </p>
    	
    	<% for(int i = 0; i < viewhistory.size(); i++)
    	{
    		// s here is a number in the format of a string
    		String s = viewhistory.get(i);
    		Cat cat = new Cat();
    		
    		for(int x = 0; x < catList.size(); x++ )
    		{
    			if(s.equals(catList.get(x).getCat_id()))
    			{
    				cat = catList.get(x);
    				break;
    			}
    		}
    		
    		
			out.println("<a href = \"kitten?id=" + s + "\">");
		    String name = cat.getName();
		    String image = cat.getImage();
		    %>
			<img src="<%= image %>"></img>
			<p>Name: <%= name %><p>
			</a>
    	<%} %> 
    	</div>
	<%} %>
    <div id = cart>
    	<p>Cart: <%= c.size() %></p>
    	<%if(c.size() != 0)
    	{
    		out.println("<form action=\"FillOrder\" method=\"get\">");
    	}
    	else{
    		out.println("<form action=index.jsp method=\"get\">");
    	} %>
    	<input type=submit value=Checkout>
    	</form>
    	</div>
    	<div id=main>
    	<h1 id=header>Kittens For All </h1>
    	<nav>
    	<a href=index.jsp> Home </a>
    	<a href=kittens_for_all.html>About Us </a>
    	</nav>
    	<table>
    	<tbody>
    	<tr>
    	<td>
    	<a href = kitten?id=<%=tracker %>>
    	<%
    	String s = Integer.toString(tracker);
		Cat cat = new Cat();
    	for(int x = 0; x < catList.size(); x++ )
		{
			if(s.equals(catList.get(x).getCat_id()))
			{
				cat = catList.get(x);
				out.println("<img src=\"" + cat.getImage() + "\"></img>");
				out.println("<p>Name: " + cat.getName() + "</p>");
				out.println("<p>Price: $" + cat.getPrice() + "</p>");
				break;
			}
		}
    	tracker++;
		%>
		</a>
		</td>
		<td>
		<a href = kitten?id=<%=tracker %>>
		<%
    	s = Integer.toString(tracker);
		cat = new Cat();
    	for(int x = 0; x < catList.size(); x++ )
		{
			if(s.equals(catList.get(x).getCat_id()))
			{
				cat = catList.get(x);
				out.println("<img src=\"" + cat.getImage() + "\"></img>");
				out.println("<p>Name: " + cat.getName() + "</p>");
				out.println("<p>Price: $" + cat.getPrice() + "</p>");
				break;
			}
			
		}
    	tracker++;
		%>
		</a>
		</td>
		<td>
		<a href = kitten?id=<%=tracker %>>
		<%
    	s = Integer.toString(tracker);
		cat = new Cat();
    	for(int x = 0; x < catList.size(); x++ )
		{
			if(s.equals(catList.get(x).getCat_id()))
			{
				cat = catList.get(x);
				out.println("<img src=\"" + cat.getImage() + "\"></img>");
				out.println("<p>Name: " + cat.getName() + "</p>");
				out.println("<p>Price: $" + cat.getPrice() + "</p>");
				break;
			}
		}
    	tracker++;
		%>
		</a>
		</td>
		</tr>
		<tr>
		<td>
		<a href = kitten?id=<%=tracker %>>
		<%
    	s = Integer.toString(tracker);
		cat = new Cat();
    	for(int x = 0; x < catList.size(); x++ )
		{
			if(s.equals(catList.get(x).getCat_id()))
			{
				cat = catList.get(x);
				out.println("<img src=\"" + cat.getImage() + "\"></img>");
				out.println("<p>Name: " + cat.getName() + "</p>");
				out.println("<p>Price: $" + cat.getPrice() + "</p>");
				break;
			}
		}
    	tracker++;
    	%>
    	</a>
    	</td>
    	<td>
    	<a href = kitten?id=<%=tracker %>>
		<%
    	s = Integer.toString(tracker);
		cat = new Cat();
    	for(int x = 0; x < catList.size(); x++ )
		{
			if(s.equals(catList.get(x).getCat_id()))
			{
				cat = catList.get(x);
				out.println("<img src=\"" + cat.getImage() + "\"></img>");
				out.println("<p>Name: " + cat.getName() + "</p>");
				out.println("<p>Price: $" + cat.getPrice() + "</p>");
				break;
			}
		}
    	tracker++;
    	%>
    	</a>
    	</td>
    	<td>
    	<a href = kitten?id=<%=tracker %>>
		<%
    	s = Integer.toString(tracker);
		cat = new Cat();
    	for(int x = 0; x < catList.size(); x++ )
		{
			if(s.equals(catList.get(x).getCat_id()))
			{
				cat = catList.get(x);
				out.println("<img src=\"" + cat.getImage() + "\"></img>");
				out.println("<p>Name: " + cat.getName() + "</p>");
				out.println("<p>Price: $" + cat.getPrice() + "</p>");
				break;
			}
		}
    	tracker++;
    	%>
    	</a>
    	</td>
    	</tr>
    	<tr>
    	<td>
    	<a href = kitten?id=<%=tracker %>>
		<%
    	s = Integer.toString(tracker);
		cat = new Cat();
    	for(int x = 0; x < catList.size(); x++ )
		{
			if(s.equals(catList.get(x).getCat_id()))
			{
				cat = catList.get(x);
				out.println("<img src=\"" + cat.getImage() + "\"></img>");
				out.println("<p>Name: " + cat.getName() + "</p>");
				out.println("<p>Price: $" + cat.getPrice() + "</p>");
				break;
			}
		}
    	tracker++;
    	%>
    	</a>
    	</td>
    	<td>
    	<a href = kitten?id=<%=tracker %>>
		<%
    	s = Integer.toString(tracker);
		cat = new Cat();
    	for(int x = 0; x < catList.size(); x++ )
		{
			if(s.equals(catList.get(x).getCat_id()))
			{
				cat = catList.get(x);
				out.println("<img src=\"" + cat.getImage() + "\"></img>");
				out.println("<p>Name: " + cat.getName() + "</p>");
				out.println("<p>Price: $" + cat.getPrice() + "</p>");
				break;
			}
		}
    	tracker++;
    	%>
    	</a>
    	</td>
    	<td>
    	<a href = kitten?id=<%=tracker %>>
		<%
    	s = Integer.toString(tracker);
		cat = new Cat();
    	for(int x = 0; x < catList.size(); x++ )
		{
			if(s.equals(catList.get(x).getCat_id()))
			{
				cat = catList.get(x);
				out.println("<img src=\"" + cat.getImage() + "\"></img>");
				out.println("<p>Name: " + cat.getName() + "</p>");
				out.println("<p>Price: $" + cat.getPrice() + "</p>");
				break;
			}
		}
    	tracker++;
    	%>
    	</a>
    	</td>
    	</tr>
    	<tr>
    	<td>
    	<a href = kitten?id=<%=tracker %>>
		<%
    	s = Integer.toString(tracker);
		cat = new Cat();
    	for(int x = 0; x < catList.size(); x++ )
		{
			if(s.equals(catList.get(x).getCat_id()))
			{
				cat = catList.get(x);
				out.println("<img src=\"" + cat.getImage() + "\"></img>");
				out.println("<p>Name: " + cat.getName() + "</p>");
				out.println("<p>Price: $" + cat.getPrice() + "</p>");
				break;
			}
		}
    	tracker++;
    	%>
    	</a>
    	</td>
    	<td>
    	<a href = kitten?id=<%=tracker %>>
		<%
    	s = Integer.toString(tracker);
		cat = new Cat();
    	for(int x = 0; x < catList.size(); x++ )
		{
			if(s.equals(catList.get(x).getCat_id()))
			{
				cat = catList.get(x);
				out.println("<img src=\"" + cat.getImage() + "\"></img>");
				out.println("<p>Name: " + cat.getName() + "</p>");
				out.println("<p>Price: $" + cat.getPrice() + "</p>");
				break;
			}
		}
    	tracker++;
    	%>
    	</a>
    	</td>
    	<td>
    	<a href = kitten?id=<%=tracker %>>
		<%
    	s = Integer.toString(tracker);
		cat = new Cat();
    	for(int x = 0; x < catList.size(); x++ )
		{
			if(s.equals(catList.get(x).getCat_id()))
			{
				cat = catList.get(x);
				out.println("<img src=\"" + cat.getImage() + "\"></img>");
				out.println("<p>Name: " + cat.getName() + "</p>");
				out.println("<p>Price: $" + cat.getPrice() + "</p>");
				break;
			}
		}
    	tracker++;
    	%>
    	</a>
    	</td>
    	</tr>
    	</tbody>
    	</table>
    </div>
    </body>
    <footer>
    </footer>
</html>