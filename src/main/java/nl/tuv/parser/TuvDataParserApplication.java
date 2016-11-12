package nl.tuv.parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class TuvDataParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(TuvDataParserApplication.class, args);
	}
}
