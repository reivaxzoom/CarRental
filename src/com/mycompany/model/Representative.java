package com.mycompany.model;

public class Representative {

	private BookingBuilder bookingBuilder;
	
	public void makeBooking(){
		bookingBuilder.createNewBooking();
		bookingBuilder.calculateBookingAmount();
		bookingBuilder.applyInsurancePolice();
		bookingBuilder.applyDiscount();
		bookingBuilder.calculateTotalPayment();
	}

	public BookingBuilder getBookingBuilder() {
		return bookingBuilder;
	}

	public void setBookingBuilder(BookingBuilder bookingBuilder) {
		this.bookingBuilder = bookingBuilder;
	}
	
	
	
}
