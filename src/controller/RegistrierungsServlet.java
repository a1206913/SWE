package controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.util.Date;
import java.text.*;

import entity.*;
import servicemanagement.*;

@WebServlet(urlPatterns = { "/RegistrierungsServlet" })
public class RegistrierungsServlet extends HttpServlet {

	private static final long serialVersionUID = -2244732469487419265L;
	private ServiceManagement service = new ServiceManagement();
	AbstractUser newUser;
	
	String resposeForRegistrierung = "false";
	
	public RegistrierungsServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		response.setContentType("text/html");

		System.out.println("doPost() method in RegistrierungsServlet");
//		HttpSession session = request.getSession();
		boolean isDateValid = false;
		String submitValue = request.getParameter("registertyp");

		if (submitValue.equals("benutzer")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String vorname = request.getParameter("vorname");
			String nachname = request.getParameter("nachname");
			String geburtsdatum = request.getParameter("geburtsdatum");

			if (service.getDate(geburtsdatum) != null) {
				Date geburtsdatumDate =  service.getDate(geburtsdatum);
				isDateValid = true;
				newUser = new Benutzer(username, password, email, vorname, nachname, geburtsdatumDate);
				resposeForRegistrierung = service.checkRegistrierung(newUser);
			}


			request.getRequestDispatcher("header.jsp").include(request, response);
			out.println("<h1 class='title'>Registrierung</h1><div class='line'></div>");
			
			
			if (!isDateValid) {
				out.println("<font color='red'>The data format must be: DD-MM-YYYY!</font>");
			}
			else if (resposeForRegistrierung.equals("true")) {
//				all the validation passed, we can serialize the user 
				service.registerUser(newUser);
				
//				redirect User on Index page and schow him the congratulation message
//				session.setAttribute("session_user", username);
				//				response.sendRedirect("index.jsp");
				out.println("<font color='green'>Gratulation " + username + ", du bist jetzt ein FlightFinder Mitglied!</font>");
//				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
//            rd.forward(request, response);
				
			} 

			else {
//				out.println("<font color='red'><%=resposeForRegistrierung%></font>");
//				out.println("<font color='red'>Registrierung war nicht erfolgreich!</font>");
				out.println("<font color='red'>" + resposeForRegistrierung + "</font>");
			}
			request.getRequestDispatcher("footer.jsp").include(request, response);
		}

		else if (submitValue.equals("fluggesellschaft")) {
			String username = request.getParameter("username");
			System.out.println("FlugGes username: " + username);
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String name = request.getParameter("name");
			String adresse = request.getParameter("adresse");
			String telefon = request.getParameter("telefon");

			AbstractUser newUser = new Fluggesellschaft(username, password, email, name, adresse, telefon);
			resposeForRegistrierung = service.checkRegistrierung(newUser);

			request.getRequestDispatcher("header.jsp").include(request, response);
			out.println("<h1 class='title'>Registrierung</h1><div class='line'></div>");

			if (resposeForRegistrierung.equals("true")) {
//				all the validation passed, we can serialize the user 
				service.registerUser(newUser);
				
				out.println("<font color='green'>Registrierung war erfolgreich!</font>");
			} 
			else {
//				out.println("<font color='red'>Registrierung war nicht erfolgreich!</font>");
				out.println("<font color='red'>" + resposeForRegistrierung + "</font>");
			}
			request.getRequestDispatcher("footer.jsp").include(request, response);
		}

		else if (submitValue.equals("statistiker")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String telefon = request.getParameter("telefon");

			AbstractUser newUser = new Statistiker(username, password, email, telefon);
			
//			check if the registration form is valid and put the result in a string to pass it to the jsp file
			resposeForRegistrierung = service.checkRegistrierung(newUser);

			request.getRequestDispatcher("header.jsp").include(request, response);
			out.println("<h1 class='title'>Registrierung</h1><div class='line'></div>");

			if (resposeForRegistrierung.equals("true")) {
//				all the validation passed, we can serialize the user 
				service.registerUser(newUser);
				
				out.println("<font color='green'>Registrierung war erfolgreich!</font>");
			} 
			else {
				out.println("<font color='red'>" + resposeForRegistrierung + "</font>");
			}
			request.getRequestDispatcher("footer.jsp").include(request, response);
		}

	}

}