package servicemanagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import entity.*;
import dao.*;

public class ServiceManagement {
	private UserDAO userDAO;
	private FlugDAO flugDAO;
	private BuchungDAO buchungDAO;
	private AbstractUser newUser;

	public ServiceManagement() {
		userDAO = new UserSerializedDAO();
		flugDAO = new FlugSerializedDAO();
		 buchungDAO = new BuchungSerializedDAO();
	}

	public Date getDate(String date) {
		// check if date is valid
		SimpleDateFormat dateFromJSP = new SimpleDateFormat("dd-MM-yyyy");
		Date newDate = null;
		
		try {
			newDate = dateFromJSP.parse(date);
			Date dateNow = new Date();
			if (newDate.before(dateNow)) {
				return newDate;
			}
		} catch (ParseException pe) {
			System.out.println("The date is not valid!");
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("Das Datumformat muss 'DD-MM-YYYY' sein");
		} catch (IllegalArgumentException ex) {
			System.out.println("The Date is out of the Calendar bound. The date format is 'DD-MM-YYYY'");
		}
		return newDate;
	}
	
	public Date getDateWithHours(String date) {
		// check if date is valid
		SimpleDateFormat dateFromJSP = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date newDate = null;
		try {
			newDate = dateFromJSP.parse(date);
			Date dateNow = new Date();
			if (newDate.after(dateNow)) {
				return newDate;
			}
		} catch (ParseException pe) {
			System.out.println("The date is not valid!");
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("Das Datumformat muss 'DD-MM-YYYY' sein");
		} catch (IllegalArgumentException ex) {
			System.out.println("The Date is out of the Calendar bound. The date format is 'DD-MM-YYYY'");
		}
		return newDate;
	}

	// making method checkRegistration() to return a String, so that we can pass a message for the registration
	public String checkRegistrierung(AbstractUser user) {

		AbstractUser userNameFromDAO = null;
		userNameFromDAO = userDAO.getUserbyUsername(user.getUsername());

		System.out.println("checkRegistrierung: " + user.toString());
		System.out.println("userNameFromDAO: " + userNameFromDAO);

		// check if username is unique
		if (user.getUsername() == null || user.getUsername().isEmpty()) {
			return "Das Benutzernamefeld ist verpflichtend und kann nicht leer bleiben.";
		} else if (userNameFromDAO == null) {
			return "true";
		} else if (userNameFromDAO.getUsername().equals(user.getUsername())) {
			return "Username already in use";
		}
		// get the right instance of the user
		String userTyp = getUserTyp(user);
		// this.userNameFromDAO = userDAO.getUserbyUsername(user.getUsername());
		if (userTyp.equals("Benutzer")) {
			if (!((Benutzer) user).verifyPasswort(user.getPassword())) {
				return "Das Passwortfeld ist verpflichtend und kann nicht leer bleiben.";
			}

			if (!((Benutzer) user).verifyPasswort(user.getPassword())) {
				return "Das Passwortfeld ist verpflichtend und kann nicht leer bleiben.";
			}
			
			if (((Benutzer) user).getEmail().isEmpty() || !((Benutzer) user).getEmail().contains("@")) {
				return "Email der Benutzer ist verpflichtend. Es wird auf '@' geprüft.";
			}
			
			if (((Benutzer) user).getVorname().isEmpty()) {
				return "Vorname der Benutzer ist verpflichtend.";
			}
			if (((Benutzer) user).getNachname().isEmpty()) {
				return "Nachname der Benutzer ist verpflichtend.";
			}
		}

		if (userTyp.equals("Fluggesellschaft")) {
			System.out.println("ServiceManagement - checkRegistrierung(): ");
			if (!((Fluggesellschaft) user).verifyPasswort(user.getPassword())) {
				return "Das Passwortfeld ist verpflichtend und kann nicht leer bleiben.";
			}

			if (((Fluggesellschaft) user).getName().isEmpty()) {
				return "Name der Fluggesellschaft ist verpflichtend und kann nicht leer bleiben.";
			}	
			
			if (((Fluggesellschaft) user).getEmail().isEmpty() || !((Fluggesellschaft) user).getEmail().contains("@")) {
				return "Email der Fluggesellschaft ist verpflichtend. Es wird auf '@' geprüft.";
			}	 
			if (((Fluggesellschaft) user).getAdresse().isEmpty()) {
				return "Die Adresse der Fluggesellschaft ist verpflichtend und kann nicht leer bleiben.";
			}	
			if (((Fluggesellschaft) user).getTelefon().isEmpty()) {
				return "Die Telefonnummer der Fluggesellschaft ist verpflichtend und kann nicht leer bleiben.";
			}	
		}

		if (userTyp.equals("Statistiker")) {
			if (!((Statistiker) user).verifyPasswort(user.getPassword())) {
				return "Das Passwortfeld ist verpflichtend und kann nicht leer bleiben.";
			}
			
			if (((Statistiker) user).getEmail().isEmpty() || !((Statistiker) user).getEmail().contains("@")) {
				return "Email der Statistiker ist verpflichtend. Es wird auf '@' geprüft.";
			}
			
			if (((Statistiker) user).getTelefon().isEmpty()) {
				return "Die Telefonnummer der Statistiker ist verpflichtend und kann nicht leer bleiben.";
			}
		}

		return "true";
	}
 
	public String checkFlugErstellen(Flug flug) {
		Flug flugFromDAO = flugDAO.getFlugbyFlugnummer(flug.getFlugnummer());

		// Fehlermeldungen für int und double werden im FlugeintragenServlet gefangen
		// if (flug.getFlugnummer() == 0) {
		// return "Die Flugnummer ist verpflichtend. Darf nicht leer bleiben oder das Wert 0 haben.";
		// }
		if (flugFromDAO == null) {
			return "true";
		}
		if (flugFromDAO.getFlugnummer() == flug.getFlugnummer()) {
			return "Flugnummer " + flug.getFlugnummer() + " existiert bereits. Die Flugnummer müssen eindeutig sein.";
		}

		// check if Flug nach field is empty
		else if (flug.getVon() == null || flug.getVon().isEmpty()) {
			return "Das 'Flug von' Feld ist verpflichtend.";
		}

		else if (flug.getNach() == null || flug.getNach().isEmpty()) {
			return "Das 'Flug nach' Feld ist verpflichtend.";
		}

		// Fehlermeldungen für int und double werden im FlugeintragenServlet gefangen
		/*
		 * else if (flug.getPreis() == 0) { return "Der FlugPreis darf nicht leer bleiben oder das Wert 0 haben."; }
		 * 
		 * else if (flug.getPlatz() == 0) { return "Der FlugPlatz darf nicht leer bleiben oder das Wert 0 haben."; }
		 * 
		 * else if (flug.getFlugsteig() == 0) { return "Der Flugsteig darf nicht leer bleiben oder das Wert 0 haben."; }
		 */

		else if (flug.getTerminal() == null || flug.getTerminal().isEmpty()) {
			return "Das Terminal Feld darf nicht leer bleiben.";
		}

		else if (flug.getStatus() == null || flug.getStatus().isEmpty()) {
			return "Das 'Flug Status' Feld ist verpflichtend.";
		}

		return "true";
	}
	public void registerUser(AbstractUser user) {
		this.userDAO.saveUser(user);
	}

	public boolean login(String username, String password) {
		AbstractUser usercheck = userDAO.getUserbyUsername(username);
		printAllUsers(userDAO.getUserList());
		if (usercheck != null) {
			System.out.println("servicemanagement - login() --> usercheck != null");
			if ((usercheck.getPassword()).equals(password)) {
				return true;
			}
		}
		return false;
	}

	public void printAllUsers(ArrayList<AbstractUser> allUsers) {
		AbstractUser absUser = null;
		System.out.println("******** starting printing all users *********");
		try {
			for (Iterator<AbstractUser> i = allUsers.iterator(); i.hasNext();) {
				absUser = i.next();
				if (absUser == null) {
					System.out.println("no users");
				} else {
					System.out.println(absUser.toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("******** finishing printing all users ********");
	}

	public String getUserTyp(AbstractUser usercheck) {
		String userTyp = null;
		if (usercheck != null) {
			if (usercheck instanceof Benutzer) {
				userTyp = "Benutzer";
				System.out.println("userType: " + userTyp);
			}
			if (usercheck instanceof Fluggesellschaft) {
				userTyp = "Fluggesellschaft";
			}
			if (usercheck instanceof Statistiker) {
				userTyp = "Statistiker";
			}
		}
		return userTyp;
	}

	public AbstractUser getUser(String username) {
		AbstractUser usercheck = userDAO.getUserbyUsername(username);
		if (usercheck != null) {
			return usercheck;
		}
		return null;
	}

	public void createFlug(Flug newFlug) {
		flugDAO.saveFlug(newFlug);
	}

	public Flug getFlug(int flugnummer) {
		Flug flugcheck = flugDAO.getFlugbyFlugnummer(flugnummer);
		if (flugcheck != null) {
			return flugcheck;
		}
		return null;
	}

	public ArrayList getFlugList() {
		ArrayList<Flug> flugList = flugDAO.getFlugList();
		return flugList;
	}
	
	public synchronized int checkSeat(int flugnummer) {
		Flug f = flugDAO.getFlugbyFlugnummer(flugnummer);
		int availableSeats = f.getPlatz(); 
		return (availableSeats - buchungDAO.countBookedSeats(flugnummer));
	}
	
	public void changeFlugStatus(int flugnummer, String newStatus) {
		Flug f = flugDAO.getFlugbyFlugnummer(flugnummer);
		f.setStatus(newStatus);
		flugDAO.saveFlug(f);
	}
	
	public boolean createBuchung(Buchung newBuchung) {
		buchungDAO.saveBuchung(newBuchung);
		return true;
	}

	public ArrayList getBuchungList() {
		ArrayList<Buchung> buchungList = buchungDAO.getBuchungList();
		return buchungList;
	}
	
	public ArrayList<Flug> searchFlugByTown(String abFlug, String flugZiel) {
		ArrayList<Flug> flugList = getFlugList();
		ArrayList<Flug> townList = new ArrayList<Flug>();
		Flug f = null;
		
		try {
			for (Iterator<Flug> i = flugList.iterator(); i.hasNext();) {
				f = i.next();
				if (f != null) {
					if ((f.getVon().equalsIgnoreCase(abFlug) || f.getVon().contains(abFlug)) && flugZiel.isEmpty() && f.getStatus().equals("available")) {
						townList.add(f);
					} 
					else if (f.getNach().equalsIgnoreCase(flugZiel) && abFlug.isEmpty() && f.getStatus().equals("available")) {
						townList.add(f);
						System.out.println("in the searchFlugByTown() - ServiceManag");
					}
					else if (f.getVon().equalsIgnoreCase(abFlug) && f.getNach().equalsIgnoreCase(flugZiel) && f.getStatus().equals("available")) {
						townList.add(f);
						System.out.println("in the searchFlugByTown() - ServiceManag");
					}
				}
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return townList;
	}

}