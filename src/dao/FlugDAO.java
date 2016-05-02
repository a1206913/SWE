package dao;

import entity.*;
import java.util.ArrayList;

public interface FlugDAO{
	public ArrayList getFlugList();

	public Flug getFlugbyFlugnummer(int flugnummer);

	public void saveFlug(Flug saveFlugVar);

	public void deleteFlug(Flug deleteFlugVar);

	public void updateFlug(Flug updateFlugVar);
}