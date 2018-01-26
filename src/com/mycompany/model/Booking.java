package com.mycompany.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Booking {
	private boolean isAbleToRent;
	private String[] plainDates;
	private List<LocalDate> bookingDates;
	private Car car;
	private BigDecimal amount;
	private int customerAge;
	
	private BigDecimal multipleDaydiscount;
	private BigDecimal weekdaydiscount;
	private BigDecimal membershipDiscount;
	private BigDecimal insurance;
	private BigDecimal total;
	private BigDecimal totalDiscounts;
	private boolean membership;


		
	@Override
	public String toString() {
		return "Booking [amount=" + amount + ", insurance=" + insurance
				+ ", totalDiscounts=" + totalDiscounts + ", total=" + total
				+ "]";
	}




	
	
}
