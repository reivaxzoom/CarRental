package com.mycompany.BookingBuilder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.mycompany.implementation.SportCarBookingBuilder;
import com.mycompany.model.Booking;
import com.mycompany.builder.BookingBuilder;
import com.mycompany.builder.Representative;
import com.mycompany.model.BookingRequest;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SmallCarBookingBuilderTest {

	BookingBuilder carBooking;
	
	Function<String, LocalDate> parseDates = e -> 
    LocalDate.parse(e, DateTimeFormatter.ISO_DATE_TIME);
    
	@Before
	public void setUp() throws Exception {
//		String input="{\"rentDates\":[\"2017-11-19T05:00:00.000Z\",\"2017-11-20T05:00:00.000Z\",\"2017-11-21T05:00:00.000Z\"],\"car\":{\"model\":\"cherato\",\"type\":\"sport\"},\"membership\":false,\"age\":24}"  ;
//		String input2="{\"rentDates\":[\"2017-11-18T05:00:00.000Z\",\"2017-11-19T05:00:00.000Z\",\"2017-11-20T05:00:00.000Z\",\"2017-11-21T05:00:00.000Z\",\"2017-11-22T05:00:00.000Z\",\"2017-11-25T05:00:00.000Z\"],\"car\":{\"model\":\"vitoro\",\"type\":\"SUV\"},\"membership\":false,\"age\":28}"  ;
		 
                
                 String bookingRequest="test1.json"  ;
//		 String expectedOutput="expected1.json";
		 Path path = Paths.get(bookingRequest);
		 List<String> input =Files.readAllLines(path);
                 
                 JsonReader reader;
		 reader = new JsonReader(new FileReader(bookingRequest));
                 BookingRequest request = new Gson().fromJson(reader,BookingRequest.class);
                 
		carBooking= new SportCarBookingBuilder();
		List<LocalDate> bookingDates=request.getRentDates().stream().map(parseDates).collect(Collectors.toList());
		Booking booking = new Booking();
		booking.setBookingDates(bookingDates);
		booking.setCar(request.getCar());
		booking.setCustomerAge(request.getAge());
		carBooking.setBooking(booking);
		carBooking.calculateBookingAmount();
	}
	@Test
	public void testCalculateWeekdaysDiscount() {
		String expectedWeekDayDiscount= "12.00";
		carBooking.calculateWeekdaysDiscount();
		String currentWeekDayDiscount= carBooking.getBooking().getWeekdaydiscount().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
		assertThat(currentWeekDayDiscount, is(expectedWeekDayDiscount));
	}

	@Test
	public void testCalculateInsurance() {
		carBooking.calculateInsurance();
		BigDecimal expectedInsurance= new BigDecimal("26.25");
		BigDecimal currentInsurance= carBooking.getBooking().getInsurance();
		assertThat(currentInsurance, is(expectedInsurance));
	}

	@Test
	public void testCalculateMembershipDiscount() {
		carBooking.calculateMembershipDiscount();
		BigDecimal expectedWeekDayDiscount= BigDecimal.ZERO;
		BigDecimal currentWeekDayDiscount= carBooking.getBooking().getMembershipDiscount();
		assertThat(currentWeekDayDiscount, is(expectedWeekDayDiscount));
	}

	@Test
	public void testCalculateMultipleDaysDiscount() {
		String expectedMultipleDayDiscount= "9.00";
		carBooking.calculateMultipleDaysDiscount();
		String currentMultipleDayDiscount= carBooking.getBooking().getMultipleDaydiscount().setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();;
		assertThat(currentMultipleDayDiscount, is(expectedMultipleDayDiscount));
	}

}
