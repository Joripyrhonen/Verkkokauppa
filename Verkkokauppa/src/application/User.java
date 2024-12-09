package application;

import java.util.ArrayList;

public class User {
	private String name;
	private String address;
	private ArrayList<Purchase> purhcaseHistory;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public void setPurhcaseHistory(ArrayList<Purchase> purhcaseHistory) {
		this.purhcaseHistory = purhcaseHistory;
	}
}
