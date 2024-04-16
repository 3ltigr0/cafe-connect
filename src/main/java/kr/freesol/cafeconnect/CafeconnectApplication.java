package kr.freesol.cafeconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CafeconnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CafeconnectApplication.class, args);
	}

}
