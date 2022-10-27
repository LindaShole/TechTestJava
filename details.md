Hello,

Welcome to AnyCompany Entertainment.

This microservice is about to creation of orders based on customer. This project is built useing Springboot framework, H2 Database.
Below mention end-points are created for to create and retrive customer and orders along with the sample payloads.

## Create customer API: 
###### URL : http://localhost:8080/api/v1/createCustomer

###### HTTP request type: POST

###### Request : {
	"customerName": "Sarthi",
	"country": "Canada",
	"dateOfBirth":"02/12/1992"
}

###### Response : {
	"customerId":1,
	"customerName": "Sarthi",
	"country": "Canada",
	"dateOfBirth":"02/12/1992"
}


## Retrive customer : 
###### URL : http://localhost:8080/api/v1/getCustomer?customerId=1

###### HTTP request type: GET

###### Response : {
    "customerId": 1,
    "customerName": "Sarthi",
    "country": "Canada",
    "dateOfBirth": "02/12/1992",
    "orders": [
        {
            "orderId": 1,
            "customerId": 0,
            "amount": 474,
            "vat": 0.0
        }
    ]
}

## Retrive all customers : 
###### URL : http://localhost:8080/api/v1/getAllCustomers

###### HTTP request type: GET

###### Response : [
    {
        "customerId": 1,
        "customerName": "Sarthi",
        "country": "Canada",
        "dateOfBirth": "02/12/1992",
        "orders": [
            {
                "orderId": 1,
                "customerId": 0,
                "amount": 474,
                "vat": 0.0
            }
        ]
    }
]

## Create order : 
###### URL : http://localhost:8080/api/v1/createOrder

###### HTTP request type: POST

###### Request : {
	"customerId":1,
	"amount":474
}

###### Response: {
    "orderId": 1,
    "customerId": 1,
    "amount": 474,
    "vat": 0
}
