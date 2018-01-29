# Car Rental
Sample project for a car Rental Business

## Instructions
Clone the repo:

### Git:

$ git clone https://github.com/reivaxzoom/CarRental.git


## Prerequisites
In order to run the demo, maven should be installed.

File input.json has the input data for carRentalDemostration

```
C:\...\CarRental> mvn clean install
C:\...\CarRental> java -jar target/carRental-1.0-jar-with-dependencies.jar input.json
```
input.json
```JSON
{"rentDates":["2017-11-19T05:00:00.000Z","2017-11-20T05:00:00.000Z","2017-11-21T05:00:00.000Z"],"car":{"model":"Eveo","type":"SUV"},"membership":false,"age":30}
```
output
```JSON
{"subtotal":"300.00","insuranceTotal":"30.00","discountPercentage":"35.00","totalPayment":"295.00"}
```

