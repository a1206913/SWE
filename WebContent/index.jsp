<%@ include file="header.jsp"%>

<%
	if (session.getAttribute("session_username") == null) {
		out.println("<h1 class='title' style='color:#7b9efb;'>FlightFinder. Login or Sign in</h1>");
	}

	else {
		out.println("<h1 class='title' style='color:#7b9efb;'>Eingellogt als " + session.getAttribute("session_username") + "</h1>");
	}
%>

<div class="line"></div>

<%@ include file="flugSuche.jsp"%>

<%@ include file="footer.jsp"%>