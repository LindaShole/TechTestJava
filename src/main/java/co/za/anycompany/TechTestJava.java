package co.za.anycompany;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@ComponentScan("co.za.anycompany")
@EnableAutoConfiguration
public class TechTestJava {

	private static final Logger log = LoggerFactory.getLogger(TechTestJava.class);
	
    public static void main(String[] args) {
    	SpringApplication app = new SpringApplication(TechTestJava.class);
        Environment environment = app.run(args).getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                "Application '{}' is running! Access URLs:\n\t" +
                "Local: \t\thttp://127.0.0.1:{}" +
                "\n----------------------------------------------------------",
                
                environment.getProperty("spring.application.name"),
                environment.getProperty("server.port"));
    }
}
