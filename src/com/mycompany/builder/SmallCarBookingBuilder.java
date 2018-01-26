package com.mycompany.builder;

import java.math.BigDecimal;
import java.util.function.BiFunction;

import com.mycompany.model.BookingBuilder;

public class SmallCarBookingBuilder extends BookingBuilder {

	BigDecimal daylyRentalRate=BigDecimal.valueOf(40);
	BigDecimal insuranceRate= BigDecimal.valueOf(5);
	BigDecimal insuranceRateYoung= BigDecimal.valueOf(5).add(BigDecimal.valueOf(0.25));
			
	@Override
	public void calculateBookingAmount() {
		BigDecimal amount= calculateAmount.apply(BigDecimal.valueOf(booking.getBookingDates().size()), daylyRentalRate);
		booking.setAmount(amount) ;
	}
		
	@Override
	public void calculateWeekdaysDiscount() {
		BigDecimal weekdays=BigDecimal.valueOf(booking.getBookingDates().stream().filter(isWeekDays()).count());
		booking.setWeekdaydiscount(calculateWeekdaysDiscount.apply(weekdays, daylyRentalRate));
	}

	@Override
	public void calculateInsurance() {
		if(booking.getCustomerAge() >25){
			BigDecimal insurance= calculateInsurance.apply(insuranceRate, BigDecimal.valueOf(booking.getBookingDates().size()));
			booking.setInsurance(insurance);
		}else{
			BigDecimal insurance= calculateInsurance.apply(insuranceRateYoung, BigDecimal.valueOf(booking.getBookingDates().size()));
			booking.setInsurance(insurance);
		}
		
	}
}
