package com.mycompany.builder;

import com.mycompany.builder.BookingBuilder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Representative {

	private BookingBuilder bookingBuilder;
	
	public void makeBooking(){
		bookingBuilder.parseRequest();
		bookingBuilder.createNewBooking();
		bookingBuilder.calculateBookingAmount();
		bookingBuilder.calculateMultipleDaysDiscount();
		bookingBuilder.calculateMembershipDiscount();
		bookingBuilder.calculateWeekdaysDiscount();
		bookingBuilder.calculateInsurance();
		bookingBuilder.calculateTotalPayment();
		bookingBuilder.verifyAge();
	}
	public String showBooking(){
		return bookingBuilder.showBooking();
	}
	
}
