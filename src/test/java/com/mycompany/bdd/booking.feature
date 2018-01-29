Feature: Customer Booking
 Customer should pick the type of the car before car rental request

  Scenario: Customer elmer request older than 18 years old, rent a sport car for 3 days, 2 weekdays and one day weekend 
    Given that the customer elmer is 23 years old and days 3 and membership false
    When elmer selects type of car sport and model cherato and dates 2017-11-19T05:00:00.000Z,2017-11-20T05:00:00.000Z,2017-11-21T05:00:00.000Z so can choose the program
    Then elmer get subtotal 180.00 insuranceTotal 26.25 discount 21.00 totalPayment 185.25
    
  Scenario: Customer duffy request older than 18 years old, rent a sport car for 6 days, 6 weekdays
    Given that the customer duffy is 28 years old and days 6 and membership false
    When duffy selects type of car SUV and model vitoro and dates 2017-11-20T05:00:00.000Z,2017-11-21T05:00:00.000Z,2017-11-22T05:00:00.000Z,2017-11-23T05:00:00.000Z,2017-11-24T05:00:00.000Z,2017-11-27T05:00:00.000Z so can choose the program
    Then duffy get subtotal 600.00 insuranceTotal 60.00 discount 120.00 totalPayment 540.00    
    
