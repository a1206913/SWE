package controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.util.ArrayList;
import java.util.Date;
import java.text.*;

import servicemanagement.*;
import entity.*;

@WebServlet("/FlugeintragenServlet")
public class FlugeintragenServlet extends HttpServlet {

	String responseForFlugeintrag = "false";

	private static final long serialVersionUID = -2244732469487419265L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(); 
		response.setContentType("text/html");
		ServiceManagement service = new ServiceManagement();
		String submitValue = request.getParameter("submit");
		
		String resposeForRegistrierung = "false";
		
		boolean isDateValid = false;
		Flug newFlug = null;

		if (submitValue.equals("Flug erstellen")) {
			int flugnummer = checkInt(request.getParameter("flugnummer"), request, response, "Flugnummer");
			String von = request.getParameter("von");
			String nach = request.getParameter("nach");
			String abfahrt = request.getParameter("abfahrt");
			double preis = Double.parseDouble(request.getParameter("preis"));
			int platz = checkInt(request.getParameter("platz"), request, response, "Plätze");
			int flugDauer = checkInt(request.getParameter("flugDauer"), request, response, "Flugdauer");
			String terminal = request.getParameter("terminal");
//			String status = request.getParameter("status");
			String userName = (String) session.getAttribute("session_username");
			System.out.println("doPOst - flug erstellen " + userName);

//			falls ein Int oder Double Wert auf 0 ist, dann direkt die Fehlermeldung ausgeben
			if ((flugnummer != 0) || (preis != 0) || (platz != 0) || (flugDauer != 0)) {
				
				Date abfahrtDate = service.getDateWithHours(abfahrt);
				if (abfahrtDate != null) {
					isDateValid = true;
					String flugStatus = "available";
					
					newFlug = new Flug(flugnummer, von, nach, abfahrtDate, preis, platz, flugDauer, terminal, flugStatus, userName);
					resposeForRegistrierung = service.checkFlugErstellen(newFlug);
				}
				
				request.getRequestDispatcher("header.jsp").include(request, response);
				if (!isDateValid) {
					out.println("<font color='red'>The data format must be: DD-MM-YYYY HH:mm and the date has to be in the future!</font>");
				}
				
				else if(resposeForRegistrierung.equals("true")) {
					out.println("<h1 class='title'>Flug erstellen</h1><div class='line'></div>");
					out.println("<font color='green'>Flug wurde erfolgreich erstellt!</font>");
					service.createFlug(newFlug);
				}
				else {
					System.out.println("FlugeintragenServlet - in the else");
					out.println("<font color='red'>" + resposeForRegistrierung + "</font>");
				}
				
				request.getRequestDispatcher("footer.jsp").include(request, response);
			}
		}
	}
	
	public int checkInt(String intWert, HttpServletRequest request, HttpServletResponse response, String feldName) throws ServletException, IOException {
		int asInt = 0;
		PrintWriter out = response.getWriter();
		
		try {
			asInt = Integer.parseInt(intWert);
			if (asInt == 0) {
				request.getRequestDispatcher("header.jsp").include(request, response);
				out.println("<font color='red'>Das Feld " + feldName + " ist ein Ganzzahl, größer als 0 verlangt</font>");
			}
		}
		catch (NumberFormatException ex) {
			out.println("<font color='red'>In das Feld " + feldName + " ist ein Ganzzahl verlangt</font>");
			ex.printStackTrace();
		}
		finally {
			request.getRequestDispatcher("footer.jsp").include(request, response);
		}
		return asInt;
	}
	
	public double checkDouble(String doubleWert, HttpServletRequest request, HttpServletResponse response, String feldName) throws ServletException, IOException {
		double asDouble = 0;
		
		PrintWriter out = response.getWriter();
		
		try {
			asDouble = Double.parseDouble(doubleWert);
			if (asDouble == 0) {
				request.getRequestDispatcher("header.jsp").include(request, response);
				out.println("<font color='red'>In das Feld " + feldName + " ist ein Double, größer als 0 verlangt</font>");
			}
		}
		catch (NumberFormatException ex) {
			out.println("<font color='red'>In das Feld " + feldName + " ist ein Ganzzahl verlangt</font>");
			ex.printStackTrace();
		}
		finally {
			request.getRequestDispatcher("footer.jsp").include(request, response);
		}
		return asDouble;
	}

}