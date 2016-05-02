package dao;

import entity.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class FlugSerializedDAO implements FlugDAO {
	private final String filename = "flug.ser";
	private File file;
	private ArrayList<Flug> fList = new ArrayList<Flug>();

	public FlugSerializedDAO() {
		this.file = new File(this.filename);
		this.fList = getFlugList();
	}

	public ArrayList<Flug> getFlugList() {
		ArrayList<Flug> newFlugList = new ArrayList<Flug>();
		
		try {
			if (this.file.createNewFile()) {
				System.out.println(file.getAbsolutePath());
			}
		}	
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		if (this.file.length() != 0) {
			try {

				FileInputStream fInputS = new FileInputStream(this.file);
				ObjectInputStream ois = new ObjectInputStream(fInputS);
				newFlugList = (ArrayList<Flug>) ois.readObject();
				ois.close();
				fInputS.close();

				return newFlugList;
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
		return newFlugList;
	}

	public Flug getFlugbyFlugnummer(int flugnummer) {
		Flug flug = null;
		int flugNummer = 0;
		
		fList = this.getFlugList();
		try {
			for (Iterator<Flug> it = this.fList.iterator(); it.hasNext();) {
				flug = it.next();
				
				if (flug != null) {
					flugNummer = flug.getFlugnummer();
					if (flugnummer == flugNummer) {
						return flug;
					}	
				}
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return flug;
	}

	/* when this method is called, the conditions for a new username have been already proved - 
		no need to prove if the user is already taken
	*/
	public void saveFlug(Flug saveNewFlug) {
		try {
			FileOutputStream fOutputS = new FileOutputStream(file);
			ObjectOutputStream objOutputS = new ObjectOutputStream(fOutputS);
			fList.add(saveNewFlug);
			objOutputS.writeObject(fList);
			objOutputS.close();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

	public void deleteFlug(Flug deleteFlugVar) {
		try {
			File file = new File(filename);
			if (file.exists()) {
				ArrayList<Flug> oldFlugList;
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				oldFlugList = (ArrayList<Flug>) ois.readObject();
				ArrayList<Flug> newFlugList = new ArrayList<Flug>();
				for (int i = 0; i < oldFlugList.size(); i++) {
					if (!(((oldFlugList.get(i)).getFlugnummer()) == (deleteFlugVar.getFlugnummer()))) {
						newFlugList.add(oldFlugList.get(i));
					}
				}
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
				oos.writeObject(newFlugList);
				oos.close();
			} else
				throw new IllegalArgumentException("There is no FlugList!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void updateFlug(Flug updateFlugVar) {
		try {
			File file = new File(filename);
			if (file.exists()) {
				ArrayList<Flug> FlugList;
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
				FlugList = (ArrayList<Flug>) ois.readObject();
				ois.close();

				ArrayList<Flug> newFlugList = new ArrayList<Flug>();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
				boolean isinit = true;
				for (int i = 0; i < FlugList.size(); i++) {
					if (((FlugList.get(i)).getFlugnummer()) == (updateFlugVar.getFlugnummer())) {
						
						isinit = false;
						newFlugList.add(updateFlugVar);
					} else {
						newFlugList.add(FlugList.get(i));
					}
				}

				oos.writeObject(newFlugList);
				oos.close();
				if (isinit)
					throw new IllegalArgumentException("Flug was not found on the List!");
			} else
				throw new IllegalArgumentException("There is no FlugList!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
