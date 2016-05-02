package entity;

import java.util.Date;

public class Benutzer extends AbstractUser{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String vorname;
	private String nachname;
	private Date geburtsDatum;
	
	public Benutzer(String username, String password, String email, String vorname, String nachname, Date geburtsDatum){
		super(username, password, email);
		setVorname(vorname);
		setNachname(nachname);
		setGeburtsDatum(geburtsDatum);
	}
	
	public String getVorname(){
		return vorname;
	}

	public String getNachname(){
		return nachname;
	}

	public Date getGeburtsDatum(){
		return geburtsDatum;
	}

	public void setVorname(String vorname){
		this.vorname=vorname;
	}

	public void setNachname(String nachname){
		this.nachname=nachname;
	}

	public void setGeburtsDatum(Date geburtsDatum){
		this.geburtsDatum=geburtsDatum;
	}
  
}