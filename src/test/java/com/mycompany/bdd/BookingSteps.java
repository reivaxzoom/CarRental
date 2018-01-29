package com.mycompany.bdd;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.Function;

import org.hamcrest.core.IsNull;

import com.mycompany.builder.SUVCarBookingBuilder;
import com.mycompany.builder.SmallCarBookingBuilder;
import com.mycompany.builder.SportCarBookingBuilder;
import com.mycompany.model.BookingBuilder;
import com.mycompany.model.BookingRequest;
import com.mycompany.model.Car;
import com.mycompany.model.Representative;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class BookingSteps {
	BookingRequest request = new BookingRequest();
	Representative representative = new Representative(); 
	BookingBuilder carBooking;
	Function<BigDecimal, String> printVal = e -> e.setScale(2, BigDecimal.ROUND_HALF_EVEN).toString();
   
   @Given("^that the customer (.*) is (\\d+) years old and days (\\d+) and membership (.*)$")
   public void isAbleToRent(String name , Integer age , Integer days, boolean membership) throws Throwable{
		request.setAge(Integer.valueOf(age));
		request.setMembership(membership);
		assertThat(request.getAge(),  greaterThanOrEqualTo(18));
		assertThat(request.getAge(), IsNull.notNullValue());
		assertThat(request.getMembership(), IsNull.notNullValue());
   }
   
   @When("^(.*) selects type of car (.*) and model (.*) and dates (.*) so can choose the program$")
	public void makeBooking(String name, String type, String model, String dates) throws Throwable {
	    switch (type) {
		case "small":
			carBooking= new SmallCarBookingBuilder();
			representative.setBookingBuilder(carBooking);
			break;
		case "sport":
			carBooking= new SportCarBookingBuilder();
			representative.setBookingBuilder(carBooking);
			break;	
		case "SUV":
			carBooking= new SUVCarBookingBuilder();
			representative.setBookingBuilder(carBooking);
			break;	
		}
	    request.setCar(new Car(model, type));
	    request.setRentDates(Arrays.asList(dates.split(",")));
	    carBooking.setRequest(request);
		representative.setBookingBuilder(carBooking);
		representative.makeBooking();
		assertThat(carBooking.getBooking().getSubtotal(), IsNull.notNullValue());
   }

   @Then("^(.*) get subtotal (.*) insuranceTotal (.*) discount (.*) totalPayment (.*)$")
	public void verifyBooking(String name,String subtotal, String insuranceTotal, String discountPercentage, String totalPayment ) throws Throwable {
		 assertThat(printVal.apply(carBooking.getBooking().getSubtotal()), is(subtotal));
		 assertThat(printVal.apply(carBooking.getBooking().getInsurance()), is(insuranceTotal));
		 assertThat(printVal.apply(carBooking.getBooking().getTotalDiscounts()), is(discountPercentage));
		 assertThat(printVal.apply(carBooking.getBooking().getTotal()), is(totalPayment));
		 
	}
}