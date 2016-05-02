package dao;

import entity.*;
import java.util.ArrayList;

public interface BuchungDAO{
	public ArrayList getBuchungList();

	public void saveBuchung(Buchung saveBuchungVar);
	
	public int countBookedSeats(int flugnummer);

}