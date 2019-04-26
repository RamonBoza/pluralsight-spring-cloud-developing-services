package pluralsight.demo.pluralsightspringcloudm1startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PluralsightSpringcloudM1StartupApplication {

	public static void main(String[] args) {
		SpringApplication.run(PluralsightSpringcloudM1StartupApplication.class, args);
	}

	@GetMapping(value="/greeting")
	public String sayHello() {
		return "Hello from Spring Boot!";
	}

}
