package com.mycompany.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Representative {

	private BookingBuilder bookingBuilder;
	
	public void makeBooking(){
		bookingBuilder.createNewBooking();
		bookingBuilder.calculateBookingAmount();
		bookingBuilder.calculateMultipleDaysDiscount();
		bookingBuilder.calculateMembershipDiscount();
		bookingBuilder.calculateWeekdaysDiscount();
		bookingBuilder.calculateInsurance();
		bookingBuilder.calculateTotalPayment();
		bookingBuilder.showBooking();
	}
}
