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

	public String dbConnection(String database, String username, String password)
			throws SQLException, ClassNotFoundException {
		DataBaseConnection dbc = new DataBaseConnection();
		String createConnectionResult = dbc.createConnection(database, username, password);
		return createConnectionResult;
	}

	public String dbSignUp(String database, String username, String password, String realname, String address)
			throws SQLException, ClassNotFoundException {
		DataBaseConnection dbc = new DataBaseConnection();
		String createConnectionResult = dbc.addUser(database, username, password, realname, address);
		return createConnectionResult;
	}

	public ArrayList<String> getUserInfo(String database, String username) throws SQLException {
		ArrayList<String> userinfotobereturned;
		DataBaseConnection dbc = new DataBaseConnection();
		userinfotobereturned = dbc.UserInfoQuery(database, username);
		return userinfotobereturned;
	}

	public void makePurchase(String database, ObservableList<Item> observableItemList, String user)
			throws SQLException {
		DataBaseConnection dbc = new DataBaseConnection();
		dbc.addPurchase(database, observableItemList, user);
	}

	public ArrayList<Purchase> purchaseHistory(String user) throws SQLException {
		DataBaseConnection dbc = new DataBaseConnection();
		ArrayList<Purchase> purchasehistory = new ArrayList<Purchase>();
		purchasehistory = dbc.purchaseHistory(user);
		return purchasehistory;
	}

	public ArrayList<String> getCategoryItems(String database, String category) throws SQLException {
		ArrayList<String> categoryitems = new ArrayList<String>();
		DataBaseConnection dbc = new DataBaseConnection();
		categoryitems = dbc.getItemList(database, category);
		return categoryitems;
	}

	public ArrayList<String> getCategories(String database) throws SQLException {
		ArrayList<String> categories = new ArrayList<String>();
		DataBaseConnection dbc = new DataBaseConnection();
		categories = dbc.getCategoryList(database);
		return categories;
	}
}
