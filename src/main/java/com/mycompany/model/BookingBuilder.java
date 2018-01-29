package com.mycompany.model;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class BookingBuilder {

	protected BookingRequest request;
	protected Booking booking;
	protected BigDecimal weekdayDiscount = new BigDecimal(0.1);
	protected BigDecimal multipleDaysDiscount3to5 = new BigDecimal(0.05);
	protected BigDecimal multipleDaysDiscount6to10 = new BigDecimal(0.1);
	protected BigDecimal multipleDaysDiscount11up = new BigDecimal(0.15);
	protected BigDecimal membershipDiscount = new BigDecimal(0.05);
	protected int legalAgeToRent =18;
	
	protected BiFunction<BigDecimal,BigDecimal, BigDecimal> calculateAmount =
			(days, daylyRentalRate) -> days.multiply(daylyRentalRate); 
	
    protected BiFunction<Booking,BigDecimal, BigDecimal> calculateWeekdaysDiscount = 
			(b, discount) ->  {
				BigDecimal days=BigDecimal.valueOf(b.getBookingDates().size());
				BigDecimal weekdays=BigDecimal.valueOf(b.getBookingDates().stream().filter(isWeekDays()).count());
				BigDecimal daylyRentalRate= b.getSubtotal().divide(days, 2, BigDecimal.ROUND_HALF_UP);
				return weekdays.multiply(daylyRentalRate).multiply(discount); 
			};
	
	protected BiFunction<Booking,BigDecimal,BigDecimal> calculateMultDayDiscount = 
					(b, discount) ->  b.getSubtotal().multiply(discount) ;		
			

	protected BiFunction<BigDecimal,BigDecimal,BigDecimal> calculateMembershipDiscount = 
					(amount, discount) -> amount.multiply(discount) ;
	
	protected BiFunction<BigDecimal, BigDecimal, BigDecimal> calculateInsurance =
			(days, rate) -> days.multiply(rate);
			
			
	protected Predicate<LocalDate> isWeekDays() {
			    return day -> day.getDayOfWeek() !=DayOfWeek.SATURDAY  && day.getDayOfWeek() !=DayOfWeek.SUNDAY;
	}
	protected Function<String, LocalDate> parseDates = e -> 
	       LocalDate.parse(e, DateTimeFormatter.ISO_DATE_TIME); 		
	
	       
	protected void createNewBooking(){
		booking.setBookingDates(booking.getPlainDates().stream().map(parseDates).collect(Collectors.toList()));
	}
	
	public void calculateWeekdaysDiscount() {
		booking.setWeekdaydiscount(calculateWeekdaysDiscount.apply(booking, weekdayDiscount));
	}

	public abstract void calculateBookingAmount();
	
	public abstract void calculateInsurance();
	
	/**
	 * Sum subtotal and insurance then substract all discounts
	 */
	protected void calculateTotalPayment() {
		BigDecimal discounts =booking.getMembershipDiscount().add(booking.getMultipleDaydiscount()).add(booking.getWeekdaydiscount());
		BigDecimal amounts = booking.getSubtotal().add(booking.getInsurance()); 
		BigDecimal total=amounts.subtract(discounts);
		booking.setTotalDiscounts(discounts);
		booking.setTotal(total);
	}
	
	public void calculateMembershipDiscount(){
		if(booking.isMembership()){
			BigDecimal discount=calculateMembershipDiscount.apply(booking.getSubtotal(), membershipDiscount);
			booking.setMembershipDiscount(discount);
		}else{
			booking.setMembershipDiscount(BigDecimal.ZERO);
		}
	}
	
	/*
	 * Calculate discount based one the number of days booked
	 */
	
	public void calculateMultipleDaysDiscount() {
		int days=booking.getBookingDates().size();
		if(days >=3 && days <=5){
			BigDecimal multDaydiscount=calculateMultDayDiscount.apply(booking, multipleDaysDiscount3to5);
			booking.setMultipleDaydiscount(multDaydiscount);
		}
		else if(days >=6 && days <=10){
			BigDecimal multDaydiscount=calculateMultDayDiscount.apply(booking, multipleDaysDiscount6to10);
			booking.setMultipleDaydiscount(multDaydiscount);
		}
		else if (days >=11){
			BigDecimal multDaydiscount=calculateMultDayDiscount.apply(booking, multipleDaysDiscount11up);
			booking.setMultipleDaydiscount(multDaydiscount);
		}
		else {
			booking.setMultipleDaydiscount(BigDecimal.ZERO);
		}
		
	}

	/**
	 * Transform request input into booking
	 */
	protected void parseRequest(){
		Booking booking = new Booking();
		booking.setPlainDates(request.getRentDates());
		booking.setCar(request.getCar());
		booking.setCustomerAge(request.getAge());
		booking.setMembership(request.getMembership());
		setBooking(booking);
	}

	protected String showBooking(){
		return booking.toString();
	};
	
	/**
	 * validate age lower boundery for car rental
	 * @return
	 */
	protected boolean verifyAge(){
		if( booking.getCustomerAge() > legalAgeToRent)
			return true;
		else
			booking = new Booking();
			return false;
	}
}
