package za.co.anycompany;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import za.co.anycompany.controller.CustomerController;

/**
 * Spring boot allows us an elegant way to load our schema at application
 * start-up.
 *
 * It does so by executing the schema.sql file in the resources directory
 *
 * @author v-nchatitai
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    private ApplicationContext context;

    @Autowired
    public void context(ApplicationContext context) {
        this.context = context;
    }

    /**
     * Application execution entry point
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        LOGGER.info("Joiner thread started!!!");
//        CustomerController controller = context.getBean(CustomerController.class);
//        ResponseEntity<Object> cust = controller.getCustomers();  
    }
}
