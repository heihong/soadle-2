package fr.soat.soadle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Predicates;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SoadleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoadleApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}	
	  
    @Bean
    public Docket api() {
    	return new Docket(DocumentationType.SWAGGER_2).select() 
    	          .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
    	          .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.cloud"))) 
    	             .build();
    }

}
