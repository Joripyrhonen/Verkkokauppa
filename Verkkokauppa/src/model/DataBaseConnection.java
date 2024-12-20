package model;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseConnection implements DBConnection{
	private final String mysqlurl = "jdbc:mysql://localhost:3306/verkkokauppa";
	private final String mysqluser = "root";
	private final String mysqlpassword = "Jatski85";

	private Connection conn;
	private Statement stmnt;

	private final String users = "verkkokauppa.users";
	private final String items = "verkkokauppa.items";
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
}
