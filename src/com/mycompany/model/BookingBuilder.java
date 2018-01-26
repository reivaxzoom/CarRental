package com.mycompany.model;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class BookingBuilder {

	protected Booking booking;
	protected BigDecimal weekdayDiscount = new BigDecimal(0.1);
	protected BigDecimal multipleDaysDiscount3to5 = new BigDecimal(0.05);
	protected BigDecimal multipleDaysDiscount6to10 = new BigDecimal(0.1);
	protected BigDecimal multipleDaysDiscount11up = new BigDecimal(0.15);
	protected BigDecimal membershipDiscount = new BigDecimal(0.05);
	protected int legalAgeToRent =18;
	
	protected BiFunction<BigDecimal,BigDecimal, BigDecimal> calculateAmount =
			(days, daylyRentalRate) -> days.multiply(daylyRentalRate); 
	
    protected BiFunction<BigDecimal,BigDecimal, BigDecimal> calculateWeekdaysDiscount = 
			(weekdays, daylyRentalRate) -> daylyRentalRate.multiply(weekdays).multiply(weekdayDiscount);
	
	protected BiFunction<Integer,BigDecimal,BigDecimal> calculateMultDayDiscount = 
			(days, discount) -> BigDecimal.valueOf(days).multiply(discount) ;

	protected BiFunction<BigDecimal,BigDecimal,BigDecimal> calculateMembershipDiscount = 
					(amount, discount) -> amount.multiply(discount) ;
	
	protected BiFunction<BigDecimal, BigDecimal, BigDecimal> calculateInsurance =
			(days, rate) -> days.multiply(rate);
			
			
	public Predicate<LocalDate> isWeekDays() {
			    return day -> day.getDayOfWeek() !=DayOfWeek.SATURDAY  && day.getDayOfWeek() !=DayOfWeek.SUNDAY;
	}
	Function<String, LocalDate> parseDates = e -> 
	       LocalDate.parse(e, DateTimeFormatter.ISO_DATE_TIME); 		
			
	public void createNewBooking(){
		booking.setBookingDates(Arrays.asList(booking.getPlainDates()).stream().map(parseDates).collect(Collectors.toList()));
		booking.setAbleToRent(isAbleToRent(booking.getCustomerAge()));
	}
	
	public abstract void calculateWeekdaysDiscount();

	public abstract void calculateBookingAmount();
	
	public abstract void calculateInsurance();
	
	public void calculateTotalPayment() {
		BigDecimal discounts =booking.getMembershipDiscount().add(booking.getMultipleDaydiscount()).add(booking.getWeekdaydiscount());
		BigDecimal amounts = booking.getAmount().add(booking.getInsurance()); 
		BigDecimal total=amounts.subtract(discounts);
		booking.setTotalDiscounts(discounts);
		booking.setTotal(total);
	}
	
	public void calculateMembershipDiscount(){
		if(booking.isMembership()){
			BigDecimal discount=calculateMembershipDiscount.apply(booking.getAmount(), membershipDiscount);
			booking.setMembershipDiscount(discount);
		}else{
			booking.setMembershipDiscount(BigDecimal.ZERO);
		}
	}
	
	public void calculateMultipleDaysDiscount() {
		int days=booking.getBookingDates().size();
		if(days >=3 && days <=5){
			BigDecimal multDaydiscount=calculateMultDayDiscount.apply(days, multipleDaysDiscount3to5);
			booking.setMultipleDaydiscount(multDaydiscount);
		}
		else if(days >=6 && days <=10){
			BigDecimal multDaydiscount=calculateMultDayDiscount.apply(days, multipleDaysDiscount6to10);
			booking.setMultipleDaydiscount(multDaydiscount);
		}
		else if (days >=11){
			BigDecimal multDaydiscount=calculateMultDayDiscount.apply(days, multipleDaysDiscount11up);
			booking.setMultipleDaydiscount(multDaydiscount);
		}
		else {
			booking.setMultipleDaydiscount(BigDecimal.ZERO);
		}
		
	}
	
	public void showBooking(){
		System.out.println(booking);
	};
	
	public boolean isAbleToRent(int age){
		if( age > legalAgeToRent)
			return true;
		else
			return false;
	}
}
