package model;

import java.sql.*;

public class DataBaseConnection implements DBConnection{
	//	public static void main(String[] args) throws SQLException {
	//		launch(args);
	//		Connection conn = null;
	//		Statement stmnt = null;
	//		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/verkkokauppa", "root", "Jatski85");
	//		stmnt = conn.createStatement();
	//		String sql = "INSERT INTO Verkkokauppa.users (username, password)"
	//				+"VALUES ('pepe2', 'salasana2');";
	//		stmnt.execute(sql);
	//	}
	//	private static void launch(String[] args) {
	//		
	//	}
	//MySQL tietokannan tiedot
	private final String mysqlurl = "jdbc:mysql://localhost:3306/verkkokauppa";
	private final String mysqluser = "root";
	private final String mysqlpassword = "Jatski85";

	//MongoDB tietokannan tiedot, jotka tulisivat täytettyä myöhemmin
	private final String mongodburl = "a";
	private final String mongodbuser = "b";
	private final String mongodbpassword = "c";

	private Connection conn;
	private Statement stmnt;

	private final String users = "verkkokauppa.users";
	private final String items = "verkkokauppa.items";
	@Override
	public String createConnection(String currentUser, String currentPassword) throws SQLException {
		try {
			stmnt = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword).createStatement();
			//Excecute SQL query
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
					System.out.println(realpassword);
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
}
