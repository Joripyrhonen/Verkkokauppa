package model;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.ObservableList;

public interface DBConnection {
	public String createConnection(String database, String user, String password) throws SQLException;

	public String addUser(String database, String username, String password, String realname, String address)
			throws SQLException;

	public ArrayList<String> getItemList(String database, String category) throws SQLException;

	public ArrayList<String> UserInfoQuery(String database, String username) throws SQLException;

	public void addPurchase(String database, ObservableList<Item> observableItemList, String user) throws SQLException;

	public ArrayList<String> getCategoryList(String database) throws SQLException;

	public ArrayList<Purchase> purchaseHistory(String user) throws SQLException;
}
