<%@page import="java.io.PrintStream"%>
<%@page import="servicemanagement.ServiceManagement"%>
<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="entity.Flug"%>
<%@ include file="header.jsp"%>

<h1 class="title">Flugliste</h1>
<div class="line"></div>


<%
	// if session inactivity > 1700 seconds, log the user out   

	ServiceManagement service = new ServiceManagement();
	ArrayList<Flug> flugList = service.getFlugList();
	String username = (String) session.getAttribute("session_username");

	if (flugList.isEmpty()) {
		out.println("<h3 style='color:green;'>Es gibt keine Flüge!</h3>");
	} 
	else {
		int lastIndex = (flugList.size() - 1);
		for (int i = lastIndex; i >= 0; i--) {
			int flugNummer = flugList.get(i).getFlugnummer();
			if (flugList.get(i).getStatus().equals("available")) {
%>
<table>
	<tr>
		<td>Flugnummer:</td>
		<td><%=flugNummer%></td>
	</tr>
	<tr>
		<td>Freie Plätze:</td>
		<td><%=service.checkSeat(flugNummer)%></td>
	</tr>
	<tr>
		<td>Flug von:</td>
		<td><%=flugList.get(i).getVon()%></td>
	</tr>
	<tr>
		<td>Flug nach:</td>
		<td><%=flugList.get(i).getNach()%></td>
	</tr>
	<tr>
		<td>Datum der Abfahrt:</td>
		<td><%=flugList.get(i).getAbfahrt()%></td>
	</tr>
	<tr>
		<td>Preis:</td>
		<td><%=flugList.get(i).getPreis()%></td>
	</tr>
	<tr>
		<td>Flugdauer:</td>
		<%
		int hours = flugList.get(i).getFlugDauer() / 60;
		int minutes = flugList.get(i).getFlugDauer() % 60;
		%>
		<td><%=hours + " St. : " + minutes + " Min."%></td>
	</tr>
	<tr>
		<td>Terminal:</td>
		<td><%=flugList.get(i).getTerminal()%></td>
	</tr>
	<tr>
		<td colspan="2">
			<form action="FlugbuchenServlet" method="POST">
				<input type="hidden" value="<%=flugList.get(i).getFlugnummer()%>"
					name="flugnummer"> <input type="hidden"
					value="<%=username%>" name="username"> <input type="submit"
					value="Flug buchen" name="submit">
			</form>
		</td>
	</tr>
</table>

<%
	  }
	 }
	}
%>

<%@ include file="footer.jsp"%>