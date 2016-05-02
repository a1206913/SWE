<%@ include file="header.jsp"%>

<%
	session.removeAttribute("session_username");
   session.invalidate();
	response.sendRedirect("index.jsp");
%>

<%@ include file="footer.jsp"%>