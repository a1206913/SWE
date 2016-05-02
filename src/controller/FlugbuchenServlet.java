package controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.util.ArrayList;
import java.util.Date;
import java.text.*;

import entity.*;
import servicemanagement.*;

@WebServlet("/FlugbuchenServlet")
public class FlugbuchenServlet extends HttpServlet {

	private static final long serialVersionUID = -2244732469487419265L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		ServiceManagement service = new ServiceManagement();
		HttpSession session = request.getSession();
		String submitValue = request.getParameter("submit");

		if (submitValue.equals("Flug buchen")) {
			int flugnummer = Integer.parseInt(request.getParameter("flugnummer"));
			String username = request.getParameter("username");

			Flug flug = service.getFlug(flugnummer);
			Benutzer benutzer = (Benutzer) service.getUser(username);
			String status = "ok";

			request.getRequestDispatcher("header.jsp").include(request, response);
			Buchung newBuchung = new Buchung(benutzer, flug, status);
			if (service.checkSeat(flugnummer) > 0) {
				boolean checkBuchung = service.createBuchung(newBuchung);
				out.println("<h1 class='title'>Flug buchen</h1><div class='line'></div>");

				if (service.checkSeat(flugnummer) == 0) {
					System.out.println("in the checkSeat() - FLugbuchenServlet");
					synchronized(this) { 
						service.changeFlugStatus(flugnummer, "booked");
					}
				}
				
				if (checkBuchung) {
					out.println("<font color='green'>Flug wurde erfolgreich gebucht!</font>");
				}
				
			}

			else {
//				out.println("<font color='red'>Leider gibt es keine freie Plätze mehr!</font>");
				out.println("<font color='red'>Flug buchen war nicht erfolgreich!</font>");
			}
			request.getRequestDispatcher("footer.jsp").include(request, response);
		}

	}

}