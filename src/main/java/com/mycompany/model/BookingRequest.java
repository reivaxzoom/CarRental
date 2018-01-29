package com.mycompany.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookingRequest{
	private List<String> rentDates;
	private Car car;
	private Boolean membership;
	private Integer age;
	@Override
	public String toString() {
		return "BookingRequest [rentDates=" + rentDates + ", car=" + car
				+ ", membership=" + membership + ", age=" + age + "]";
	}
}
