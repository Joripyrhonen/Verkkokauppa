package model;
import controller.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Purchase {
	private ArrayList<Item> items;
	private LocalDateTime purchaseDate;
	public ArrayList<Item> getItems() {
		return items;
	}
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
}
