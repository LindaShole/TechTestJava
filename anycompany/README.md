
Welcome to AnyCompany Entertainment.

Note: (I have seen that the solution was written using pure Java and some JDBC CONNECTION with Prepare statement, 
I decided to using springboot framework and spring JPA for data Persistence in h2 memory DB and Mockito framework for Unit test  )

The following below is what is required to run the application.

1.This is a simple springboot application that is running using Java OpenJDK 11.
2.It is using gradle-4.10.3 for Building this project.
3.Install Postman to test the end points
4. Git clone this project and then import it as gradle project to your IDE.
5. Build your the project using this command : gradle build
6. Run the application on IDE or using the following command: 
7.After you has stated the application successfully.
8. Use the following url to connect to DB after your application has started succesfull : http://localhost:8080/h2-console after the login to db will popup and 
use jdbc:h2:mem:testdb on the JDBC URL and then connect.
9. you will see two tables Customer_table and Order_table.


This project consist of two end-points.

10.you can check API swagger documantation for this simple API by hitting this url http://localhost:8080/swagger-ui.html#/ 
11. By doing the above will give you short brief about endpoints below and schemas 

> http://localhost:8080/placeCustomerOrder/service/v1         (To Place an order for a customer)
> http://localhost:8080/retrieveCustomerOrders/service/v1     (To load/retrieve all the customers with their linked orders )



sample data format on postman on place order endpoint

[
 {
"name":"Lufuno",
"country":"South Africa",
"dateOfBirth":"1991-03-19",
 "orders" : [
        {   "orderId":1,
            "amount" : 112.00,
            "vat" :0.8
          
        },
        {   "orderId":12,
            "amount" : 1122.1,
            "vat" :0.5
        },
        {   "orderId":14,
            "amount" :105.8,
            "vat" :0.7
        }
    ]

}
]


