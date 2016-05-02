package dao;

import entity.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class UserSerializedDAO implements UserDAO {
	private final String filename = "user.ser";
	private File file;
	private ArrayList<AbstractUser> bList = new ArrayList<AbstractUser>();

	public UserSerializedDAO() {
		this.file = new File(this.filename);
		this.bList = getUserList();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<AbstractUser> getUserList() {
		ArrayList<AbstractUser> newUserList = new ArrayList<AbstractUser>();

		try {
			// falls keine Datei mit der Parameter von "serFile" existiert, eine neue Datei wird erstellt
			if (this.file.createNewFile()) {
				System.out.println(file.getAbsolutePath());
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (this.file.length() != 0) {
			try {

				FileInputStream fInputS = new FileInputStream(this.file);
				ObjectInputStream ois = new ObjectInputStream(fInputS);
				newUserList = (ArrayList<AbstractUser>) ois.readObject();
				ois.close();
				fInputS.close();

				return newUserList;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (EOFException e) {
				e.printStackTrace();
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return newUserList;
	}

	public AbstractUser getUserbyUsername(String username) {
		System.out.println("in the getUserbyUsername() method - parameter username: " + username);
		AbstractUser abstractUser = null;
		String user = null;
		
		bList = getUserList();
		try {
			for (Iterator<AbstractUser> u = bList.iterator(); u.hasNext();) {
				abstractUser = u.next();

				if (abstractUser != null) {
					user = abstractUser.getUsername();
					System.out.println("in the getUserbyUsername() method - abstractUser != null--> the user: " + user);

					if (user.equals(username)) {
						return abstractUser;
					}
				}
			}
			return null;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return abstractUser;
	}

	// when this method is called, the conditions for a new username have been already proved - no need to prove if the
	// user is already taken
	public void saveUser(AbstractUser saveUserVar) {
		System.out.println("in the saveUser()");
		try {
			FileOutputStream fOutputS = new FileOutputStream(file);
			ObjectOutputStream objOutputS = new ObjectOutputStream(fOutputS);
			bList.add(saveUserVar);
			objOutputS.writeObject(bList);
			objOutputS.close();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateUser(AbstractUser updateUserVar) {
		try {
			File file = new File(filename);
			if (file.exists()) {
				ArrayList<AbstractUser> UserList;
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				UserList = (ArrayList<AbstractUser>) ois.readObject();
				ois.close();

				ArrayList<AbstractUser> newUserList = new ArrayList<AbstractUser>();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
				boolean isinit = true;
				for (int i = 0; i < UserList.size(); i++) {
					if (((UserList.get(i)).getUsername()).equals(updateUserVar.getUsername())) {
						isinit = false;
						newUserList.add(updateUserVar);
					} else {
						newUserList.add(UserList.get(i));
					}
				}

				oos.writeObject(newUserList);
				oos.close();
				if (isinit)
					throw new IllegalArgumentException("Username was not found on the List!");
			} else
				throw new IllegalArgumentException("There is no UserList!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}