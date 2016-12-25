package com.tlh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages="com.tlh.**.mapper")
public class TlhApplication {

	public static void main(String[] args) {
		SpringApplication.run(TlhApplication.class, args);
	}

}
