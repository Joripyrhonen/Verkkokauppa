package model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DBConnection {
	public String createConnection(String user, String password) throws SQLException;
	public String addUser(String username, String password, String realname, String address) throws SQLException;
	public ArrayList<String> getItemList(String category) throws SQLException;
	public ArrayList<String> UserInfoQuery(String username) throws SQLException;
	public void addPurchase(ArrayList<String> purchaseditems, String user) throws SQLException;
}
