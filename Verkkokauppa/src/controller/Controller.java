package controller;
import java.sql.SQLException;

import model.*;
import view.*;
public class Controller {
	public void dbConnection(String database) throws SQLException{
		DataBaseConnection dbc = new DataBaseConnection();
		dbc.createConnection(database);
	}
	//Tarkastetaan käyttäjän tietoja sisäänkirjautumisessa, ainoastaan testausta toistaiseksi, ei tietokantayhteyttä
	public String userCheck(String username, String password) {
		User testUser = new User();
		testUser.setUserName("pepe");
		testUser.setPassword("password");
		String result;
		if(username.equals(testUser.getUserName()) && password.equalsIgnoreCase(testUser.getPassword())) {
			result = "username and password correct";
			return result;
		}
		else if(username.equals(testUser.getUserName()) && !password.equalsIgnoreCase(testUser.getPassword())){
			result = "password incorrect";
			return result;
		}
		else {
			result = "user does not exist";
			return result;
		}
	}
}