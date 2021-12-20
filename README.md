# Bus lines challenge

The solution is a Spring Boot application that fetches data from the api specified at http://www.trafiklab.se/api/sl-hallplatser-och-linjer-2 and prints the result to a browser page.

## Technologies
- Java 11, Maven, Spring Boot v2.6.1


## Run the application
- Mac OS terminal: Clone the repository and navigate to the project root folder "bus-lines".
- Run the command ./mvnw spring-boot:run
- To access the application (and make the call to fetch the result data) navigate to localhost:8080/buslines/most-stops in a browser.
- The result will be printed to the browser page when the fetch is done.

## Project
- Entry point in the code is line.LineController.java which handles the call to localhost:8080/buslines/most-stops.
- The resulting json strings from the calls made in the application to the endpoints at trafiklab.se, are parsed to respectively *Response classes that corresponds to the 
json string received, e.g. stop.StopPointResponse.java
