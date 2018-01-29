package com.mycompany.implementation;

import java.math.BigDecimal;

import com.mycompany.builder.BookingBuilder;

public class SportCarBookingBuilder extends BookingBuilder {

	BigDecimal daylyRentalRate=new BigDecimal(60);
	BigDecimal insuranceRate= new BigDecimal(7);
	BigDecimal insuranceRateYoung= insuranceRate.add(insuranceRate.multiply(new BigDecimal(0.25)));
			
	@Override
	public void calculateBookingAmount() {
		BigDecimal amount= calculateAmount.apply(BigDecimal.valueOf(booking.getBookingDates().size()), daylyRentalRate);
		booking.setSubtotal(amount) ;
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
