package za.co.anycompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories("za.co.anycompany.anycompany.persistence.repo")
//@EntityScan("za.co.anycompany.anycompany.persistence.model")
@SpringBootApplication
public class AnycompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnycompanyApplication.class, args);
	}

}
