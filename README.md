Hi,

I have implemented the functionality to create orders and customers via a rest API. To test the rest API via swagger, first start up the application by typing mvn spring-boot:run and then navigate to localhost:9090/swagger-ui.html in a browser.

By default, the application uses jdbc for persistence, but I have also added the option of using jpa. For this reason, I have removed the static functions from the Customer/Order repositories. To enable jpa and disable jdbc, set the persistence.use-jpa property to true in the application.yml file.
