package za.co.anycompany;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class TechTestJava {

	private static final Logger LOG = LoggerFactory.getLogger(TechTestJava.class);

	public static void main(String[] args) {
		SpringApplication.run(TechTestJava.class, args);
		LOG.info("starting TechTestJava application");
	}

}
