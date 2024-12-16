package controller;
import java.sql.SQLException;

import model.*;
import view.*;
public class Controller {
	//Viedään tietoja modelin suuntaan ja yritetään luoda yhteys tietokantaan
	public void dbConnection(String database) throws SQLException, ClassNotFoundException{
		if(database.equals("MySQL")) {
			DataBaseConnection dbc = new DataBaseConnection();
			dbc.createConnection(database);
		}
		else if(database.equals("MongoDB")) {
			System.out.println("MongoDB toiminnallisuutta ei ole luotu vielä");
		}
	}
	//Tarkastetaan käyttäjän tietoja sisäänkirjautumisessa, sisältää ainoastaan testausta toistaiseksi, ei tietokantayhteyttä
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