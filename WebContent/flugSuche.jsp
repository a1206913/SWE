<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
	<h3>Suche nach Abflug und Flugziel</h3>
	<form action="FlugSuchenServlet" method="POST">
		<table>
			<tr>
				<td>Von:</td>
				<td><input type="text" name="flugVon"></td>
				<td>Nach:</td>
				<td><input type="text" name="flugNach"><input style="margin-left:60px;" type="submit" value="Suchen" name="search"></td>
			</tr>
		</table>
		<br>
			
	
   <!-- <br><br>	
	<h3>Suche nach Abflugzeit</h3>
	<br>
      Abflugzeit:
            <input type="text" name="flugZeit"> 
            <input style="margin-left:60px;" type="submit" value="Suchen" name="search">  -->
   </form>
</body>
</html>