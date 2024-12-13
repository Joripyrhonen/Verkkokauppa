package model;

import java.sql.*;

public class DataBaseConnection {
//	public static void main(String[] args) throws SQLException {
//		launch(args);
//		Connection conn = null;
//		Statement stmnt = null;
//		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/verkkokauppa", "root", "Jatski85");
//		stmnt = conn.createStatement();
//		String sql = "INSERT INTO Verkkokauppa.users (username, password)"
//				+"VALUES ('pepe', 'salasana');";
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
	
	public void createConnection(String chosenDatabase) throws SQLException {
		//Jos on valittuna tietokannoista MySQL
		if (chosenDatabase.equals("MySQL")) {
			System.out.println("Varmasti yhdistettynä tietokantaan");
//			Connection conn = null;
//			Statement stmnt = null;
//
//			try {
//				//Yhdistys tietokantaan
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				conn = DriverManager.getConnection(mysqlurl, mysqluser, mysqlpassword);
//				//MySQL statement
//				stmnt = conn.createStatement();
//				//Excecute SQL query
//				String sql = "INSERT INTO Verkkokauppa.users (username, password)"
//						+"VALUES ('pepe', 'salasana');";
//				stmnt.execute(sql);
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				if (stmnt != null) {
//					stmnt.close();
//				}
//				if (conn != null) {
//					conn.close();
//				}
//			}
		}
		//Jos on valittuna tietokannoista MongoDB
		else if(chosenDatabase.equals("MongoDB")) {
			System.out.println("MongoDB toiminnallisuutta ei ole luotu vielä");
			}
		}

	}
