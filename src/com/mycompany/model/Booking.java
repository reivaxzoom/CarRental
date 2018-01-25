package com.mycompany.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Booking {
	private List<LocalDate> bookingDates;
	private Car car;
	private BigDecimal amount;
	private int customerAge;
	
	private boolean membership;
	
	
	public boolean isMembership() {
		return membership;
	}
	public void setMembership(boolean membership) {
		this.membership = membership;
	}
	public List<LocalDate> getBookingDates() {
		return bookingDates;
	}
	public void setBookingDates(List<LocalDate> bookingDates) {
		this.bookingDates = bookingDates;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public int getCustomerAge() {
		return customerAge;
	}
	public void setCustomerAge(int customerAge) {
		this.customerAge = customerAge;
	}
	
	
	
}
