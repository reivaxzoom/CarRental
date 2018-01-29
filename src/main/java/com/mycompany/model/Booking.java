package com.mycompany.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

import com.google.gson.Gson;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Booking {
	private List<String> plainDates;
	private List<LocalDate> bookingDates;
	private Car car;
	private BigDecimal subtotal;
	private int customerAge;
	private BigDecimal multipleDaydiscount;
	private BigDecimal weekdaydiscount;
	private BigDecimal membershipDiscount;
	private BigDecimal insurance;
	private BigDecimal total;
	private BigDecimal totalDiscounts;
	private boolean membership;
	@Override
	public String toString() {
		Function<BigDecimal, String> printVal = e -> e.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
		BookingOutput output= new BookingOutput();
		output.setDiscountPercentage(printVal.apply(totalDiscounts));
		output.setInsuranceTotal(printVal.apply(insurance));
		output.setSubtotal(printVal.apply(subtotal));
		output.setTotalPayment(printVal.apply(total));
		return output.toString();
	}
}

@Setter
@Getter
class BookingOutput{
	String subtotal; 
	String insuranceTotal;
	String discountPercentage;
	String totalPayment;
	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}