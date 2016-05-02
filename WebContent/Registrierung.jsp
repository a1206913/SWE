<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="Styles.css">
	<title>Registrierung</title>
</head>
<body>
	<h3>
		<font color="#7b9efb">FlightFinder Registrierung</font>
	</h3>
	
	<form class="reg_form" method="post" action="RegistrierungsServlet">
		Benutzername*<br>
      <input class="form_cell" placeholder="Benutzername" type="text" name="user">
      <br>
		Vorname*<br>
		<input class="form_cell" placeholder="Vorname" type="text" name="vorname">
		<br>
		Nachname*<br>
		<input class="form_cell" placeholder="Nachname" type="text" name="nachname">
		<br>
		Email*<br>
		<input class="form_cell" placeholder="Emailadresse" type="text" name="email">
		<br>
		Geburtsdatum*<br>
		<input class="form_cell" placeholder="JJJJ-MM-DD" type="text" name="gebDatum">
		<br>
		Passwort*<br>
		<input class="form_cell" placeholder="Passwort" type="password" name="passw">
		<br><br>
	
		<input class="button" type="submit" name="action" value="Registrieren">		
		<input class="button" type="submit" name="back" value="Abbrechen">
	</form>
</body>
</html>