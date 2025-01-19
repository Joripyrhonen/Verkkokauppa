package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Stack;

import javafx.collections.ObservableList;

public class DataBaseConnection implements DBConnection{
	private final String mysqlurl = "jdbc:mysql://localhost:3306/verkkokauppa";
	private final String mysqluser = "root";
	private final String mysqlpassword = "Jatski85";

	private Connection conn;
	private PreparedStatement stmnt;
	private final String users = "verkkokauppa.users";
	private final String items = "verkkokauppa.items";
	private final String purchases = "verkkokauppa.purchases";
	@Override
	public String createConnection(String currentUser, String currentPassword) throws SQLException {
		try {
			Connection conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			String sql = "SELECT username FROM " +users +" WHERE username = ?;";
			PreparedStatement stmnt = conn.prepareStatement(sql);
			stmnt.setString(1, currentUser);
			ResultSet resultSet = stmnt.executeQuery();
			if(!resultSet.next()) {
				return "Käyttäjää ei löydy.";
			}
			else{
				sql = "SELECT username, password FROM " +users +" WHERE username = ? AND password = ?;";
				stmnt =  conn.prepareStatement(sql);
				stmnt.setString(1, currentUser);
				stmnt.setString(2, currentPassword);
				resultSet = stmnt.executeQuery();
				String realpassword = "";
				if(resultSet.next()) {
					realpassword = resultSet.getString("password");
				}
				if(!realpassword.equals(currentPassword)) {
					return "Väärä salasana.";
				}
				else {
					return "Kirjautuminen onnistui.";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (stmnt != null) {
				stmnt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	@Override
	public String addUser(String username, String password, String realname, String address) throws SQLException {
		try {
			Connection conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			String sql = "SELECT username FROM " +users +" WHERE username = ?;";
			PreparedStatement stmnt = conn.prepareStatement(sql);
			stmnt.setString(1, username);
			ResultSet resultSet = stmnt.executeQuery();
			String nameExists = "";
			if(resultSet.next()) {
				nameExists = resultSet.getString("username");
			}
			if(!nameExists.equals("")) {
				return "Käyttäjätunnus on jo varattu.";
			}
			else {
				sql = "INSERT INTO " +users +" (username, password, realname, address) VALUES (?, ?, ?, ?);";
				stmnt = conn.prepareStatement(sql);
				stmnt.setString(1, username);
				stmnt.setString(2, password);
				stmnt.setString(3, realname);
				stmnt.setString(4, address);
				stmnt.execute();
				return "Käyttäjä luotu.";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (stmnt != null) {
				stmnt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	@Override
	public ArrayList<String> getItemList(String category) throws SQLException{
		try {
			ArrayList<String> products = new ArrayList<String>();
			Connection conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			String sql = "SELECT name FROM "+items +" WHERE category = ?;";
			PreparedStatement stmnt = conn.prepareStatement(sql);
			stmnt.setString(1, category);
			ResultSet resultSet = stmnt.executeQuery();
			while(resultSet.next()) {
				products.add(resultSet.getString(1));
			}
			return products;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public ArrayList<String> UserInfoQuery(String username) throws SQLException{
		try {
			ArrayList<String> userinfo = new ArrayList<String>();
			Connection conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
			String sql = "SELECT realname, address FROM "+users +" WHERE username = ?;";
			PreparedStatement stmnt = conn.prepareStatement(sql);
			stmnt.setString(1, username);
			ResultSet resultSet = stmnt.executeQuery();
			while(resultSet.next()) {
				userinfo.add(resultSet.getString("realname"));
				userinfo.add(resultSet.getString("address"));
			}
			return userinfo;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public void addPurchase(ObservableList<Item> observableItemList, String user) throws SQLException {
		Connection conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
		Statement stmnt =  conn.createStatement();
		
		String sql1 = "INSERT INTO "+purchases+" (username, item1, amount_item1";
		String sql2 = "values ('" +user +"', '" +observableItemList.get(0).getName() +"', " +observableItemList.get(0).getAmount();
		int forincrement = 1;
		for(int i=1; i<observableItemList.size();i++) {
			forincrement = forincrement+1;
			sql1 = sql1 +", item" +forincrement +", amount_item" +forincrement;
			sql2 = sql2 +", '" +observableItemList.get(i).getName() +"', " +observableItemList.get(i).getAmount();
		}
		sql1 = sql1 +") ";
		sql2 = sql2 +");";
		String sql =sql1 + sql2;
		stmnt.execute(sql);
	}
	
	
	
	public ArrayList<Purchase> purchaseHistory(String user) throws SQLException {
		Connection conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
		Statement stmnt =  conn.createStatement();
		ArrayList<Purchase> purchasehistory = new ArrayList<Purchase>();
		String sql2 = "SELECT itemid FROM "+items+";";
		ResultSet resultSet2 = stmnt.executeQuery(sql2);
		int numberofitems=0;
		while(resultSet2.next()) {
			numberofitems++;
		}
		String sql = "SELECT * FROM "+purchases+" WHERE username = '" +user+"';";
		ResultSet resultSet = stmnt.executeQuery(sql);
		int incrementer=1;
		while(resultSet.next()) {
			Purchase purchase = new Purchase();
			ArrayList<Item> items = new ArrayList<Item>();
			String name = "item"+incrementer;
			String amount = "amount_item"+incrementer;
			for(int i = 0; i<numberofitems;i++) {
			String itemname = resultSet.getString(name);
			if(resultSet.wasNull()) {
				purchase.setPurchaseid(resultSet.getInt("purchasesid"));
				purchasehistory.add(purchase);
				incrementer=1;
				break;
			}
			int itemamount = resultSet.getInt(amount);
			Item item = new Item(itemname, itemamount);
			if(!resultSet.wasNull()) {
				purchase.addItem(item);
				incrementer++;
				name = "item"+incrementer;
				amount = "amount_item"+incrementer;
				if(incrementer>numberofitems) {
					incrementer=1;
					break;
				}
			}
			else {
				incrementer=1;
				break;
			}
		}
	}
		return purchasehistory;
}
}