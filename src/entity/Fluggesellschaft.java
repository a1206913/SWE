package entity;

public class Fluggesellschaft extends AbstractUser{
	private String name;
	private String adresse;
	private String telefon;
	
	public Fluggesellschaft(String username, String password, String email, String name, String adresse, String telefon){
		super(username, password, email);
		setName(name);
		setAdresse(adresse);
		setTelefon(telefon);
	}
	
	public String getName(){
		return name;
	}

	public String getAdresse(){
		return adresse;
	}

	public String getTelefon(){
		return telefon;
	}

	public void setName(String name){
		this.name=name;
	}

	public void setAdresse(String adresse){
		this.adresse=adresse;
	}

	public void setTelefon(String telefon){
		this.telefon=telefon;
	}
  
}