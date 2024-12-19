package model;

import java.sql.SQLException;

public interface DBConnection {
	public String createConnection(String user, String password) throws SQLException;
	public String addUser(String username, String password, String realname, String address) throws SQLException;
}
