package model;

import java.awt.image.BufferedImage;

import javafx.beans.property.SimpleStringProperty;

public class Item {
	private SimpleStringProperty name;
	private BufferedImage picture;
	private String category;
	private int amount = 1;

	public void setAmount(int i) {
		this.amount = i;
	}

	public int getAmount() {
		return amount;
	}

	public void increaseAmount() {
		amount++;
	}

	public void reduceAmount() {
		amount--;
	}

	public Item(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public Item() {

	}

	public Item(String string, int int1) {
		this.name = new SimpleStringProperty(string);
		amount = int1;

	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}

	public BufferedImage getPicture() {
		return picture;
	}

	public void setPicture(BufferedImage picture) {
		this.picture = picture;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
