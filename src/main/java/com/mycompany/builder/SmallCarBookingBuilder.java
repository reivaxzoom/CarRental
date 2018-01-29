package com.mycompany.builder;

import java.math.BigDecimal;

import com.mycompany.model.BookingBuilder;

public class SmallCarBookingBuilder extends BookingBuilder {

	BigDecimal daylyRentalRate=BigDecimal.valueOf(40);
	BigDecimal insuranceRate= BigDecimal.valueOf(5);
	BigDecimal insuranceRateYoung= insuranceRate.add(BigDecimal.valueOf(0.25));
			
	@Override
	public void calculateBookingAmount() {
		BigDecimal subtotal= calculateAmount.apply(BigDecimal.valueOf(booking.getBookingDates().size()), daylyRentalRate);
		booking.setSubtotal(subtotal) ;
	}
		
	@Override
	public void calculateWeekdaysDiscount() {
		booking.setWeekdaydiscount(calculateWeekdaysDiscount.apply(booking, daylyRentalRate));
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
