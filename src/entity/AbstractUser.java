package entity;

abstract public class AbstractUser implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String email;
	
	public AbstractUser(String username, String password, String email){
		setUsername(username);
		setPassword(password);
		setEmail(email);
	}
	
	public String getUsername(){
		return username;
	}

	public String getPassword(){
		return password;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username=username;
	}

	public void setPassword(String password){
		this.password=password;
	}

	public void setEmail(String email){
		this.email=email;
	}
  
	public boolean verifyPasswort(String passwort) {
		if (!passwort.isEmpty()) {
			setPassword(passwort);
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "AbstractUser [username=" + username + ", passwort=" + password + ", email=" + email + "]";
	}
}
