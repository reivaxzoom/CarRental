package com.mycompany.model;

import com.mycompany.builder.SmallCarBookingBuilder;

public class CarRentalDemo {

	public static void main(String[] args) {
		
	
		Booking booking = new Booking();
		booking.setPlainDates(new String[]{"2017-11-19T05:00:00.000Z","2017-11-20T05:00:00.000Z","2017-11-21T05:00:00.000Z"});
		booking.setCar(new Car("Cherato","sport"));
		booking.setCustomerAge(25);
		booking.setMembership(false);
		
		
		BookingBuilder sportCarBooking= new SmallCarBookingBuilder();
		sportCarBooking.setBooking(booking);
		
		Representative representative= new Representative();
		representative.setBookingBuilder(sportCarBooking);
		representative.makeBooking();
	}
	
	
}
