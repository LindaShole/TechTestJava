package za.co.anycompany;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Slf4j
public class Application {

	public static void main(String[] args) {
		log.info("starting anycompaany");
		SpringApplication.run(Application.class, args);
		log.info("anycompaany started");
	}
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("za.co.anycompany"))
				.build().apiInfo(metaData());
	}
	private ApiInfo metaData() {
		return new ApiInfoBuilder()
				.title("Welcome to AnyCompany Entertainment")
				.description("Spring Boot REST API that populate place a customer order and load customer with their linked orders")
				.build();
	}

}
