# Bus Ticket API
This is a simple API application where you can make a POST request to calculate bus ticket prices when providing ticket data in the request body.
--------
To develop this project I have used Intellij IDE (JavaSE-17), Spring framework (2.5.6), Maven and Postman. This is a test assignment where I had to create a web service for bus tickets using clean architecture and clean code principles, as well to include unit tests. As a reference for clean architecture I used Robert C. Martin blog post (https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

As for my understanding the most important part was to seperate logic by layers as well as dependencies should point only inwards. These are the main files for the application:

**BusfareApplication.java** -> **BusTicketPriceDraftController.java** -> **BusTicketPriceDraftService.java** -> **DraftTicket.java**.

--------
### BusTicketPriceDraftController.java
Handles a POST request to uri: "/ticket" and excpects data from the request body, deserializes the received json to a POJO using a different service, then
passes it to the service responsible for calculations and afterwards returns a serialized response json with prices and all the necessary data to dispaly the information as per the task requirements.

### BusTicketPriceDraftService.java
Receives the request data from the Controller, retrieves base price and tax rate from different services and passes all this information to the next layer for calculations, afterwards returns a POJO with necessary data.

### DraftTicket.java
This class has all the high level implementation logic. This class creates a new object that will be sent back as the response, calculates then populates a list with the ticket price values, calculates the total price and returns this POJO.

--------
To run the application type java -jar (your path)/target/busfareapi-0.0.1-SNAPSHOT.jar in console or run BusfareApplication.java from your IDE. For the request use url: http://localhost:8080/ticket and include the content from jsonExampleForRequestBody as the request body.

Improvements needed:

0) Add proper exception handling;
1) Would need someone with more experience to check if the project structure is correctly implemented regards to clean architecture.
2) Should write more integration tests, as of now the funcionality is covered by unit tests and one acceptance test.
