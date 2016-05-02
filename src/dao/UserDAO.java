package dao;

import entity.*;
import java.util.ArrayList;

public interface UserDAO{
	public ArrayList getUserList();

	public AbstractUser getUserbyUsername(String username);

	public void saveUser(AbstractUser saveUserVar);
}