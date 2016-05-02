package entity;

import java.util.ArrayList;
import java.util.Date;

public class Buchung implements java.io.Serializable{
	private Benutzer benutzer;
	private Flug flug;
	private String status;

	public Buchung(Benutzer benutzer, Flug flug, String status){
		setBenutzer(benutzer);
		setFlug(flug);
		setStatus(status);
	}

	public Benutzer getBenutzer(){
		return benutzer;
	}

	public Flug getFlug(){
		return flug;
	}

	public String getStatus(){
		return status;
	}


	public void setBenutzer(Benutzer benutzer){
		this.benutzer=benutzer;
	}

	public void setFlug(Flug flug){
		this.flug=flug;
	}

	public void setStatus(String status){
		this.status=status;
	}
}
