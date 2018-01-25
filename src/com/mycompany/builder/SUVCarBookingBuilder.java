package com.mycompany.builder;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.mycompany.model.BookingBuilder;

public class SUVCarBookingBuilder extends BookingBuilder {

	
	
	@Override
	public void calculateBookingAmount() {
		booking.getBookingDates();
		calculateAmountWeekdays( booking.getBookingDates().stream().filter(isWeekDays()).collect(Collectors.toList()));
		

		
		
	}
	
	
	@Override
	public void applyDiscount() {	
		// TODO Auto-generated method stub
		
	}

	@Override
	public void applyInsurancePolice() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void calculateTotalPayment() {
		// TODO Auto-generated method stub
		

	}

	@Override
	protected void rentSingleDay(LocalDate date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void weekendsrental(List<LocalDate> dates) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void multipleDayRental(List<LocalDate> dates) {
		// TODO Auto-generated method stub
		
	}



}
