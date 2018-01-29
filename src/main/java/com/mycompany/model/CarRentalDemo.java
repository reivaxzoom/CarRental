package com.mycompany.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.mycompany.builder.SUVCarBookingBuilder;
import com.mycompany.builder.SmallCarBookingBuilder;
import com.mycompany.builder.SportCarBookingBuilder;

public class CarRentalDemo {
	private static final Logger LOGGER = Logger.getLogger(CarRentalDemo.class
			.getName());

	public static void main(String[] args) {
		rentalCar(args[0]);
	}

	public static String rentalCar(String inputFile) {
		JsonReader reader;
		Representative representative = new Representative();
		try {
			reader = new JsonReader(new FileReader(inputFile));

			BookingRequest request = new Gson().fromJson(reader,
					BookingRequest.class);
			BookingBuilder carBooking;

			switch (request.getCar().getType()) {
			case "small":
				carBooking = new SmallCarBookingBuilder();
				carBooking.setRequest(request);
				representative.setBookingBuilder(carBooking);
				break;
			case "sport":
				carBooking = new SportCarBookingBuilder();
				carBooking.setRequest(request);
				representative.setBookingBuilder(carBooking);
				break;
			case "SUV":
				carBooking = new SUVCarBookingBuilder();
				carBooking.setRequest(request);
				representative.setBookingBuilder(carBooking);
				break;
			default:
				System.out.println("Car type not avaiable");
			}
			representative.makeBooking();
			System.out.println(representative.showBooking());

		} catch (FileNotFoundException e) {
			LOGGER.log(Level.SEVERE, "File not found", e);
		}
		return representative.showBooking();
	}
}