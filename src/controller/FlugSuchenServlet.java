package controller;

import java.io.IOException;

import servicemanagement.*;
import entity.*;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.corba.se.spi.protocol.RequestDispatcherRegistry;

/**
 * Servlet implementation class FlugSuchen
 */
@WebServlet("/FlugSuchenServlet")
public class FlugSuchenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlugSuchenServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ServiceManagement service = new ServiceManagement();
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("session_username");
		System.out.println("FlugSuchenServlet username: " + userName);
		
		String abFlug = request.getParameter("flugVon");
		String flugZiel = request.getParameter("flugNach");
		
		ArrayList<Flug> searchedTowns = service.searchFlugByTown(abFlug, flugZiel);
//		request.getRequestDispatcher("header.jsp").include(request, response);
//		put the Flug ArrayList witch contains only the searched results in the dispatcher
		if (!searchedTowns.isEmpty()) {
			request.setAttribute("townList", searchedTowns);
			request.setAttribute("session_username", userName);
			AbstractUser abstractUser = service.getUser(userName);
			if (abstractUser != null) {
				request.setAttribute("userType", service.getUserTyp(abstractUser));
			}	
//			out.println("<h2>Folgende Ergebnisse entsprechen ihre Kriterien: </h2>");
			
//		request.getRequestDispatcher("footer.jsp").include(request, response);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/flugSucheResults.jsp");
			rd.include(request, response);
//			request.getRequestDispatcher("/flugSucheResults.jsp").include(request, response);
		}
		else {
			out.println("Keine Ergebnisse wurden gefunden");
		}
	}
}
