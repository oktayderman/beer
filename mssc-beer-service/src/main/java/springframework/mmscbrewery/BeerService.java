package springframework.mmscbrewery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BeerService {

	public static void main(String[] args) {
		SpringApplication.run(BeerService.class, args);
	}

}
