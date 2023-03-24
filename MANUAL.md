# AnyCompany Order App User Manual

## Introduction
> Hi this is an app which was specifically developed to solve the problem as 
> stated in the GitHub repository ([GitHub Project](https://github.com/LindaShole/TechTestJava)).

## Assumptions
1. Customers Already exist in the System.
2. A System Admin will be running the system and not customers
3. There is no sensitive data and system admin has all rights
4. Only valid accounts can login
5. We use cookies/session variables
6. Customers May not have orders
7. All orders correspond to a Customer
8. Customers have unique names and a customer has unique ID

## App Address 

* [Localhost](http://localhost:8081/) 
* [Azure](https://azure-spring-apps-dev-anycompany.azuremicroservices.io/)

## The available end-points
* __/login__     --->       (Enter Username and Password)
* __/__          --->      (Landing Page with CTA to other pages)
* __/customers__  --->      (Shows all Customers)
* __/customers/{id}__ --->  (View the customer Details)
* __/customer?customer={id}__ --->  (View the customer Details)
* __/orders__ --->  (Shows all Orders)
* __/order__ --->  (Allows User to Place Order)
* __/orders/customer?customer={id}__  --->  (Show all orders for a corresponding Customer) 
* __/order/{id}__ ---> (Show the order with id={id})

## Project Summary

1. Springboot 3 (Java Framework)
2. GitHub (Version Control repository)
3. Bootstrap 4.6 (Front end Styling)
4. JUnit 5 (Unit Testing)
5. MVC (architectural pattern followed), Controller, Repository, Service 
6. M2 (in Memory) Database used
7. JavaScript (dynamic front end content)

## Future Work
1. Integration Testing
2. Persistent Database (Oracle, MySQL, SQL Server etc)
3. Angular or other CSS and JavaScript Frameworks
4. Security
5. Monitoring
6. Optimisation
7. Mobile responsiveness
8. Live Server deployment 
9. Session Control
10. Authentication and Authorisation
11. Cloud Deployment 
12. User Registration
13. Admin Features
14. Error Handling
15. Animations
16. More JavaScript (Single Page Application -- SPA)