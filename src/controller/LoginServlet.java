package controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import entity.AbstractUser;

import java.util.ArrayList;
import java.util.Date;
import java.text.*;

import servicemanagement.*;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -2244732469487419265L;
	private ServiceManagement service = new ServiceManagement();
	private AbstractUser user;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		ServiceManagement service = new ServiceManagement();
		HttpSession session = request.getSession();
		String submitedValue = request.getParameter("login");

		if (submitedValue != null || submitedValue.equalsIgnoreCase("Einloggen")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			System.out.println("LoginServlet - check username: " + username + " " + password);
			user = service.getUser(username);
			boolean checkLogin = service.login(username, password);

			if (checkLogin) {
				String usertyp = service.getUserTyp(user);

				session.setAttribute("session_username", username);
				session.setAttribute("session_usertyp", usertyp);

				RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
				rd.include(request, response);
				
			} else {
				request.getRequestDispatcher("header.jsp").include(request, response);
				out.println("<h1 class='title'>Einloggen</h1><div class='line'></div>");
				out.println("<font color='red'>Login war nicht erfolgreich!</font>");
				request.getRequestDispatcher("footer.jsp").include(request, response);
			}
		}

	}
}