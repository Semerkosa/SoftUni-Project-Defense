package bg.softuni.GimyApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class GimyApiApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(GimyApiApplication.class, args);
	}
}
