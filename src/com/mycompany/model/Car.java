package com.mycompany.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {
	private String model;
	private String type;
	private String price;

	public Car(String model, String type) {
		super();
		this.model = model;
		this.type = type;
	}
	
	
}
