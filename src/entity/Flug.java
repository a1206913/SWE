package entity;

import java.util.ArrayList;
import java.util.Date;

public class Flug implements java.io.Serializable {

	private static final long serialVersionUID = 8047446180928248422L;
	private int flugnummer;
	private String von;
	private String nach;
	private Date abfahrt;
	private double preis;
	private int platz;
	private int flugDauer;
	private String terminal;
//	posibilities for status: available, booked, canceled
	private String status;
	private String userName;
	

	public Flug(int flugnummer, String von, String nach, Date abfahrt, double preis, int platz, int flugDauer,
			String terminal, String status, String userName) {
		setFlugnummer(flugnummer);
		setVon(von);
		setNach(nach);
		setAbfahrt(abfahrt);
		setPreis(preis);
		setPlatz(platz);
		setFlugDauer(flugDauer);
		setTerminal(terminal);
		setStatus(status);
		setUserName(userName);

	}

	public int getFlugnummer() {
		return flugnummer;
	}

	public String getVon() {
		return von;
	}

	public String getNach() {
		return nach;
	}

	public Date getAbfahrt() {
		return abfahrt;
	}

	public double getPreis() {
		return preis;
	}

	public int getPlatz() {
		return platz;
	}

	public int getFlugDauer() {
		return flugDauer;
	}

	public String getTerminal() {
		return terminal;
	}

	public String getStatus() {
		return status;
	}

	public String getUserName() {
		return userName;
	}
	public void setFlugnummer(int flugnummer) {
		this.flugnummer = flugnummer;
	}

	public void setVon(String von) {
		this.von = von;
	}

	public void setNach(String nach) {
		this.nach = nach;
	}

	public void setAbfahrt(Date abfahrt) {
		this.abfahrt = abfahrt;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	public void setPlatz(int platz) {
		this.platz = platz;
	}

	public void setFlugDauer(int flugDauer) {
		this.flugDauer = flugDauer;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*public String toString() {
		return "flugnummer: " + getFlugnummer();
	}*/
}
