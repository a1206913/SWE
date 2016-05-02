<%@ include file="header.jsp" %>

<h1 class="title">Registration</h1>
<div class="line"></div>


<div class="tabbed-content">
      <h3>Usertypen</h3>
      <ul class="tabs">
        <li><a href="#tab1">Benutzer</a></li>
        <li><a href="#tab2">Fluggesellschaft</a></li>
        <li><a href="#tab3">Statistiker</a></li>
      </ul>
      <div class="tab_container">
        <div style="display: none;" id="tab1" class="tab_content">
          <h4>Benutzer</h4>
				<form action="RegistrierungsServlet" method="POST" name="registerForm1">
					<table>
						<tr>
							<td>Username:</td>
							<td><input type="text" name="username"></td>
						</tr>
						<tr>
							<td>Passwort:</td>
							<td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td>E-Mail:</td>
							<td><input type="text" name="email"></td>
						</tr>
						<tr>
							<td>Vorname:</td>
							<td><input type="text" name="vorname"></td>
						</tr>
						<tr>
							<td>Nachname:</td>
							<td><input type="text" name="nachname"></td>
						</tr>
						<tr>
							<td>Geburtsdatum:</td>
							<td><input type="text" name="geburtsdatum">(dd-MM-yyyy)</td>
						</tr>
					</table>
					<input type="hidden" name="registertyp" value="benutzer">
					<input type="submit" value="Registration" name="action"><br><br>
				</form>
        </div>
        
        <div style="display: none;" id="tab2" class="tab_content">
          <h4>Fluggesellschaft</h4>
				<form action="RegistrierungsServlet" method="POST" name="registerForm2">
					<table>
						<tr>
							<td>Username:</td>
							<td><input type="text" name="username"></td>
						</tr>
						<tr>
							<td>Passwort:</td>
							<td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td>E-Mail:</td>
							<td><input type="text" name="email"></td>
						</tr>
						<tr>
							<td>Name der Fluggesellschaft:</td>
							<td><input type="text" name="name"></td>
						</tr>
						<tr>
							<td>Adresse:</td>
							<td><input type="text" name="adresse"></td>
						</tr>
						<tr>
							<td>Telefon:</td>
							<td><input type="text" name="telefon"></td>
						</tr>
					</table>
					<input type="hidden" name="registertyp" value="fluggesellschaft">
					<input type="submit" value="Registration" name="submit"><br><br>
				</form>
        </div>
        
        <div style="display: none;" id="tab3" class="tab_content">
          <h4>Statistiker</h4>
				<form action="RegistrierungsServlet" method="POST" name="registerForm3">
					<table>
						<tr>
							<td>Username:</td>
							<td><input type="text" name="username"></td>
						</tr>
						<tr>
							<td>Passwort:</td>
							<td><input type="password" name="password"></td>
						</tr>
						<tr>
							<td>E-Mail:</td>
							<td><input type="text" name="email"></td>
						</tr>
						<tr>
							<td>Telefon:</td>
							<td><input type="text" name="telefon"></td>
						</tr>
					</table>
					<input type="hidden" name="registertyp" value="statistiker">
					<input type="submit" value="Registration" name="submit"><br><br>
				</form>
        </div>
		</div>
</div>




<%@ include file="footer.jsp" %>