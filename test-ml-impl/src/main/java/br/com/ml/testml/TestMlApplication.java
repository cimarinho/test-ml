package br.com.ml.testml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TestMlApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestMlApplication.class, args);
	}
}
