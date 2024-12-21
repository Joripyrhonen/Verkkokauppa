package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Stack;

public class DataBaseConnection implements DBConnection{
	private final String mysqlurl = "jdbc:mysql://localhost:3306/verkkokauppa";
	private final String mysqluser = "root";
	private final String mysqlpassword = "Jatski85";

	private Connection conn;
	private Statement stmnt;

	private final String users = "verkkokauppa.users";
	private final String items = "verkkokauppa.items";
	private final String purchases = "verkkokauppa.purchases";
	@Override
	public String createConnection(String currentUser, String currentPassword) throws SQLException {
		try {
			stmnt = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword).createStatement();
			String sql = "SELECT username FROM " +users +" WHERE username = '" +currentUser +"';";
			ResultSet resultSet = stmnt.executeQuery(sql);
			if(!resultSet.next()) {
				return "Käyttäjää ei löydy.";
			}
			else{
				sql = "SELECT username, password FROM " +users + " WHERE username = '" +currentUser 
						+"' AND password = '" +currentPassword +"';";
				resultSet = stmnt.executeQuery(sql);
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
			Statement stmnt =  conn.createStatement();
			String sql = "SELECT username FROM " +users +" WHERE username = '" +username +"';";
			ResultSet resultSet = stmnt.executeQuery(sql);
			String nameExists = "";
			if(resultSet.next()) {
				nameExists = resultSet.getString("username");
			}
			if(!nameExists.equals("")) {
				return "Käyttäjätunnus on jo varattu.";
			}
			else {
				sql = "INSERT INTO " +users +"(username, password, realname, address)"
						+ "VALUES ('" +username +"', '" +password +"', '" +realname +"', '" +address +"');";
				stmnt.execute(sql);
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
			Statement stmnt =  conn.createStatement();
			String sql = "SELECT name FROM " +items +" WHERE category = '" +category+"';";
			ResultSet resultSet = stmnt.executeQuery(sql);
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
			Statement stmnt =  conn.createStatement();
			String sql = "SELECT realname, address FROM " +users +" WHERE username = '" +username+"';";
			ResultSet resultSet = stmnt.executeQuery(sql);
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
	public void addPurchase(ArrayList<String> purchaseditems, String user) throws SQLException {
		Connection conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
		Statement stmnt =  conn.createStatement();
		
		String sql1 = "INSERT INTO " +purchases +" (username, item1";
		String sql2 = "values ('" +user +"', '" +purchaseditems.get(0) +"'";
		int forincrement = 1;
		for(int i=1; i<purchaseditems.size();i++) {
			forincrement = forincrement+1;
			sql1 = sql1 +", item" +forincrement;
			sql2 = sql2 +", '" +purchaseditems.get(i) +"'";
		}
		sql1 = sql1 +") ";
		sql2 = sql2 +");";
		String sql =sql1 + sql2;
		stmnt.execute(sql);
	}
	public ArrayList<Purchase> purchaseHistory(String user) throws SQLException {
		Connection conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
		Statement stmnt =  conn.createStatement();
		Stack<Integer> purchaseids = new Stack();
		ArrayList<Purchase> purchasehistory = new ArrayList<Purchase>();
		String sql = "SELECT purchasesid FROM verkkokauppa.purchases WHERE username = '" +user+"';";
		ResultSet resultSet = stmnt.executeQuery(sql);
		while(resultSet.next()) {
			purchaseids.push(resultSet.getInt(1));
		}
		int larger = purchaseids.pop();
		int smaller = purchaseids.pop();
		sql = "SELECT * FROM " +purchases +" WHERE (username = '"+user+"' "
				+ "AND purchasesid = "+larger +") OR "
				+ "(username = '" +user +"' and purchasesid = "+smaller +");";
		resultSet = stmnt.executeQuery(sql);
		while(resultSet.next()) {
			Purchase purchase = new Purchase();
			ArrayList<String> items = new ArrayList<String>();
			purchase.setItemNames(items);
			purchase.setPurchaseid(resultSet.getInt("purchasesid"));
			purchase.additem(resultSet.getString("item1"));
			purchase.additem(resultSet.getString("item2"));
			purchase.additem(resultSet.getString("item3"));
			purchase.additem(resultSet.getString("item4"));
			purchasehistory.add(purchase);
		}
		return purchasehistory;
		
	}
}
