package com.tlh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class TlhApplication {

	public static void main(String[] args) {
		SpringApplication.run(TlhApplication.class, args);
	}

}
