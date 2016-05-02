<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.*"%>
<%@ page import="entity.*"%>
<%@ page import="dao.*"%>
<%@ page import="controller.*"%>

<!DOCTYPE HTML>
<html lang="en">
<head>
<title>Flugbuchung</title>
<meta charset="UTF-8">
<link rel="shortcut icon" type="image/x-icon"
	href="style/images/favicon.png">
<link rel="stylesheet" type="text/css" href="style/css/style.css"
	media="all">
<link href='http://fonts.googleapis.com/css?family=Amaranth'
	rel='stylesheet' type='text/css'>
<link
	href='http://fonts.googleapis.com/css?family=Droid+Serif:400,400italic,700,700italic'
	rel='stylesheet' type='text/css'>

<script src="style/js/jquery-1.6.4.min.js"></script>
<script src="style/js/ddsmoothmenu.js"></script>
<script src="style/js/jquery.jcarousel.js"></script>
<script src="style/js/jquery.prettyPhoto.js"></script>
<script src="style/js/carousel.js"></script>
<script src="style/js/jquery.flexslider-min.js"></script>
<script src="style/js/jquery.masonry.min.js"></script>
<script src="style/js/jquery.slickforms.js"></script>
</head>
<body>
	<div id="wrapper">
		<div id="sidebar">
			<div id="logo">
				<a href="index.html"><img src="style/images/logo.png" alt=""></a>
			</div>
			<div id="menu" class="menu-v">
				<ul>
					<li><a href="index.jsp">Home</a></li>
					<%
						if (session.getAttribute("session_username") == null) {
					%>
					<li><a href="login.jsp">Einloggen</a></li>
					<li><a href="register.jsp">Registration</a></li>
					<%
						} else {
							String usertyp = (String) session.getAttribute("session_usertyp");
					%>
					<li><a href="logout.jsp">Ausloggen</a></li>
					<li><a href="myprofil.jsp">Mein Profil</a></li>
					<%
						if (usertyp.equals("Benutzer")) {
					%>
					<li><a href="fluglist.jsp">Alle Fluege anzeigen</a></li>
					<li><a href="mybooking-user.jsp">Meine Buchungen</a></li>
					<%
						} else if (usertyp.equals("Fluggesellschaft")) {
					%>
					<li><a href="flugcreate.jsp">Flug erstellen</a></li>
					<li><a href="myflug.jsp">Meine Fluege</a></li>
					<%
						} else if (usertyp.equals("Statistiker")) {

							}

						}
					%>


				</ul>
			</div>

		</div>

		<div id="content">