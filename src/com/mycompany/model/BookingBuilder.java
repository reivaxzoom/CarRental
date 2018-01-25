package com.mycompany.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;


public abstract class BookingBuilder {

	protected Booking booking;
	
	
	public void createNewBooking(){
		booking = new Booking();
	}
	
//builder methods
	public abstract void applyDiscount();
	
	public abstract void applyInsurancePolice();
	
	public abstract void calculateTotalPayment();
	
	public abstract void  calculateBookingAmount();
	
//Time policies 
	protected abstract void rentSingleDay(LocalDate date);
	protected abstract void weekendsrental(List<LocalDate> dates);
	protected abstract void multipleDayRental(List<LocalDate> dates);
	
	public static Predicate<LocalDate> isWeekDays() {
	    return p -> p.getDayOfWeek() !=DayOfWeek.SATURDAY  && p.getDayOfWeek() !=DayOfWeek.SUNDAY;
	}
	
	
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
}
