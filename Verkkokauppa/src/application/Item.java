package application;

import java.awt.image.BufferedImage;

public class Item {
	private String name;
	private BufferedImage picture;
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

}
