package com.tlh;

import com.tlh.sys.config.TlhProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@EnableConfigurationProperties(TlhProperties.class)
@SpringBootApplication
public class TlhApplication {

	public static void main(String[] args) {
		SpringApplication.run(TlhApplication.class, args);
	}

}
