package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.*;

public class ConnectionController {

	@FXML
	private PasswordField password;
	
	@FXML
	private TextField username;

	public String dbConnection(String database, String username, String password) throws SQLException, ClassNotFoundException{
		if(database.equals("MySQL")) {
			DataBaseConnection dbc = new DataBaseConnection();
			String createConnectionResult = dbc.createConnection(username, password);
			return createConnectionResult; 
		}
		else if(database.equals("MongoDB")) {
//			MongoDBConnection mdbc = new MongoDBConnection();
//			mdbc.createConnection(username, password);
			return "MongoDB toiminnallisuutta ei ole luotu vielä";
		}
		return "ei yhteyttä";
	}

	public String dbSignUp(String database, String username, String password, String realname, String address) throws SQLException, ClassNotFoundException {
		if(database.equals("MySQL")) {
			DataBaseConnection dbc = new DataBaseConnection();
			String createConnectionResult = dbc.addUser(username, password, realname, address);
			return createConnectionResult; 
		}
		else if(database.equals("MongoDB")) {
//			MongoDBConnection mdbc = new MongoDBConnection();
//			mdbc.addUser(database, username.getText(), password.getText());
			return "MongoDB toiminnallisuutta ei ole luotu vielä";
		}
		return "ei yhteyttä";
	}
	public ArrayList<String> getUserInfo(String username) throws SQLException{
		ArrayList<String> userinfotobereturned;
		DataBaseConnection dbc = new DataBaseConnection();
		userinfotobereturned = dbc.UserInfoQuery(username);
		return userinfotobereturned;
	}
	public void makePurchase(ObservableList<Item> observableItemList, String user) throws SQLException {
		DataBaseConnection dbc = new DataBaseConnection();
		dbc.addPurchase(observableItemList, user);
	}

	public ArrayList<Purchase> purchaseHistory(String user) throws SQLException {
		DataBaseConnection dbc = new DataBaseConnection();
		ArrayList<Purchase> purchasehistory = new ArrayList<Purchase>();
		purchasehistory = dbc.purchaseHistory(user);
		return purchasehistory;
	}
}
