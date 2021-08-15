Customer Order Service
The App does following:

    1.Place an order, linked to a customer
    2.Retrieve a customer with their linked order(s)
    3.Load all customers and their linked orders

To build the App: mvn clean install



To Build the App without unit Tests: mvn clean install -DskipTests=true


To start the App: jar -jar target/anycompany-0.0.1-SNAPSHOT.jar OR 


mvn spring-boot:run

