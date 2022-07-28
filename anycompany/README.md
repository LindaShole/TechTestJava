### Running the Application

``` 
mvn clean spring-boot:run
```

Create a jar file from the project

```
mvn clean package
```

Run from jar file, default profile

```
java -jar target/anycompany.jar
```

### Technology

- Java 18
- Spring Boot 2.7.2 (with Spring Web, Spring Data JPA, Spring Test, Flyway)
- H2 Database
- Maven 3.8.6

These are APIs:

### Order

| Methods | 	Urls                                         | 	Actions                                                                |
|---------|-----------------------------------------------|-------------------------------------------------------------------------|
| POST	   | /api/order                                    | 	add new order                                                          |
| GET	    | /api/order                                    | 	retrieve all orders with the page and size 0 and 3 defaults respectively |
| GET	    | /api/order?page=[page]&size=[size]            | 	retrieve all orders with the specified page and size                 |
| GET	    | /api/order?page=[page]&size=[size]&name=[name] | 	retrieve all orders with the specified page, size, and containing name |
| GET     | 	/api/order/:id                             | 	retrieve an order by :id                                             |
| PUT     | 	/api/order/:id                             | 	update an order by :id                                               |
| DELETE  | 	/api/order/:id                             | 	delete an order by :id                                               |
| DELETE  | 	/api/order                                 | 	delete all orders                                                   |

#### Customer Payload

````
[
    {
        "name": "John Doe",
        "country": "UK",
        "dateOfBirth": "1995-01-01",
        "orders": [
            {
                "name": "July Order",
                "amount": 5000.00
            },
            {
                "name": "Home Order",
                "amount": 50000.00
            }
        ]
    }
]

````

```
[
    {
        "id": 1,
        "name": "John Doe",
        "country": "ZA",
        "dateOfBirth": "1990-01-01",
        "totalAmount": 1100.00,
        "quantity": 2,
        "orders": [
            {
                "orderId": 1,
                "name": "Chrismas Order",
                "amount": 100.00,
                "vat": 0.0
            },
            {
                "orderId": 2,
                "name": "Birthday Order",
                "amount": 1000.00,
                "vat": 0.0
            }
        ]
    },
    {
        "id": 2,
        "name": "Cool Joe",
        "country": "UK",
        "dateOfBirth": "1990-01-01",
        "totalAmount": 60000.00,
        "quantity": 2,
        "orders": [
            {
                "orderId": 3,
                "name": "Chrismas Order",
                "amount": 10000.00,
                "vat": 0.2
            },
            {
                "orderId": 4,
                "name": "Birthday Order",
                "amount": 50000.00,
                "vat": 0.2
            }
        ]
    }
]
```

### Note

- Refactored the package structure to reflect a tree directory structure which is a java convention
- The customer's total amount and the order quantity are automatically calculated based on total amount of orders and number of orders respectively
- Retrieving the customer list is paged and the default size of three and page is zero. 
- I didn't add business exceptions as errors are handled by Spring default API Error and I think it's sufficient for the demo 
- Refactored the method names to reflect the behaviour
- Refactored the database properties from the classes to the application properties 