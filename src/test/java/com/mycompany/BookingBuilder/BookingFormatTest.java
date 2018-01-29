package com.mycompany.BookingBuilder;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import com.mycompany.executor.CarRentalDemo;

/**
 * Test class for format validation
 * @author Xavier
 *
 */
public class BookingFormatTest {

	@Test
	    public void testRentalCar() throws IOException {
		 String bookingRequest="test1.json"  ;
		 String expectedOutput="expected1.json";
		 Path path = Paths.get(expectedOutput);
		 List<String> expOut=Files.readAllLines(path);
		 
		 String output =CarRentalDemo.rentalCar(bookingRequest);
		 assertThat(output, is(expOut.get(0)));
	    }
}
