package com.mycompany.model;

import java.time.LocalDate;
import java.util.ArrayList;

import com.mycompany.builder.SmallCarBookingBuilder;

public class CarRentalDemo {

	public static void main(String[] args) {
		
		Booking booking = new Booking();
		booking.setBookingDates(new ArrayList<LocalDate>());
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
