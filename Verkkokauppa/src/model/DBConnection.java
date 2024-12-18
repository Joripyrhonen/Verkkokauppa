package model;

import java.sql.SQLException;

public interface DBConnection {
	public String createConnection(String user, String password) throws SQLException;
}
