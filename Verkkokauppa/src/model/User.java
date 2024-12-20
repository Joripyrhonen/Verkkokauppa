package model;
import java.util.ArrayList;

public class User {
	private String userName;
	private String realName;
	private String password;
	private String address;
	private ArrayList<Purchase> purhcaseHistory;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public ArrayList<Purchase> getPurhcaseHistory() {
		return purhcaseHistory;
	}
	public void addPurhcase(Purchase purhcase) {
		this.purhcaseHistory.add(purhcase);
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(String userName, String password) {
		userName = this.userName;
		password = this.password;
	}
	public User() {
		
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
}
