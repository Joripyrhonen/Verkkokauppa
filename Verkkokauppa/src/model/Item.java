package model;
import java.awt.image.BufferedImage;

public class Item {
	private String name;
	private BufferedImage picture;
	private String category;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
