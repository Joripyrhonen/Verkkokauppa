package model;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.ObservableList;

public class MongoDBConnection implements DBConnection{
	//MongoDB tietokannan tiedot, jotka tulisivat täytettyä myöhemmin
//	private final String mongodburl = "a";
//	private final String mongodbuser = "b";
//	private final String mongodbpassword = "c";
	
	public String createConnection(String user, String password) throws SQLException{
		return null;
}

	@Override
	public String addUser(String username, String password, String realname, String address) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getItemList(String category) throws SQLException{
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<String> UserInfoQuery(String username) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPurchase(ObservableList<Item> observableItemList, String user) throws SQLException {
		// TODO Auto-generated method stub
		
	}
}
