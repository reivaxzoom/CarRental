package com.mycompany.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {
	private String model;
	private String type;
	@Override
	public String toString() {
		return "Car [model=" + model + ", type=" + type + "]";
	}

	public Car(String model, String type) {
		super();
		this.model = model;
		this.type = type;
	}
}
