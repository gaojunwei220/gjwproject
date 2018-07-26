package net.practise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = "classpath:mykaptcha.xml")
@SpringBootApplication
public class SpringbootLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootLoginApplication.class, args);
	}
}
