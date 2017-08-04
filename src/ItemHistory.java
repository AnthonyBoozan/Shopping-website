

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ItemHistory
 */
@WebServlet("/ItemHistory")
public class ItemHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ArrayList<String> viewhistory = (ArrayList<String>) session.getAttribute("history");
		String s = request.getParameter("id");
			session.setAttribute("history", viewhistory);
			if(viewhistory.size() >= 5)
			{
				viewhistory.remove(0);
				viewhistory.add(s);
				session.setAttribute("history", viewhistory);
			}
			else
			{
				viewhistory.add(s);
				session.setAttribute("history", viewhistory);
			}
	}
	
}
