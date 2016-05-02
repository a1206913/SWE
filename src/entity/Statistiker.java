package entity;

public class Statistiker extends AbstractUser{
	private String telefon;
	
	public Statistiker(String username, String password, String email, String telefon){
		super(username, password, email);
		setTelefon(telefon);
	}
	
	public String getTelefon(){
		return telefon;
	}

	public void setTelefon(String telefon){
		this.telefon=telefon;
	}
  
}