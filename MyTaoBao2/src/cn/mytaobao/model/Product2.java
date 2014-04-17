package cn.mytaobao.model;

import android.graphics.drawable.Drawable;

public class Product2 {
	private int id;
	private int image;
	private String name;
	private String price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Product2(int id, int image, String name, String price) {
		super();
		this.id = id;
		this.image = image;
		this.name = name;
		this.price = price;
	}

	public Product2() {

	}

}
