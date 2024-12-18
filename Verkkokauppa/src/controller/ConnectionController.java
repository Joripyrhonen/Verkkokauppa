package controller;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.*;

public class ConnectionController {

	@FXML
	private PasswordField password;
	
	@FXML
	private TextField username;
	//Viedään tietoja modelin suuntaan ja yritetään luoda yhteys tietokantaan
	public String dbConnection(String database, String username, String password) throws SQLException, ClassNotFoundException{
		if(database.equals("MySQL")) {
			DataBaseConnection dbc = new DataBaseConnection();
			String createConnectionResult = dbc.createConnection(username, password);
			System.out.println(createConnectionResult);
			return createConnectionResult; 
		}
		else if(database.equals("MongoDB")) {
//			MongoDBConnection mdbc = new MongoDBConnection();
//			mdbc.createConnection(database, username.getText(), password.getText());
			return "MongoDB toiminnallisuutta ei ole luotu vielä";
		}
		return "ei yhteyttä";
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
