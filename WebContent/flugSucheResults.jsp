<%@page import="servicemanagement.ServiceManagement"%>
<%@ include file="header.jsp"%>

<h2 class="title">Ergebnisse bei der Flugsuche:</h2>
<div class="line"></div>

<%
   ServiceManagement service = new ServiceManagement();
   ArrayList<Flug> flugList = (ArrayList<Flug>) request.getAttribute("townList");

   if (flugList == null) {
      out.println("<h3 style='color:green;'>Zurzeit gibt es keine Flüge die die angegebene Kriterien entsprechen!</h3>");
   } 
   else {
      for (int i = 0; i < flugList.size(); i++) {
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
   </table> 
<%-- <% 
   String username = (String) session.getAttribute("session_username");
   String userType = (String) session.getAttribute("userType");
   if(username != null && !username.isEmpty() && userType.equals("Benutzer")) {
  
%>    --%>
  <%--  <tr>
      <td colspan="2">
         <form action="FlugbuchenServlet" method="POST">
            <input type="hidden" value="<%=flugList.get(i).getFlugnummer()%>" name="flugnummer"> 
            <input type="hidden" value="<%=username%>" name="username"> 
            <input type="submit" value="Flug buchen" name="submit">
         </form>
      </td>
   </tr>
</table> --%>
<%
   
      }
   }
%>

<%@ include file="footer.jsp"%>