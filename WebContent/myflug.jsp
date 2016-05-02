<%@page import="entity.Flug"%>
<%@page import="servicemanagement.ServiceManagement"%>
<%@ include file="header.jsp"%>

<h1 class="title">Meine Flüge</h1>
<div class="line"></div>

<%
	ServiceManagement service = new ServiceManagement();
	ArrayList<Flug> flugList = service.getFlugList();
	String username = (String) session.getAttribute("session_username");
	
   DecimalFormat df = new DecimalFormat("#.##");
   String doubleFormated = " ";
	if (flugList == null) {
		out.println("Es gibt keine Flüge!");
	} else {
		for (int i = 0; i < flugList.size(); i++) {
			doubleFormated = df.format(flugList.get(i).getPreis());
			if (username.equals(flugList.get(i).getUserName())) {
				
			
			//if(flugList.get(i).getFluggesellschaft().getUsername().equals(username)){
%>
<table>
	<tr>
		<td>Flugnummer:</td>
		<td><%=flugList.get(i).getFlugnummer()%></td>
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
		<td><%=doubleFormated %></td>
	</tr>
	<tr>
		<td>Plätze:</td>
		<td><%=flugList.get(i).getPlatz()%></td>
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
		<td>Status:</td>
		<td><%=flugList.get(i).getStatus()%></td>
	</tr>
</table>

<%
         }
		}
	}
%>

<%@ include file="footer.jsp"%>