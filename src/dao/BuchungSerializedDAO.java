package dao;

import entity.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class BuchungSerializedDAO implements BuchungDAO {
	private final String filename = "buchung.ser";
	private File file;
	private ArrayList<Buchung> buchList = new ArrayList<Buchung>();
	
	public BuchungSerializedDAO() {
		this.file = new File(this.filename);
		this.buchList = getBuchungList();
	}

	public ArrayList getBuchungList() {
		ArrayList<Buchung> newBuchungList = new ArrayList<Buchung>();
		
		try {
		// falls keine Datei mit der Parameter von "serFile" existiert, eine neue Datei wird erstellt
			if (this.file.createNewFile()) {
				System.out.println(file.getAbsolutePath());
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		
		if (this.file.length() != 0) {
			try {
				FileInputStream fInputS = new FileInputStream(this.file);
				ObjectInputStream oIS = new ObjectInputStream(fInputS);
				newBuchungList = (ArrayList<Buchung>) oIS.readObject();
				
				oIS.close();
				fInputS.close();
				return newBuchungList;
			}
			catch (IllegalArgumentException e) {
				e.printStackTrace();
			} 
			catch (EOFException e) {
				e.printStackTrace();
			} 
			catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} 
			catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		} 
		return newBuchungList;
	}

	public void saveBuchung(Buchung saveBuchungVar) {
		try {
			FileOutputStream fOutputS = new FileOutputStream(this.file);
			ObjectOutputStream oOS = new ObjectOutputStream(fOutputS);
			buchList.add(saveBuchungVar);
			oOS.writeObject(buchList);

			oOS.close();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Buchung getBuchungByID() {
		return null;
	}
	
	public int countBookedSeats(int flugnummer) {
		Buchung b = null;
		int bookedSeats = 0;
//		int allSeats = buchung.getFlug().getPlatz();
//		int flugID = buchung.getFlug().getFlugnummer();
		
		try {
			for (Iterator<Buchung> i = this.getBuchungList().iterator(); i.hasNext();) {
				b = i.next();
				
				if (b != null && (b.getFlug().getFlugnummer() == flugnummer)) {
					bookedSeats++;
				}
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return bookedSeats;
	}
}
