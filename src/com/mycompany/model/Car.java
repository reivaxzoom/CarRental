package com.mycompany.model;

public class Car {
	private String model;
	private String type;
	private String price;

	public String getModel() {
		return model;
	}
	public Car(String model, String type) {
		super();
		this.model = model;
		this.type = type;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

	
	
}
