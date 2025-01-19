package model;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Purchase {
	private String user;
	private ArrayList<Item> items;
	private ArrayList<String> itemnames;
	private LocalDateTime purchaseDate;
	private int purchaseid;
	
	public Purchase () {
		items = new ArrayList<Item>();
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public ArrayList<Item> getItems() {
		return items;
	}
	public void setItemNames(ArrayList<String> itemnames) {
		this.itemnames = itemnames;
	}
	public ArrayList<String> getItemNames() {
		return itemnames;
	}
	public void additem(String item) {
		itemnames.add(item);
	}
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	public void addItem(Item item) {
		items.add(item);
	}
	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public int getPurchaseid() {
		return purchaseid;
	}
	public void setPurchaseid(int purchaseid) {
		this.purchaseid = purchaseid;
	}
}
