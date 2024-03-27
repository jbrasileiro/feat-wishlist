package org.wishlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@PropertySource({
	"classpath:/properties/custom.properties"
})
@SpringBootApplication
@EnableMongoRepositories
public class CustomApplication {

	public static void main(
		final String[] args) {
		SpringApplication.run(CustomApplication.class, args);
	}
}
