<%@page import="entity.Buchung"%>
<%@page import="servicemanagement.ServiceManagement"%>
<%@ include file="header.jsp"%>

<h1 class="title">Meine Buchungen</h1>
<div class="line"></div>

<%
	ServiceManagement service = new ServiceManagement();
	ArrayList<Buchung> buchungList = service.getBuchungList();
	String username = (String) session.getAttribute("session_username");
	int lastIndex = buchungList.size() - 1;

	if (buchungList.isEmpty()) {
		out.println("<h3 style='color:green;'>Zurzeit gibt es keine Buchungen!</h3>");
	} else {
		for (int i = lastIndex; i >= 0; i--) {
			if (buchungList.get(i).getBenutzer().getUsername().equals(username)) {
%>
<table>
	<tr>
		<td>Flugnummer:</td>
		<td><%=buchungList.get(i).getFlug().getFlugnummer()%></td>
	</tr>
	<tr>
		<td>Flug von:</td>
		<td><%=buchungList.get(i).getFlug().getVon()%></td>
	</tr>
	<tr>
		<td>Flug nach:</td>
		<td><%=buchungList.get(i).getFlug().getNach()%></td>
	</tr>
	<tr>
		<td>Datum der Abfahrt:</td>
		<td><%=buchungList.get(i).getFlug().getAbfahrt()%></td>
	</tr>
	<tr>
		<td>Flugdauer:</td>
      <%
      int hours = buchungList.get(i).getFlug().getFlugDauer() / 60;
      int minutes = buchungList.get(i).getFlug().getFlugDauer() % 60;
      %>
      <td><%=hours + " St. : " + minutes + " Min."%></td>
	</tr>
	<tr>
		<td>Terminal:</td>
		<td><%=buchungList.get(i).getFlug().getTerminal()%></td>
	</tr>
</table>
<%
	}
		}
	}
%>

<%@ include file="footer.jsp"%>