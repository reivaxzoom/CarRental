package com.mycompany.rentalCarBdd;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty", "html:target/html"},
        features = {"classpath:booking.feature"},
        snippets = SnippetType.CAMELCASE
)
public class BookingTest {

}
